package MVC.Controller.Client.Networking.Output;

import MVC.Model.Data;
import MVC.Service.Enum.Status;
import MVC.Service.InterfaceService.File.ParseFile;
import MVC.Service.InterfaceService.IO.SocketDataOutput;
import MVC.Service.InterfaceService.IO.UserInputReceiver;
import MVC.Service.LazySingleton.ID.IDManager;
import MVC.Service.LazySingleton.Status.StatusManager;
import MVC.Service.LazySingleton.UserName.UserNameManager;
import MVC.Service.ServiceImplenments.File.ParseFileImplementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class OutputDataToServer {
    private SocketDataOutput socketDataOutput;
    private UserInputReceiver userInputReceiver;
    private ParseFile parseFile;
    private File file = new File(Data.getFilePath());

    public OutputDataToServer(SocketDataOutput socketDataOutput, UserInputReceiver userInputReceiver) {
        this.socketDataOutput = socketDataOutput;
        this.userInputReceiver = userInputReceiver;
        this.parseFile = new ParseFileImplementation();
    }

    public void sendData(Socket serverSocket) throws IOException {
        BufferedReader userInput = userInputReceiver.getData();
        while (true) {
            String ID = null;

            String messageToSend = UserNameManager.getInstance().getUsername() + " : " + userInput.readLine();

            if (file.length() == 0) {
                IDManager.getInstance().updateMaxReceivedId(1);
                ID = IDManager.getInstance().getMaxReceivedId() + "." + " ";
            } else if (file.length() >= 1) {
                IDManager.getInstance().updateMaxReceivedId(parseFile.getBiggestID(file) + 1);
                ID = IDManager.getInstance().getMaxReceivedId() + "." + " ";
            }
            StatusManager.getInstance().setCurrentStatus(Status.RELAX);

            String fullMessage = ID + messageToSend;
            if (messageToSend.contains("1")) {
                fullMessage = UserNameManager.getInstance().getUsername() + " : - request history data";
                StatusManager.getInstance().setCurrentStatus(Status.LOADING);
            }
            socketDataOutput.sendData(serverSocket,
                    fullMessage,
                    StatusManager.getInstance().getCurrentStatus(),
                    UserNameManager.getInstance().getUsername());
        }
    }
}
