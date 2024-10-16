package TwoUsers;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {

    public static ArrayList<Socket> clientSockets = new ArrayList<>();


    public ChatServer() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6543);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            clientSockets.add(clientSocket);
            System.out.println("New client connected : " + clientSocket);

            new Thread(new ClientHandler(clientSocket)).start();
        }
    }
}
