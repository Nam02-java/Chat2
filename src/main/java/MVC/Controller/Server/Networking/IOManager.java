package MVC.Controller.Server.Networking;

import MVC.Controller.Server.Networking.Input.InputDataFromClient;
import MVC.Controller.Server.Networking.Output.OutputDataToClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class IOManager implements Runnable {

    private Socket clientSocket;
    private InputDataFromClient inputDataFromClient;
    private OutputDataToClient outputDataToClient;

    private BufferedReader inFromClient;

    public IOManager(Socket clientSocket, InputDataFromClient inputDataFromClient, OutputDataToClient outputDataToClient) {
        this.clientSocket = clientSocket;
        this.inputDataFromClient = inputDataFromClient;
        this.outputDataToClient = outputDataToClient;
    }

    @Override
    public void run() {
        try {
            inFromClient = inputDataFromClient.receiveData(clientSocket);
            outputDataToClient.sendData(clientSocket, inFromClient);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
