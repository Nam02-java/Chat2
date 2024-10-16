package MVC.Model;


import java.net.Socket;
import java.util.ArrayList;

public class Data {
    private final static ArrayList<Socket> clientSockets = new ArrayList<>();
    private final static int PORT = 8080;
    private final static String filePath = "E:\\Save.txt";
    private int startLine = 0;



    public static ArrayList<Socket> getClientSockets() {
        return clientSockets;
    }

    public static int getPORT() {
        return PORT;
    }

    public static String getFilePath() {
        return filePath;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }
}
