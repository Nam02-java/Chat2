package MVC.Controller.Server.Config;

import MVC.Model.Data;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientConnectionHandler {

    public Socket waitForClients(ServerSocket serverSocket) throws IOException {
        Socket clientSocket = serverSocket.accept();
        Data.getClientSockets().add(clientSocket);
        System.out.println("New client connected : " + clientSocket.getInetAddress());

        return clientSocket;
    }
}
