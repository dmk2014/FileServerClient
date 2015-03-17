package ftp.console;

//Enum used by console application
public enum ServerCommand {
    LOGIN(100), LOGOFF(200), UPLOAD(300), DOWNLOAD(400), LIST(500);

    private int value;

    private ServerCommand(int value){
        this.value = value;
    }
}