package zad1.servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;

public class DictionaryServer {

    private ServerSocket listeningSocket;
    private Map<String, String> dictionary;

    public DictionaryServer(Map<String, String> dictionary, int port) throws IOException {
        this.dictionary = dictionary;
        this.listeningSocket = new ServerSocket(port);
        new Thread(() -> {
            try {
                startListening();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void startListening() throws IOException {
        System.out.println("[+] Language servers started listening on port: " + this.listeningSocket.getLocalPort());
        while(true) {
            new Thread(new DictionaryServerRequestHandler(listeningSocket.accept(), this.dictionary)).start();
        }
    }

}
