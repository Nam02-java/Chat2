package MVC.Service.ServiceImplenments.Initialization;

import MVC.Model.Data;
import MVC.Service.InterfaceService.ConnectionInitialization.ServerSocketInitialization;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerInitializationImplementation implements ServerSocketInitialization {
    @Override
    public ServerSocket setUp() throws IOException {
        return new ServerSocket(Data.getPORT());
    }
}
