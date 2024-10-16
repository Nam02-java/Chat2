package TwoUsers;

import java.io.IOException;

public class User2 {
    public static void main(String[] args) throws IOException {
        ChatClient chatClient1 = new ChatClient();
        chatClient1.start();
    }
}