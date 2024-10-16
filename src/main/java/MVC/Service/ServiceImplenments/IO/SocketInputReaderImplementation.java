package MVC.Service.ServiceImplenments.IO;

import MVC.Service.InterfaceService.IO.SocketInputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketInputReaderImplementation implements SocketInputReader {
    @Override
    public BufferedReader getData(Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}
