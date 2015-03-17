package ftp.gui;

import com.sun.codemodel.JOp;
import ftp.FileServerClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileServerClientGUI {
    JFrame frame;
    Container container;
    JPanel connectPanel, mainPanel;
    JTextField hostnameField, portNumberField, usernameTextField;
    FileServerClient ftpServer;
    String connectedUsername;
    java.util.List<String> userFileList;
    JComboBox fileListComboBox;
    JButton downloadButton;

    //Construct the base GUI
    public FileServerClientGUI(){
        frame = new JFrame();
        frame.setTitle("FileServer Client");
        frame.setIconImage(new ImageIcon(getClass().getResource("clienticon.png")).getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        container = frame.getContentPane();

        displayLoginWindow();

        frame.setVisible(true);
        usernameTextField.requestFocus();
    }

    public static void main(String[] args) {
        FileServerClientGUI gui = new FileServerClientGUI();
    }

    public void displayLoginWindow(){
        //Remove components - e.g if disconnecting we want to clear the user interaction window
        //Then display the login window
        container.removeAll();
        buildLoginWindow();
        container.add(connectPanel);
    }

    public void displayMainWindow(){
        buildMainWindow();
        container.removeAll(); //Again, remove all components
        container.add(mainPanel);
    }

    //Construct standard swing interfaces
    public void buildLoginWindow(){
        connectPanel = new JPanel();

        JLabel hostnameLabel = new JLabel("Server hostname: ");
        JLabel portNumberLabel = new JLabel("Server port: ");
        hostnameField = new JTextField(10);
        hostnameField.setText("localhost");
        portNumberField = new JTextField(10);
        portNumberField.setText("8080");

        JLabel connectLabel = new JLabel("Please enter your username: ");
        usernameTextField = new JTextField(15);

        JButton connectButton = new JButton("Connect");
        connectButton.addActionListener(new ConnectEventHandler());

        connectPanel.add(hostnameLabel);
        connectPanel.add(hostnameField);
        connectPanel.add(portNumberLabel);
        connectPanel.add(portNumberField);
        connectPanel.add(connectLabel);
        connectPanel.add(usernameTextField);
        connectPanel.add(connectButton);

        frame.setSize(430,130);
    }

    public void buildMainWindow(){
        mainPanel = new JPanel();

        JPanel uploadPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        uploadPanel.setSize(50, 400);
        JLabel chooseFileLabel = new JLabel("Upload:");
        JButton uploadFileButton = new JButton("Choose a File...");
        uploadFileButton.addActionListener(new UploadEventHandler());
        uploadPanel.add(chooseFileLabel);
        uploadPanel.add(uploadFileButton);

        JPanel downloadPanel = new JPanel();
        downloadPanel.setSize(50,400);
        JLabel chooseDownloadLabel = new JLabel("Download:");
        fileListComboBox = new JComboBox();
        downloadButton = new JButton("Save Selected File");
        downloadButton.addActionListener(new DownloadEventHandler());
        downloadPanel.add(chooseDownloadLabel);
        downloadPanel.add(fileListComboBox);
        downloadPanel.add(downloadButton);

        JPanel downloadInfo = new JPanel();
        downloadPanel.setSize(50,400);
        JLabel downloadLocationLbl = new JLabel("Files saved to C:/fsdownloads/" + connectedUsername);
        JButton openDownloadLocationButton = new JButton("Open Folder...");
        openDownloadLocationButton.addActionListener(new DownloadLocationEventHandler());
        downloadInfo.add(downloadLocationLbl);
        downloadInfo.add(openDownloadLocationButton);

        JPanel disconnectPanel = new JPanel();
        JButton disconnectButton = new JButton("Disconnect");
        disconnectButton.addActionListener(new DisconnectEventHandler());
        disconnectPanel.add(disconnectButton);
        disconnectPanel.setSize(50,400);

        mainPanel.add(uploadPanel);
        mainPanel.add(downloadPanel);
        mainPanel.add(downloadInfo);
        mainPanel.add(disconnectPanel);

        frame.setSize(400,250);
    }

    //Display the users files in a drop down menu
    //Enable the download button once there are files available
    public void displayUserFiles(){
        userFileList = ftpServer.getFileList(connectedUsername);

        if(userFileList.size() == 0) {
            fileListComboBox.addItem("No files available");
            downloadButton.setEnabled(false);
        }
        else{
            fileListComboBox.removeAllItems();

            for(String fileName : userFileList)
                fileListComboBox.addItem(fileName);

            downloadButton.setEnabled(true);
        }
    }

    //Event handlers for button clicks
    //Class name shows button the handler is for
    private class ConnectEventHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String hostname = hostnameField.getText();
            String portNumString = portNumberField.getText();
            String username = usernameTextField.getText().trim();

            //Some validation
            if(username.length() == 0){
                JOptionPane.showMessageDialog(frame,"Username field cannot be blank.","Invalid Input",JOptionPane.INFORMATION_MESSAGE);
                usernameTextField.requestFocus();
            }else {
                try {
                    ftpServer = new FileServerClient(hostname, Integer.parseInt(portNumString));

                    if (ftpServer.login(username)) {
                        connectedUsername = username;
                        JOptionPane.showMessageDialog(frame, connectedUsername + " connected successfully.", "Connected", JOptionPane.INFORMATION_MESSAGE);
                        displayMainWindow();
                        displayUserFiles();
                    } else
                        JOptionPane.showMessageDialog(frame, "An error occurred, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "An error occurred while contacting the server. Check hostname and port number", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class DisconnectEventHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                if(ftpServer.logoff(connectedUsername)) {
                    connectedUsername = "";
                    displayLoginWindow();
                }
                else
                    JOptionPane.showMessageDialog(frame,"An error occurred, please try again.","Error",JOptionPane.ERROR_MESSAGE);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(frame,"An error occurred, please try again.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class UploadEventHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                //Use a JFileChooser to allow the user to graphically select a file to upload
                JFileChooser fileChooser = new JFileChooser();
                int selection = fileChooser.showDialog(frame,"Upload");

                if(selection == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getPath();
                    String fileName = fileChooser.getSelectedFile().getName();

                    if(ftpServer.upload(filePath,connectedUsername)) {
                        JOptionPane.showMessageDialog(frame, "Upload successful: " + fileName, "FileServer Client", JOptionPane.INFORMATION_MESSAGE);
                        displayUserFiles();
                    }
                    else
                        JOptionPane.showMessageDialog(frame, fileName + " failed to upload. Please try again", "FileServerClient", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(frame,"An error occurred, please try again.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class DownloadEventHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                //Try to download the file selected in the drop down box
                if(fileListComboBox.getSelectedIndex() != -1) {
                    String fileName = fileListComboBox.getSelectedItem().toString();

                    if (ftpServer.download(fileName, connectedUsername))
                        JOptionPane.showMessageDialog(frame, "Download successful", "FileServer Client", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(frame,"An error occurred, please try again.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class DownloadLocationEventHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //Try to open the users download folder
                File location = new File("C:/fsdownloads/" + connectedUsername);

                if(location.exists())
                    Desktop.getDesktop().open(location);
                else
                    JOptionPane.showMessageDialog(frame,"Folder doesn't exist! Download some files first!","FileServer Client",JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(frame,"An error occurred, please try again.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}