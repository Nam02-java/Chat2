package MVC.Service.InterfaceService.IO;

import MVC.Service.Enum.Status;

import java.io.IOException;
import java.net.Socket;

public interface SocketDataOutput {

    void sendData(Socket socket, String message, Status status, String userName) throws IOException;


}
