import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Created by Lucas on 16.11.2017.
 */

public class TCPH2HClient {

    static String requestedFileName = "";
    Socket ClientSocket = new Socket();
    Socket sendInfoSoc = new Socket();
    ServerSocket listenSocket;
    ExecutorService pool;
    static boolean recivingPush = false;

    public TCPH2HClient(String file, String ipAddress, String targetPort, boolean isPull) {

        if (file.contains("/") || file.contains("\\")) {
            int index = (Main.isUnix) ? file.lastIndexOf("/") : file.lastIndexOf("\\");
            System.out.println(index + " : " + file.length());
            file = file.substring(index + 1);
        }
        requestedFileName = file;
        int[] ports = IntStream.rangeClosed(10000, 11000).toArray();
        pool = Executors.newFixedThreadPool(20);
        try {
            for (int port : ports) {
                try {
                    ClientSocket = new Socket(Inet4Address.getByName(ipAddress), Integer.parseInt(targetPort), Inet4Address.getByName("127.0.0.1"), port);
                    break;
                } catch (IOException e) {
                    continue;
                }
            }
            for (int port : ports) {
                try {
                    listenSocket = new ServerSocket(port);
                    break;
                } catch (IOException e) {
                    continue;
                }
            }
            new Thread(() -> {
                try {
                    while (true) {
                        pool.submit(new TCPH2HClientRequestHandler(listenSocket.accept()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
            DataOutputStream outToServer = new DataOutputStream(ClientSocket.getOutputStream());
            if(isPull) {
                outToServer.writeBytes("Type_" + "PULL" + ";" +
                        "ListenPort_" + listenSocket.getLocalPort() + ";" +
                        "RequestedFile_" + requestedFileName + ";");
                outToServer.close();
            } else {

                Pattern pFileName = Pattern.compile("[^\\\\]*$");
                Matcher mFileName = pFileName.matcher(file);
                String fileName;

                mFileName.find();
                fileName = mFileName.group();

                for (int port : ports) {
                    try {
                        sendInfoSoc = new Socket(Inet4Address.getByName(ipAddress), Integer.parseInt(targetPort), Inet4Address.getByName("127.0.0.1"), port);
                        break;
                    } catch (IOException e) {
                        continue;
                    }
                }
                DataOutputStream sendInfoAboutFileSoc = new DataOutputStream(sendInfoSoc.getOutputStream());
                sendInfoAboutFileSoc.writeBytes("Type_INFO;" + fileName);
                sendInfoAboutFileSoc.close();

                File fileToSend = new File(file);
                byte[] buffor = new byte[1024*1024];
                FileInputStream fis;
                try {
                    fis = new FileInputStream(fileToSend);
                    OutputStream out = ClientSocket.getOutputStream();

                    int countbytes = 0;
                    int count;
                    while((count = fis.read(buffor)) > 0) {
                        System.out.print(" .");
                        countbytes += 1024*1024;
                        out.write(buffor, 0, count);
                    }
                    out.close();
                    System.out.println("\nSent " + countbytes + " bytes\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!Repl.replActive.get())
                    System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TCPH2HClient(String ipAddress, String targetPort) {

        int[] ports = IntStream.rangeClosed(10000, 11000).toArray();
        pool = Executors.newFixedThreadPool(20);

        for (int port : ports) {
            try {
                ClientSocket = new Socket(Inet4Address.getByName(ipAddress), Integer.parseInt(targetPort), Inet4Address.getByName("127.0.0.1"), port);
                break;
            } catch (IOException e) {
                continue;
            }
        }

        for (int port : ports) {
            try {
                listenSocket = new ServerSocket(port);
                System.out.println("Listening for answer on 127.0.0.1:" + port);
                break;
            } catch (IOException e) {
                continue;
            }
        }
        new Thread(() -> {
            try {
                while (true) {
                    pool.submit(new TCPH2HClientRequestHandler(listenSocket.accept()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        try {
            DataOutputStream outToServer = new DataOutputStream(ClientSocket.getOutputStream());
            outToServer.writeBytes("Type_CHCK;" +
                    "ListenPort_" + listenSocket.getLocalPort() + ";");
            System.out.println("Query sent");
            outToServer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TCPH2HClient() {

        pool = Executors.newFixedThreadPool(20);

        int[] ports = IntStream.rangeClosed(11000, 12000).toArray();
        for (int port : ports) {
            try {
                listenSocket = new ServerSocket(port);
                System.out.println(port);
                break;
            } catch (IOException e) {
                continue;
            }
        }

        new Thread( () -> {
            while(Repl.shareEnabled.get()) {
                try {
                    pool.submit(new TCPH2HClientRequestHandler(listenSocket.accept()));
                } catch (SocketException ee) {
                    System.out.println("[!] Debug: share socket closed");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        while(true) {
            if (!Repl.shareEnabled.get()) {
                pool.shutdownNow();
                try {
                    listenSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }
}
