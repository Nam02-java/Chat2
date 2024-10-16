package MVC;

import MVC.Controller.Server.ServerManager;
import MVC.Service.InterfaceService.Log.ConsoleLoggerServer;
import MVC.Service.ServiceImplenments.Log.ConsoleLoggerServerImplementation;

import java.io.IOException;

public class StartServer {

    public static void main(String[] args) throws IOException {
        ConsoleLoggerServer consoleLoggerServer = new ConsoleLoggerServerImplementation();
        consoleLoggerServer.save();
        ServerManager serverManager = new ServerManager();
        serverManager.initializeServer();
    }
}