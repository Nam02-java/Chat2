package MVC.Controller.Client.Networking.Input;

import MVC.Service.InterfaceService.File.ParseFile;
import MVC.Service.InterfaceService.String.ParseString;
import MVC.Service.LazySingleton.ID.BiggestID;
import MVC.Service.LazySingleton.UserName.UserNameManager;
import MVC.Service.InterfaceService.IO.SocketInputReader;
import MVC.Service.ServiceImplenments.File.ParseFileImplementation;
import MVC.Service.ServiceImplenments.String.ParseStringImplementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

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
                boolean flag = false;
                int count = 0;
                while ((messageFromServer = inFromServer.readLine()) != null) {

                    int serverCurrentMessageID = parseString.getIDMessage(messageFromServer);
                    int biggestIdSinceClientLaunch = BiggestID.getInstance().getBiggestID();

                    if (serverCurrentMessageID <= biggestIdSinceClientLaunch) {
                        flag = true;
                        count++;
                        if (count == 5) {
                            flag = false;
                        }
                    } else {
                        if (flag == true) {
                            continue;
                        }
                        count = 0;
                    }

                    if (messageFromServer.contains(UserNameManager.getInstance().getUsername())) {
                        String userName = UserNameManager.getInstance().getUsername();
                        messageFromServer = messageFromServer.replaceFirst(userName + " : ", "");
                    }
                    Thread.sleep(1000);
                    System.out.println(messageFromServer);
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}

