package zad1.servers;

import zad1.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ProxyServerRequestHandler implements Runnable {

    Socket listeningSocket;
    int clientPort;

    public ProxyServerRequestHandler(Socket listeningSocket) {
        this.listeningSocket = listeningSocket;
    }

    @Override
    public void run() {
        try {
            InputStream respondSocketStream = this.listeningSocket.getInputStream();
            String output = Utils.readStringFromStream(respondSocketStream);
            System.out.println("[ProxyServer] Got request : " + output);
            parseRequest(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseRequest(String request) {
        String[] requestParts = request.split("\\|");
        String wordToTranslate = requestParts[0];
        String languageCode = requestParts[1];
        clientPort = Integer.parseInt(requestParts[2]);
        try {
            sendRequestToDictionaryServer(wordToTranslate, languageCode, clientPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendRequestToDictionaryServer(String wordToTranslate, String languageCode, int clientPort) throws IOException {
        Integer dictionaryServerPort = DictionaryRouter.getLanguageServerPort(languageCode);
        if (dictionaryServerPort == null) {
            sendErrorMessageToClient(clientPort);
            return;
        }
        Socket connectionSocket = new Socket("127.0.0.1", dictionaryServerPort);
        connectionSocket.setSoTimeout(20000);
        connectionSocket.getOutputStream().write((wordToTranslate + "|" + clientPort).getBytes());
        connectionSocket.close();
        System.out.println("[ProxyServer] Request passed to " + languageCode + " servers on " + dictionaryServerPort + " port");
    }

    private void sendErrorMessageToClient(int clientPort) throws IOException {
        Socket connectionSocket = new Socket("127.0.0.1", clientPort);
        connectionSocket.setSoTimeout(20000);
        connectionSocket.getOutputStream().write("Language not found".getBytes());
        connectionSocket.close();
        System.out.println("[ProxyServer] Language code not found. Error message sent to client!");
    }
}
