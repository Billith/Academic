package zad1.client.backend;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

    public static void sendTranslationRequest(String word, String languageCode, int port) {
        try {
            new Thread(() -> {
                try {
                    startListening(port);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            Socket connectionSocket = new Socket("127.0.0.1", 31337);
            connectionSocket.getOutputStream().write(String.format("%s|%s|%d", word, languageCode, port).getBytes());
            connectionSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startListening(int port) throws IOException {
        ServerSocket respondSocket = new ServerSocket(port);
        System.out.println("[+] Client started listening on port: " + port);
        new ClientRespondHandler(respondSocket.accept());
        respondSocket.close();
    }
}
