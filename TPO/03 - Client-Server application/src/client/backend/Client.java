package zad1.client.backend;

import zad1.client.frontend.MainWindow;

import java.io.IOException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Client {

    public static void sendTranslationRequest(String word, String languageCode, int port) {
        try {
            Socket connectionSocket = new Socket("127.0.0.1", 31337);
            connectionSocket.setSoTimeout(20000);

            if (isPortAvailable(port)) {
                new Thread(() -> {
                    try {
                        startListening(port);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            } else {
                MainWindow.outputTextField.setText("Failed to open port.");
            }

            connectionSocket.getOutputStream().write(String.format("%s|%s|%d", word, languageCode, port).getBytes());
            connectionSocket.close();
            System.out.println("[Client] Translation request sent");
        } catch (ConnectException e) {
            MainWindow.outputTextField.setText("Failed to connect to the proxy server");
        } catch (SocketException e) {
            MainWindow.outputTextField.setText("Connection to proxy server timed out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isPortAvailable(int port) {
        try {
            new ServerSocket(port).close();
            return true;
        } catch (IOException e) {}
        return false;
    }

    private static void startListening(int port) throws IOException {
        ServerSocket respondSocket = new ServerSocket(port);
        System.out.println("[Client] Started listening on port: " + port);
        new ClientRespondHandler(respondSocket.accept());
        respondSocket.close();
    }
}
