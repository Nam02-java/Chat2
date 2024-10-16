package MVC.Service.InterfaceService.IO;

import java.io.IOException;
import java.net.Socket;

public interface SocketDataOutput {

    void sendData(Socket socket, String message) throws IOException;


}
