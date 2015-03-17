package ftp.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

//The console application
public class FileServerClientConsole {
    static FileServerClientConsole ftpConsoleApp;
    static ftp.FileServerClient fileServerClient;
    String connectedUsername;
    boolean connected = false;

    public static void main(String[] args) {
        try {
            //Attempt to start server using specified arguments
            if(args.length == 0) {
                fileServerClient = new ftp.FileServerClient("localhost",8080);
            }
            else if(args.length == 1){
                fileServerClient = new ftp.FileServerClient(args[0], 8080);
            }
            else if(args.length == 2){
                fileServerClient = new ftp.FileServerClient(args[0], Integer.parseInt(args[1]));
            }
            else {
                throw new Exception("Arguments Incorrect");
            }

            ftpConsoleApp = new FileServerClientConsole();
            ftpConsoleApp.runClient();
        }catch(Exception e){
            System.out.println("Unable to contact server....exiting");
            System.exit(0);
        }
    }

    public void runClient(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean done = false;
        String input, endMessage = ".";

        System.out.println("Welcome to the FileServer client.\n" +
                "Type 'help' to view a list of commands.\n" +
                "Type '.' to exit\n" +
                "Enter 'login' followed by your username to connect to the server.\n");

        //The loop to control user input
        while (!done) {
            try {
                //Print prompt (usability)
                if (ftpConsoleApp.connected)
                    System.out.print("FileServer:" + connectedUsername + "> ");
                else
                    System.out.print("FileServer> ");

                //Read the users input and normalise it
                input = br.readLine().trim().toLowerCase();

                if (input.equals(endMessage))
                    done = true;
                else if(input.equals("help")){
                    ftpConsoleApp.showHelpContent();
                }
                else {
                    ServerCommand command = ftpConsoleApp.retrieveCommandKeyword(input);
                    ftpConsoleApp.executeAction(command, input);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //Parse the users input and determine the entered command keyword
    //Display error messages as appropriate
    private ServerCommand retrieveCommandKeyword(String message) throws Exception{
        String[] arguments = message.split((" "));
        String command = arguments[0].toLowerCase();

        //Check that arguments were entered as required
        //They are not required for the logoff and list commands
        if(arguments.length <= 1 && !command.equals("logoff") && !command.equals("list")) //the user entered no arguments - this is invalid
            throw new Exception("Invalid input detected. Type 'help' for a list of commands.");

        ServerCommand requestCommand;

        //Determine entered command and assign the request command to an enum (code readability/removing magic text/numbers)
        if (command.equals("login")){
            if (!connected) {
                requestCommand = ServerCommand.LOGIN;
            }
            else{
                throw new Exception("You are already connected. Disconnect before opening a new session");
            }
        }
        else if (command.equals("list")){
            if(connected){
                requestCommand = ServerCommand.LIST;
            }
            else{
                throw new Exception("You must be connected to access this function");
            }
        }
        else if (command.equals("upload")){
            if(connected)
            {
                requestCommand = ServerCommand.UPLOAD;
            }
            else{
                throw new Exception("You must be connected to access this function");
            }
        }
        else if (command.equals("download")){
            if(connected){
                requestCommand = ServerCommand.DOWNLOAD;
            }
            else{
                throw new Exception("You must be connected to access this function");
            }
        }
        else if (command.equals("logoff")){
            if(connected)
                requestCommand = ServerCommand.LOGOFF;
            else
                throw new Exception("There is no connection open to FileServer!");
        }
        else{
            throw new Exception("Command not recognised. Type 'help' for a list of commands.");
        }

        return requestCommand;
    }

    //Execute the functionality requested by the user
    private void executeAction(ServerCommand command, String input){
        String[] split = input.split(" ");
        String argument = "";

        //If an argument was supplied
        if(split.length > 1)
            argument = split[1];

        //Use the aforementioned enum in choosing the action to execute
        if (command == ServerCommand.LOGIN) {
            String username = argument;

            if(username.length() > 0) {
                if (fileServerClient.login(username)) {
                    connectedUsername = username;
                    ftpConsoleApp.connected = true;
                    System.out.println("Connected successfully!");
                } else {
                    System.out.println("An error occurred. Please try again");
                }
            }else{
                System.out.println("Username cannot be blank!");
            }
        }
        else if (command == ServerCommand.LIST) {
            if (ftpConsoleApp.connected) {
                List<String> userFileList = fileServerClient.getFileList(connectedUsername);
                int i = 1;

                for (String fileName : userFileList) {
                    System.out.println(i + ". " + fileName);
                    i++;
                }
            }
        }
        else if (command == ServerCommand.LOGOFF) {
            if (fileServerClient.logoff(connectedUsername)) {
                connectedUsername = "";
                ftpConsoleApp.connected = false;
            } else {
                System.out.println("An error occurred. Please try again");
            }
        }
        else if (command == ServerCommand.UPLOAD) {
            if (fileServerClient.upload(argument, connectedUsername)) {
                System.out.println("File uploaded successfully");
            } else {
                System.out.println("Error uploading file. Please try again");
            }
        }
        else if (command == ServerCommand.DOWNLOAD) {
            fileServerClient.download(argument, connectedUsername);
        }
    }

    public void showHelpContent()
    {
        System.out.println("\n-----FileServer Help-----\nThe following commands are available:\n" +
                        "\tlogin <username>\n" +
                        "\tlist\n" +
                        "\tupload <filepath.extension>\n" +
                        "\tdownload <filename.extension>\n" +
                        "\tlogoff\n"
        );
    }
}