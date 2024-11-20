package MVC.Controller.Client.Networking.Input;

import MVC.Service.Enum.Status;
import MVC.Service.InterfaceService.File.ParseFile;
import MVC.Service.InterfaceService.String.ParseString;
import MVC.Service.LazySingleton.ID.BiggestID;
import MVC.Service.LazySingleton.Status.StatusManager;
import MVC.Service.LazySingleton.UserName.UserNameManager;
import MVC.Service.InterfaceService.IO.SocketInputReader;
import MVC.Service.ServiceImplenments.File.ParseFileImplementation;
import MVC.Service.ServiceImplenments.String.ParseStringImplementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;

public class InputDataFromServer {
    private BufferedReader inFromServer;
    private SocketInputReader socketInputReader;
    private ParseString parseString;
    private ParseFile parseFile;

    public InputDataFromServer(SocketInputReader socketInputReader) {
        this.socketInputReader = socketInputReader;
        this.parseString = new ParseStringImplementation();
        this.parseFile = new ParseFileImplementation();
    }

    public void receiveData(Socket socket) throws IOException {
        inFromServer = socketInputReader.getData(socket);
        new Thread(() -> {
            try {
                String messageFromServer;
                int currentMessageId;
                Map<Integer, String> messageMap = new TreeMap<>();

                while ((messageFromServer = inFromServer.readLine()) != null) {

                    String[] parts = messageFromServer.split("\\|", 3);
                    Status status = Status.valueOf(parts[0].trim());
                    String userName = parts[1].trim();
                    String messageContent = parts[2].trim();

                    System.out.println(UserNameManager.getInstance().getUsername());
                    if (UserNameManager.getInstance().getUsername().equals(userName)) {
                        StatusManager.getInstance().setCurrentStatus(status);
                    }

                    currentMessageId = parseString.getIDMessage(messageFromServer);
                    messageMap.put(currentMessageId, messageContent);

                    if (StatusManager.getInstance().getCurrentStatus() == Status.RELAX) {
                        for (String message : messageMap.values()) {
                            System.out.println(message);
                        }
                        messageMap.clear();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }).start();
    }
}

