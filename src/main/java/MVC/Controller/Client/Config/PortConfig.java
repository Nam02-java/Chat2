package MVC.Controller.Client.Config;

import MVC.Service.InterfaceService.ConnectionInitialization.SocketInitialization;

import java.io.IOException;
import java.net.Socket;

public class PortConfig {

    private SocketInitialization socketInitialization;

    public PortConfig(SocketInitialization socketInitialization) {
        this.socketInitialization = socketInitialization;
    }

    public Socket configure() throws IOException {
        return socketInitialization.setUp();
    }
}
