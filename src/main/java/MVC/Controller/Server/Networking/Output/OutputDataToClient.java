package MVC.Controller.Server.Networking.Output;

import MVC.Model.Data;
import MVC.Service.Enum.Status;
import MVC.Service.InterfaceService.File.ParseFile;
import MVC.Service.InterfaceService.IO.SocketDataOutput;
import MVC.Service.InterfaceService.Log.ReadLogServer;
import MVC.Service.LazySingleton.Status.StatusManager;
import MVC.Service.LazySingleton.UserName.UserNameManager;
import MVC.Service.ServiceImplenments.File.ParseFileImplementation;

import java.io.BufferedReader;
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


                String[] parts = messageFromClient.split("\\|", 3);
                Status status = Status.valueOf(parts[0].trim());
                String userName = parts[1].trim();
                String messageContent = parts[2].trim();

                UserNameManager.getInstance().setUsername(userName);

                System.out.println(messageContent);


                if (messageFromClient.contains("- request history data")) {
                    StatusManager.getInstance().setCurrentStatus(Status.LOADING);
                    List<String> listChatHistory = readLogServer.read(data);
                    String message;
                    for (int i = 0; i < listChatHistory.size(); i++) {
                        message = listChatHistory.get(i);
                        if (i == 4) {
                            StatusManager.getInstance().setCurrentStatus(Status.RELAX);
                        }
                        socketDataOutput.sendData(clientSocket,
                                "Old message ( " + message + " )",
                                StatusManager.getInstance().getCurrentStatus(),
                                UserNameManager.getInstance().getUsername());
                    }

                } else {
                    for (Socket socket : Data.getClientSockets()) {
                        if (!socket.equals(clientSocket)) { 
                            StatusManager.getInstance().setCurrentStatus(Status.RELAX);
                            socketDataOutput.sendData(socket,
                                    messageContent,
                                    StatusManager.getInstance().getCurrentStatus(),
                                    userName);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
