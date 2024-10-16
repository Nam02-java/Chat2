package MVC.Controller.Client.Networking.Output;

import MVC.Service.InterfaceService.IO.SocketDataOutput;
import MVC.Service.InterfaceService.IO.UserInputReceiver;
import MVC.Service.LazySingleton.UserName.UserNameManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class OutputDataToServer {
    private SocketDataOutput socketDataOutput;
    private UserInputReceiver userInputReceiver;

    public OutputDataToServer(SocketDataOutput socketDataOutput, UserInputReceiver userInputReceiver) {
        this.socketDataOutput = socketDataOutput;
        this.userInputReceiver = userInputReceiver;
    }

    public void sendData(Socket serverSocket) throws IOException {
        BufferedReader userInput = userInputReceiver.getData();
        while (true) {
            String messageToSend = UserNameManager.getInstance().getUsername() + " : " + userInput.readLine();
            if (messageToSend.contains("1")) {
                messageToSend = UserNameManager.getInstance().getUsername() + " : - request history data";
            }
            socketDataOutput.sendData(serverSocket, messageToSend);
        }
    }
}
