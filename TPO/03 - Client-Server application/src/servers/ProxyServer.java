package zad1.servers;

import java.io.IOException;
import java.net.ServerSocket;

public class ProxyServer {

    private ServerSocket listeningSocket;
    private final int port = 31337;

    public ProxyServer() {
        try {
            startListening();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startListening() throws IOException {
        this.listeningSocket = new ServerSocket(port);
        System.out.println("[+] ProxyServer started listening on port: " + port);
        while(true) {
            new Thread(new ProxyServerRequestHandler(listeningSocket.accept())).start();
        }
    }

}
