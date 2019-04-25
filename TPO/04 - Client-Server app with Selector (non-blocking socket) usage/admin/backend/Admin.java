package zad1.admin.backend;

import zad1.Utils;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Admin {

    private String serverHostname;
    private int serverPort;

    public Admin(String hostname, int port) {
        serverHostname = hostname;
        serverPort = port;
    }

    public void addTopic(String topic) {
        try {
            String serverAnswer = sendRequestWithPayloadToServer("ADD", topic);
            System.out.printf("[Admin] server answer: %s\n", serverAnswer);
        } catch (IOException e) {
            System.out.println("[!] failed to send add request");
            e.printStackTrace();
        }
    }

    public void removeTopic(String topic) {
        try {
            String serverAnswer = sendRequestWithPayloadToServer("REM", topic);
            System.out.printf("[Admin] server answer: %s\n", serverAnswer);
        } catch (IOException e) {
            System.out.println("[!] failed to send remove request");
            e.printStackTrace();
        }
    }

    public List<String> getTopicsList() throws IOException {
        String serverAnswer = sendRequestToServer("LST");
        System.out.printf("[Admin] server answer: %s\n", serverAnswer);
        List<String> topicsList = new ArrayList<>();
        Collections.addAll(topicsList, serverAnswer.split("\\|"));
        return topicsList;
    }

    private String sendRequestToServer(String opcode) throws IOException {
        Socket serverConnection = new Socket(serverHostname, serverPort);
        serverConnection.getOutputStream().write(String.format("%s", opcode).getBytes());
        String serverAnswer = Utils.readStringFromStream(serverConnection.getInputStream());
        serverConnection.close();
        return serverAnswer;
    }

    private String sendRequestWithPayloadToServer(String opcode, String payload) throws IOException {
        Socket serverConnection = new Socket(serverHostname, serverPort);
        serverConnection.getOutputStream().write(String.format("%s|%s", opcode, payload).getBytes());
        System.out.println("[Admin] waiting for server answer ...");
        String serverAnswer = Utils.readStringFromStream(serverConnection.getInputStream());
        serverConnection.close();
        return serverAnswer;
    }

}
