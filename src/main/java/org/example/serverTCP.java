package org.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class serverTCP {
    public static void main(String argv[]) throws Exception {
        String sentence_from_client;
        String sentence_to_client;

        //Tạo socket server, chờ tại cổng '6543'
        ServerSocket welcomeSocket = new ServerSocket(6543);

        while (true) {
            //chờ yêu cầu từ client
            Socket connectionSocket = welcomeSocket.accept();


            //Tạo input stream, nối tới Socket
            BufferedReader inFromClient =
                    new BufferedReader(new
                            InputStreamReader(connectionSocket.getInputStream()));

            //Tạo outputStream, nối tới socket
            DataOutputStream outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());

            //Đọc thông tin từ socket
            sentence_from_client = inFromClient.readLine();
            System.out.println(sentence_from_client);

            //ghi dữ liệu ra socket
            BufferedReader inFromServer =
                    new BufferedReader(new InputStreamReader(System.in));
            //Lấy chuỗi ký tự nhập từ bàn phím
            sentence_to_client = inFromServer.readLine();
            outToClient.writeBytes(sentence_to_client+ '\n');
        }
    }
}