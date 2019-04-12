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
            sendErrorMessageToClient(clientPort, "Connection to the dictionary server failed");
        }
    }

    private void sendRequestToDictionaryServer(String wordToTranslate, String languageCode, int clientPort) throws IOException {
        Integer dictionaryServerPort = DictionaryRouter.getLanguageServerPort(languageCode);
        if (dictionaryServerPort == null) {
            if (DictionaryRouter.isRouteTableEmpty()) {
                sendErrorMessageToClient(clientPort, "There are no dictionary servers running");
                return;
            }
            sendErrorMessageToClient(clientPort, "Language not found");
            return;
        }
        Socket connectionSocket = new Socket("127.0.0.1", dictionaryServerPort);
        connectionSocket.setSoTimeout(20000);
        connectionSocket.getOutputStream().write((wordToTranslate + "|" + clientPort).getBytes());
        connectionSocket.close();
        System.out.println("[ProxyServer] Request passed to " + languageCode + " server on " + dictionaryServerPort + " port");
    }

    private void sendErrorMessageToClient(int clientPort, String errorMessage) {
        try {
            Socket connectionSocket = new Socket("127.0.0.1", clientPort);
            connectionSocket.setSoTimeout(20000);
            connectionSocket.getOutputStream().write(errorMessage.getBytes());
            connectionSocket.close();
            System.out.println("[ProxyServer] Error message sent to client: " + errorMessage);
        } catch (IOException e) {
            System.out.println("[Server] Failed to send response to the client");
        }
    }
}
