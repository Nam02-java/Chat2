package MVC.Service.ServiceImplenments.Initialization;

import MVC.Model.Data;
import MVC.Service.InterfaceService.ConnectionInitialization.SocketInitialization;

import java.io.IOException;
import java.net.Socket;

public class SocketInitializationImplementation implements SocketInitialization {
    @Override
    public Socket setUp() throws IOException {
        return new Socket("Localhost", Data.getPORT());
    }
}
