package MVC.Controller.Server.Networking.Input;

import MVC.Service.InterfaceService.IO.SocketInputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class InputDataFromClient {
    private SocketInputReader socketInputReader;
    private BufferedReader inFromClient;

    public InputDataFromClient( SocketInputReader socketInputReader) throws IOException {
        this.socketInputReader = socketInputReader;
    }

    public BufferedReader receiveData(Socket clientSocket) throws IOException {
        return inFromClient = socketInputReader.getData(clientSocket);
    }
}
