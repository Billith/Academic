package zad1.server;

import zad1.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ServerRequestHandler implements Runnable {

    Socket listeningSocket;
    int clientPort;

    public ServerRequestHandler(Socket listeningSocket) {
        this.listeningSocket = listeningSocket;
    }

    @Override
    public void run() {
        try {
            InputStream respondSocketStream = this.listeningSocket.getInputStream();
            String output = Utils.readStringFromStream(respondSocketStream);
            System.out.println("[Server] Got client request : " + output);
            String response = parseRequest(output);
            sendResponse(clientPort, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String parseRequest(String request) {
        String[] requestParts = request.split("\\|");
        String wordTranslation = Server.dictionaries.translateWord(requestParts[0], requestParts[1]);
        clientPort = Integer.parseInt(requestParts[2]);
        return wordTranslation;
    }

    private void sendResponse(int port, String response) throws IOException {
        System.out.println("[Server] Response sent : " + response);
        Socket connectionSocket = new Socket("127.0.0.1", port);
        connectionSocket.setSoTimeout(20000);
        connectionSocket.getOutputStream().write(response.getBytes());
        connectionSocket.close();
    }
}
