package TwoUsers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClient {

    public void start() throws IOException {
        Socket clientSocket = new Socket("localhost", 6543);

        // Input từ người dùng
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        // Output gửi tin nhắn tới server
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        // Input nhận tin nhắn từ server
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Luồng để lắng nghe tin nhắn từ server liên tục
        new Thread(() -> {
            try {
                String messageFromServer;
                while ((messageFromServer = inFromServer.readLine()) != null) {
                    System.out.println("Received: " + messageFromServer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Vòng lặp gửi tin nhắn từ người dùng
        while (true) {
            String messageToSend = userInput.readLine();
            outToServer.writeBytes(messageToSend + "\n");
        }
    }
}
