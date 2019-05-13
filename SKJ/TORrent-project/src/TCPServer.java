import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

/**
 * Created by Lucas on 18.11.2017.
 */
public class TCPServer {

    static AtomicBoolean isStarted = new AtomicBoolean(false);
    ServerSocket listenSocket;
    ExecutorService pool;
    Map<String, Map<String, String>> clientListWithFiles = new HashMap<>();
    Map<String, Timestamp> clientListWithDate = new HashMap<>();
    int[] ports = IntStream.rangeClosed(65000, 65500).toArray();


    public TCPServer() {

        Thread keepAliveThread = new Thread ( () -> {

            while(true) {
                try {
                    Thread.currentThread().sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                try {
                    synchronized (clientListWithDate) {
                        for (Map.Entry<String, Timestamp> entry : clientListWithDate.entrySet()) {
                            if ((currentTime.getTime() - entry.getValue().getTime()) > 3000) {
                                System.out.println("\n\n---------------------------------------------");
                                System.out.println("Client 127.0.0.1:" + entry.getKey() + " seems to be offline. Removing from online client list");
                                clientListWithDate.remove(entry.getKey());
                                System.out.println("Current client list:");
                                for (Map.Entry<String, Timestamp> entry1 : clientListWithDate.entrySet()) {
                                    System.out.println("Client - 127.0.0.1:" + entry1.getKey() + ",  Last keepalive - " + entry1.getValue());
                                }
                                System.out.println("---------------------------------------------");
                            }
                        }
                    }
                } catch (ConcurrentModificationException e) {
                    //e.printStackTrace();
                    //Rzuca wyjątek przy wykryciu hosta ktory nie wysyla pakietow keepalive, ale dalje go usuwa z listy
                    //Poki dziala to ignoruje wyjatek
                    continue;
                }

            }
        });

        keepAliveThread.setName("KeepAlive Thread");
        keepAliveThread.start();

        try {
            int usedPort = 0;
            for (int port : ports) {
                try {
                    listenSocket = new ServerSocket(port);
                    usedPort = port;
                    break;
                } catch (IOException e) {
                    continue;
                }
            }
            pool = Executors.newFixedThreadPool(20);
            isStarted.set(true);
            System.out.println("Server running on 127.0.0.1:" + usedPort);
            while (true) {
                pool.submit(new TCPServerRequestHandler(listenSocket.accept(), clientListWithFiles, clientListWithDate));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
