package MVC.Controller.Client;

import MVC.Controller.Client.Config.PortConfig;
import MVC.Controller.Client.Networking.IOManager;

import MVC.Service.ServiceImplenments.Initialization.SocketInitializationImplementation;

import java.io.IOException;
import java.net.Socket;

public class ClientManager {

    /**
     * warning !
     * this is port config of client
     */
    private PortConfig portConfig;
    private IOManager ioManager;


    public ClientManager() {
        this.portConfig = new PortConfig(new SocketInitializationImplementation());
        this.ioManager = new IOManager();
    }

    public void initializeClient() throws IOException {
        Socket socket = portConfig.configure();
        ioManager.initializeNetworking(socket);
    }
}
