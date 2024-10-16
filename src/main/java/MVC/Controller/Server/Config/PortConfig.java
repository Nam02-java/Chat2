package MVC.Controller.Server.Config;

import MVC.Service.InterfaceService.ConnectionInitialization.ServerSocketInitialization;

import java.io.IOException;
import java.net.ServerSocket;

public class PortConfig {

    private ServerSocketInitialization serverSocketInitialization;

    public PortConfig(ServerSocketInitialization serverSocketInitialization) {
        this.serverSocketInitialization = serverSocketInitialization;
    }

    public ServerSocket configure() throws IOException {
        return serverSocketInitialization.setUp();
    }
}
