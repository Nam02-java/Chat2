package MVC.Service.InterfaceService.IO;

import java.io.BufferedReader;

import java.io.IOException;
import java.net.Socket;

public interface SocketInputReader {


    BufferedReader getData(Socket socket) throws IOException;
}
