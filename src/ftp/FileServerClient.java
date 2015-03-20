package ftp;

import service.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//This is the client logic class
//It handles all server interaction for both the console and GUI applications
public class FileServerClient{
    FileServer service;

    public FileServerClient(String hostname, int port) throws Exception{
        FileServerService fileServer = new FileServerService(new URL("http://" +hostname + ":" + port + "/FileServer/ftpservice?wsdl"));
        service = fileServer.getFileServerPort();
    }

    //The below five methods represent the services exposed functionality and call it as required
    public boolean login(String username){
        return service.login(username);
    }

    public boolean logoff(String username){
        return service.logoff(username);
    }

    public boolean upload(String filePath, String username){
        try {
            String fileName = new File(filePath).getName();
            service.upload(fileName, readFileData(filePath), username);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean download(String fileName, String username){
        try{
            byte[] fileData = service.download(fileName,username);
            saveFileData(fileName,fileData,username);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<String> getFileList(String username){
        try{
            return service.getFileList(username);
        }catch(Exception ex){
            return null;
        }
    }

    //Attempt to read file data from disk (for upload)
    public byte[] readFileData(String filePath) throws Exception{
        if (!Files.exists(Paths.get(filePath)))
            throw new Exception("File not found, please check the path.");

        Path file = Paths.get(filePath);
        byte[] data = Files.readAllBytes(file);

        return data;
    }

    //Attempt to save file data to disk (following download)
    public void saveFileData(String fileName, byte[] fileData, String username){
       try {
           final String STORAGE_DIRECTORY = "C:/fsdownloads/";

          File saveDirectory = new File(STORAGE_DIRECTORY + username);

            if(!saveDirectory.exists()) {
                System.out.println("Attempting save directory creation...");
                Files.createDirectories(saveDirectory.toPath());
           }

           File fileToWrite = new File(saveDirectory + "/" + fileName);
           Files.write(fileToWrite.toPath(),fileData);
           System.out.println("File successfully saved to disk at: " + fileToWrite.toPath());

        }catch(Exception ex){
            System.out.println("Exception: " + ex.getMessage() + "\nFailed to save file to disk.");
        }
    }
}