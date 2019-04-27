package zad1.client.backend;

import javafx.application.Platform;
import zad1.Utils;
import zad1.client.frontend.ClientUI;

import java.io.IOException;
import java.net.Socket;

public class Client {

    private Socket serverConnection;

    public Client(String serverHostname, int serverPort) {
        try {
            serverConnection = new Socket(serverHostname, serverPort);
            runServerResponseThread();
        } catch (IOException e) {
            System.out.println("[Client] connection to the server failed!");
        }
    }

    private void runServerResponseThread() {
        Thread t1 = new Thread(() -> {
            while(true) {
                try {
                    String serverResponse = Utils.readStringFromStreamUntil(serverConnection.getInputStream(), "||", "\\|\1{2}");
                    if (serverResponse.trim().equals("")) {
                        Thread.sleep(1000);
                        continue;
                    }
                    parseServerResponse(serverResponse);
                } catch (IOException | InterruptedException e) {
                    System.out.println("[Client] failed to connect to the server in response loop retrying in 5 sec");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        t1.start();
    }

    private void parseServerResponse(String response) {
        System.out.println("[Client] response: " + response);
        String requests[] = response.split("\\|\1{2}");
        if (requests.length > 0) {
            for (String request : requests) {
                if (request.length() > 3) {
                    String opcode = request.substring(0, 3);
                    String value = request.substring(4);
                    if (opcode.equals("LST")) {
                        String[] topics = value.split("\\|");
                        Platform.runLater(() -> ClientUI.topicsList.setAll(topics));
                    } else if (opcode.equals("MSG")) {
                        String[] parts = value.split("\\|");
                        String topic = parts[0];
                        String message = parts[1];
                        if (ClientUI.messagesMap.containsKey(topic)) {
                            ClientUI.messagesMap.get(topic).add(message);
                        }
                    }
                }
            }
        }
    }

    public void subscribeToTopic(String topic) {
        try {
            serverConnection.getOutputStream().write(("SUB|" + topic).getBytes());
        } catch (IOException e) {
            System.out.println("[Client] failed to send subscribe request!");
        }
    }

    public void unsubscribeToTopic(String topic) {
        try {
            serverConnection.getOutputStream().write(("UNS|" + topic).getBytes());
        } catch (IOException e) {
            System.out.println("[Client] failed to send unsubscribe request!");
        }
    }

    public void getTopicsList() {
        try {
            sendRequestToServer("LST");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendRequestToServer(String opcode) throws IOException {
        serverConnection.getOutputStream().write(String.format("%s", opcode).getBytes());
    }

}
