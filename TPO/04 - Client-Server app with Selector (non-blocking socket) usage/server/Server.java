package zad1.server;

import zad1.Utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

public class Server {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private Map<String, List<String>> topicsAndMessages = new HashMap<>();
    private Map<SocketChannel, List<String>> clientsConnectionsWithSubscribedTopics = new HashMap<>();


    public Server() {
        try {
            this.serverSocketChannel = ServerSocketChannel.open();
            this.serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 9001));
            this.serverSocketChannel.configureBlocking(false);
            registerSelector();
            serverLoop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerSelector() throws IOException {
        this.selector = selector.open();
        this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    private void serverLoop() {
        System.out.println("[+] Server started listening!");
        while(true) {
            try {
                int num = this.selector.select();

                if(num == 0) {
                    continue;
                }

                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();
                while(iter.hasNext()) {
                    SelectionKey key = iter.next();

                    if(key.isAcceptable()) {
                        SocketChannel connectionChannel = this.serverSocketChannel.accept();
                        connectionChannel.configureBlocking(false);
                        connectionChannel.register(selector, SelectionKey.OP_READ);
                    }

                    if(key.isReadable()) {
                        requestHandler(key);
                    }

                    iter.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    private void requestHandler(SelectionKey key) {
        SocketChannel connectionChannel = (SocketChannel) key.channel();

        String clientMsg = Utils.readStringFromChannel(connectionChannel).trim();
        if(clientMsg.equals("")) {
            return;
        }
        System.out.printf("[Server] %s\n", clientMsg);
        parseRequest(clientMsg, connectionChannel);
    }

    private void parseRequest(String clientMsg, SocketChannel connectionChannel) {
        try {
            String opcode = clientMsg.substring(0, 3);
            String value = (clientMsg.length() > 3) ? clientMsg.substring(4) : "";
            if(opcode.equals("SUB")) {
                subscribeClientToTopic(value, connectionChannel);
            } else if(opcode.equals("UNS")) {
                unsubscribeClientFromTopic(value, connectionChannel);
            } else if(opcode.equals("ADD")) {
                addTopic(value);
                connectionChannel.write(ByteBuffer.wrap("OK".getBytes()));
                connectionChannel.close();
            } else if(opcode.equals("REM")) {
                removeTopic(value);
                connectionChannel.write(ByteBuffer.wrap("OK".getBytes()));
                connectionChannel.close();
            } else if(opcode.equals("LST")) {
                sendTopicList(connectionChannel);
            } else if(opcode.equals("MSG")) {
                addMessage(value);
                connectionChannel.write(ByteBuffer.wrap("OK".getBytes()));
                connectionChannel.close();
            }
        } catch (Exception e) {
            System.out.printf("[Server] failed to parse a request: %s\n", clientMsg);
        }
    }

    private void addMessage(String value) {
        String[] parts = value.split("\\|");
        String topic = parts[0];
        String message = parts[1];
        if (topicsAndMessages.containsKey(topic)) {
            topicsAndMessages.get(topic).add(message);
            pushMessageToClients(topic, message);
            System.out.printf("[Server] message added to %s topic: %s\n", topic, message);
        }
    }

    private void pushMessageToClients(String topic, String message) {
        for (SocketChannel sc : clientsConnectionsWithSubscribedTopics.keySet()) {
            List<String> subscribedTopics = clientsConnectionsWithSubscribedTopics.get(sc);
            if (subscribedTopics.contains(topic)) {
                sendMessageTo(sc, topic, message);
            }
        }
    }

    private void sendMessageTo(SocketChannel sc, String topic, String message) {
        String messagePacket = "MSG|" + topic + "|" + message + "||";
        try {
            sc.write(ByteBuffer.wrap(messagePacket.getBytes()));
        } catch (IOException e) {
            System.out.println("[Server] message push failed!");
        }
    }

    private void sendTopicList(SocketChannel connectionChannel) {
        String topicsList = "LST|";
        for (String topic : topicsAndMessages.keySet()) {
            topicsList += topic + "|" ;
        }
        topicsList += "||";
        try {
            connectionChannel.write(ByteBuffer.wrap(topicsList.getBytes()));
        } catch (IOException e) {
            System.out.println("[Server] failed to send topics list!");
        }
    }

    private void removeTopic(String value) {
        if (topicsAndMessages.containsKey(value)) {
            topicsAndMessages.remove(value);
        }
    }

    private void addTopic(String value) {
        if (!topicsAndMessages.containsKey(value)) {
            topicsAndMessages.put(value, new ArrayList<>());
        }
    }

    private void subscribeClientToTopic(String topic, SocketChannel connectionChannel) {
        try {
            if (topicsAndMessages.containsKey(topic)) {
                if (clientsConnectionsWithSubscribedTopics.containsKey(connectionChannel)) {
                    if (!clientsConnectionsWithSubscribedTopics.get(connectionChannel).contains(topic)) {
                        clientsConnectionsWithSubscribedTopics.get(connectionChannel).add(topic);
                    }
                } else {
                    List<String> subscribedTopics = new ArrayList<>();
                    subscribedTopics.add(topic);
                    clientsConnectionsWithSubscribedTopics.put(connectionChannel, subscribedTopics);
                }
                System.out.print("[Server] all topics which client subscribes: ");
                for (String topicName : clientsConnectionsWithSubscribedTopics.get(connectionChannel))
                    System.out.print(topicName + " ");
                System.out.println();
            } else {
                connectionChannel.write(ByteBuffer.wrap(("ERR|Podany temat nie istnieje!||").getBytes()));
            }
        } catch (IOException e) {
            System.out.println("[Server] failed to send msg to client");
        }
    }

    private void unsubscribeClientFromTopic(String value, SocketChannel connectionChannel) {
        if (clientsConnectionsWithSubscribedTopics.containsKey(connectionChannel)) {
            if (clientsConnectionsWithSubscribedTopics.get(connectionChannel).contains(value)) {
                clientsConnectionsWithSubscribedTopics.get(connectionChannel).remove(value);
            }
        }
        System.out.print("[Server] all topics which client subscribes: ");
        for (String topicName : clientsConnectionsWithSubscribedTopics.get(connectionChannel))
            System.out.print(topicName + " ");
        System.out.println();
    }

}
