package MVC.Controller.Server;

import MVC.Controller.Server.Config.ClientConnectionHandler;
import MVC.Controller.Server.Config.PortConfig;
import MVC.Controller.Server.Networking.IOManager;
import MVC.Controller.Server.Networking.Input.InputDataFromClient;
import MVC.Controller.Server.Networking.Output.OutputDataToClient;
import MVC.Model.Data;
import MVC.Service.ServiceImplenments.IO.SocketDataOutputImplementation;
import MVC.Service.ServiceImplenments.IO.SocketInputReaderImplementation;
import MVC.Service.ServiceImplenments.Initialization.ServerInitializationImplementation;
import MVC.Service.ServiceImplenments.Log.ReadLogServerImplementation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerManager {

    private PortConfig portConfig;
    private ClientConnectionHandler clientConnectionHandler;

    public ServerManager() {
        this.portConfig = new PortConfig(new ServerInitializationImplementation());
        this.clientConnectionHandler = new ClientConnectionHandler();
    }

    public void initializeServer() throws IOException {

        ServerSocket serverSocket = portConfig.configure();

        while (true) {

            Socket clientSocket = clientConnectionHandler.waitForClients(serverSocket);

            InputDataFromClient inputDataFromClient = new InputDataFromClient(new SocketInputReaderImplementation());
            OutputDataToClient outputDataToClient = new OutputDataToClient(new SocketDataOutputImplementation(), new ReadLogServerImplementation(), new Data());

            new Thread(new IOManager(clientSocket, inputDataFromClient, outputDataToClient)).start();
        }
    }
}
