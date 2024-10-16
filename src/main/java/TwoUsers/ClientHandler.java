package TwoUsers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }


    @Override
    public void run() {
        String messageFromClient;
        try {
            while ((messageFromClient = inFromClient.readLine()) != null) {
                System.out.println("Received to server : " + messageFromClient);
                sendMessageToOtherClients(messageFromClient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Gửi tin nhắn tới tất cả client khác (trừ người gửi)
    private void sendMessageToOtherClients(String message) throws IOException {
        for (Socket socket : ChatServer.clientSockets) {
            if (socket != clientSocket) {
                outToClient = new DataOutputStream(socket.getOutputStream());
                outToClient.writeBytes(message + "\n");
            }
        }
    }
}
