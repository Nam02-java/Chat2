package MVC.Controller.Client.Networking;

import MVC.Controller.Client.Networking.Input.InputDataFromServer;
import MVC.Controller.Client.Networking.Output.OutputDataToServer;
import MVC.Service.ServiceImplenments.IO.SocketDataOutputImplementation;
import MVC.Service.ServiceImplenments.IO.SocketInputReaderImplementation;
import MVC.Service.ServiceImplenments.IO.UserInputReceiverImplementation;

import java.io.IOException;
import java.net.Socket;

public class IOManager {
    private InputDataFromServer inputDataFromServer;
    private OutputDataToServer outputDataToServer;

    public IOManager() {
        this.inputDataFromServer = new InputDataFromServer(new SocketInputReaderImplementation());
        this.outputDataToServer = new OutputDataToServer(new SocketDataOutputImplementation(), new UserInputReceiverImplementation());
    }

    public void initializeNetworking(Socket socket) throws IOException {
        inputDataFromServer.receiveData(socket);
        outputDataToServer.sendData(socket);
    }
}
