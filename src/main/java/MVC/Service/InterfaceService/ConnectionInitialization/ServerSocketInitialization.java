package MVC.Service.InterfaceService.ConnectionInitialization;

import java.io.IOException;
import java.net.ServerSocket;

public interface ServerSocketInitialization {

    ServerSocket setUp() throws IOException;
}
