package zad1.servers;

import zad1.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Map;

public class DictionaryServerRequestHandler implements Runnable {

    private Socket listeningSocket;
    private Map<String, String> dictionary;
    private int clientPort;

    public DictionaryServerRequestHandler(Socket listeningSocket, Map<String, String> dictionary) {
        this.listeningSocket = listeningSocket;
        this.dictionary = dictionary;
    }

    @Override
    public void run() {
        try {
            InputStream respondSocketStream = this.listeningSocket.getInputStream();
            String output = Utils.readStringFromStream(respondSocketStream);
            System.out.println("[DictServer] Got  request : " + output);
            String response = parseRequest(output);
            sendResponse(clientPort, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendResponse(int port, String response) throws IOException {
        System.out.println("[DictServer] Response sent : " + response);
        Socket connectionSocket = new Socket("127.0.0.1", port);
        connectionSocket.setSoTimeout(20000);
        connectionSocket.getOutputStream().write(response.getBytes());
        connectionSocket.close();
    }

    private String parseRequest(String request) {
        String[] requestParts = request.split("\\|");
        String wordTranslation = translateWord(requestParts[0]);
        clientPort = Integer.parseInt(requestParts[1]);
        return wordTranslation;
    }

    private String translateWord(String word) {
        if (dictionary.containsKey(word)) {
            return dictionary.get(word);
        } else {
            return "Translation not found";
        }
    }

}
