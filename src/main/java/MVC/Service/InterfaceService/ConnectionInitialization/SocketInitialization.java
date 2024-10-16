package MVC.Service.InterfaceService.ConnectionInitialization;


import java.io.IOException;
import java.net.Socket;

public interface SocketInitialization {

    Socket setUp() throws IOException;
}
