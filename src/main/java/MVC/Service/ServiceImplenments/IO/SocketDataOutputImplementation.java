package MVC.Service.ServiceImplenments.IO;

import MVC.Service.Enum.Status;
import MVC.Service.InterfaceService.IO.SocketDataOutput;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketDataOutputImplementation implements SocketDataOutput {
    @Override
    public void sendData(Socket socket, String message, Status status, String userName) throws IOException {
        DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
        String combinedMessage = status.toString() + "|" + userName + "|" + message;
        outToClient.writeBytes(combinedMessage + "\n");
    }
}
