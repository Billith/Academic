package zad1.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    ServerSocket listeningSocket;
    private final int port = 31337;
    protected static Dictionaries dictionaries;

    public Server() {
        try {
            this.dictionaries = new Dictionaries();
            startListening();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void startListening() throws IOException {
        this.listeningSocket = new ServerSocket(port);
        System.out.println("[+] Server started listening on port: " + port);
        while(true) {
            new Thread(new ServerRequestHandler(listeningSocket.accept())).start();
        }
    }

}
