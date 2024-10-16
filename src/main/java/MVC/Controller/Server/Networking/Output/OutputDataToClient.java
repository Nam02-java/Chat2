package MVC.Controller.Server.Networking.Output;

import MVC.Model.Data;
import MVC.Service.InterfaceService.File.ParseFile;
import MVC.Service.InterfaceService.IO.SocketDataOutput;
import MVC.Service.InterfaceService.Log.ReadLogServer;
import MVC.Service.ServiceImplenments.File.ParseFileImplementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class OutputDataToClient {
    private SocketDataOutput socketDataOutput;
    private ReadLogServer readLogServer;
    private Data data;

    private ParseFile parseFile;

    public OutputDataToClient(SocketDataOutput socketDataOutput, ReadLogServer readLogServer, Data data) {
        this.socketDataOutput = socketDataOutput;
        this.readLogServer = readLogServer;
        this.data = data;
        this.parseFile = new ParseFileImplementation();
    }

    public void sendData(Socket clientSocket, BufferedReader inFromClient) {
        String messageFromClient;


        try {
            while ((messageFromClient = inFromClient.readLine()) != null) {

                System.out.println(messageFromClient);

                if (messageFromClient.contains("- request history data")) {
                    List<String> listChatHistory = readLogServer.read(data);
                    for (String message : listChatHistory) {
                        //socketDataOutput.sendData(clientSocket, "Old message ( " + message + " ) - total message " + listChatHistory.size());
                        socketDataOutput.sendData(clientSocket, "Old message ( " + message + " )");
                    }

                } else {
                    for (Socket socket : Data.getClientSockets()) {
                        if (socket != clientSocket) {
                            String ID = String.valueOf(parseFile.getBiggestID(new File(Data.getFilePath())));
                            String fullMessage = ID + "." + " " + messageFromClient;
                            socketDataOutput.sendData(socket, fullMessage);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
