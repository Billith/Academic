import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Created by Lucas on 18.11.2017.
 */
public class TCPMHClient {

    Socket sendSocket;
    ServerSocket listenSocket;
    ExecutorService pool;
    static String listenPort = "";

    public TCPMHClient(String serverIpAddress, String serverPort, String requestedFile, boolean isShare) {

        TCPH2HClient.requestedFileName = requestedFile;
        int[] ports = IntStream.rangeClosed(11000, 12000).toArray();
        pool = Executors.newFixedThreadPool(20);


        for (int port : ports) {
            try {
                listenSocket = new ServerSocket(port);
                listenPort = String.valueOf(port);
                System.out.println(port);
                break;
            } catch (IOException e) {
                continue;
            }
        }

        ports = IntStream.rangeClosed(10000, 11000).toArray();

        for (int port : ports) {
            try {
                sendSocket = new Socket(Inet4Address.getByName(serverIpAddress), Integer.parseInt(serverPort), Inet4Address.getByName("127.0.0.1"), port);
                break;
            } catch (IOException e) {
                continue;
            }
        }

        if(isShare) {

            String finalListenPort = listenPort;
            new Thread(() -> {
                Path path = Main.homedir;
                Utils.watchForDirectory(path, serverIpAddress, serverPort, finalListenPort);
            }).start();

            try {
                DataOutputStream outToServer = new DataOutputStream(sendSocket.getOutputStream());
                outToServer.writeBytes("Type_JOIN;" +
                        "ListenPort_" + listenSocket.getLocalPort());
                System.out.println("Join message has been sent");
                outToServer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            new Thread(() -> {

                Socket keepAliveSocket = null;
                int[] portsKA = IntStream.rangeClosed(10000, 11000).toArray();

                while (true) {
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    for (int port : portsKA) {
                        try {
                            keepAliveSocket = new Socket(Inet4Address.getByName(serverIpAddress), Integer.parseInt(serverPort), Inet4Address.getByName("127.0.0.1"), port);
                            break;
                        } catch (IOException e) {
                            continue;
                        }
                    }

                    try {
                        DataOutputStream outKeepAlive = new DataOutputStream(keepAliveSocket.getOutputStream());
                        outKeepAlive.writeBytes("Type_KEEP;" +
                                "ListenPort_" + listenSocket.getLocalPort());
                        outKeepAlive.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } else if(requestedFile != null) {
            try {
                DataOutputStream outToServer = new DataOutputStream(sendSocket.getOutputStream());
                outToServer.writeBytes("Type_" + "PULL" + ";" +
                        "ListenPort_" + listenSocket.getLocalPort() + ";" +
                        "RequestedFile_" + requestedFile + ";");
                outToServer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            while(true) {
                System.out.println("[!] Checking for repl");
                if (Repl.replActive.get() && !Repl.shareEnabled.get())
                    return;
                pool.submit(new TCPH2HClientRequestHandler(listenSocket.accept()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
