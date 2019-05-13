import java.io.*;
import java.net.Inet4Address;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Protocol {

    public static void join(Map<String, Timestamp> clientListWithDate, int listenPort) {
        synchronized (clientListWithDate) {
            clientListWithDate.put(String.valueOf(listenPort), new Timestamp(System.currentTimeMillis()));
        }
        System.out.println("\n\n---------------------------------------------" +
                           "\nNew client came online!\nCurrent client list: ");
        synchronized (clientListWithDate) {
            for (Map.Entry<String, Timestamp> entry : clientListWithDate.entrySet()) {
                System.out.println("Client - 127.0.0.1:" + entry.getKey() + ",  Last keepalive - " + entry.getValue());
            }
        }
        System.out.println("---------------------------------------------");
    }

    public static void keepAlive(Map<String, Timestamp> clientListWithDate, int listenPort, StringBuilder data) {
        Pattern pListenPort = Pattern.compile("ListenPort_\\d{5}");
        Matcher mListenPort = pListenPort.matcher(data);
        while(mListenPort.find()) {
            String tmp = mListenPort.group();
            listenPort = Integer.parseInt(tmp.substring(11));
        }
        synchronized (clientListWithDate) {
            clientListWithDate.put(String.valueOf(listenPort), new Timestamp(System.currentTimeMillis()));
        }
    }

    public static void fileDetected(Map<String, Map<String, String>> clientListWithFiles, int listenPort, StringBuilder data) {
        String fileWithHash = data.substring(22 + String.valueOf(listenPort).length());

        String hash = "";
        Pattern pHash = Pattern.compile("[^:]+$");
        Matcher mHash = pHash.matcher(fileWithHash);
        while(mHash.find()) {
            hash = mHash.group();
        }

        String filename = "";
        Pattern pFilename = Pattern.compile("^.+:");
        Matcher mFilename = pFilename.matcher(fileWithHash);
        while(mFilename.find()) {
            String tmp = mFilename.group();
            filename = tmp.substring(0, tmp.length()-1);
        }

        Boolean alreadyInMap = false;
        synchronized (clientListWithFiles) {
            for (Map.Entry<String, Map<String, String>> entry : clientListWithFiles.entrySet()) {
                if (entry.getKey().equals(String.valueOf(listenPort))) {
                    alreadyInMap = true;
                    entry.getValue().put(filename, hash);
                    break;
                }
            }
        }

        if(!alreadyInMap) {
            Map<String, String> clientFilesMap = new HashMap<>();
            clientFilesMap.put(filename, hash);
            synchronized (clientListWithFiles) {
                clientListWithFiles.put(String.valueOf(listenPort), clientFilesMap);
            }
        }

        System.out.println("\n---------------------------------------------" +
                           "\nNew file available shared by 127.0.0.1:" + listenPort +
                           "\nFilename: " + filename + "\nMD5 hash: " + hash +
                           "\n---------------------------------------------" +
                           "\n\nAll files shared by 127.0.0.1:" + listenPort);
        synchronized (clientListWithFiles) {
            clientListWithFiles.forEach((port, filesMap) -> {
                if(port.equals(String.valueOf(listenPort))) {
                    filesMap.forEach((f, h) -> {
                        System.out.println(f + "  -   " + h);
                    });
                }
            });
        }
    }

    public static void fileDeleted(Map<String, Map<String, String>> clientListWithFiles, int listenPort, StringBuilder data) {
        String filename = data.substring(22 + String.valueOf(listenPort).length());

        synchronized (clientListWithFiles) {
            for (Map.Entry<String, Map<String, String>> entry : clientListWithFiles.entrySet()) {
                if (entry.getKey().equals(String.valueOf(listenPort))) {
                    for (Map.Entry<String, String> entry1 : entry.getValue().entrySet()) {
                        if (entry1.getKey().equals(filename)) {
                            entry.getValue().remove(entry1.getKey());
                            break;
                        }
                    }
                }
            }
        }

        System.out.println("\n---------------------------------------------" +
                           "\nFile deleted by 127.0.0.1:" + listenPort +
                           "\nFilename: " + filename +
                           "\n---------------------------------------------" +
                           "\nAll files shared by 127.0.0.1:" + listenPort);
        synchronized (clientListWithFiles) {
            clientListWithFiles.forEach((port, filesMap) -> {
                if(port.equals(String.valueOf(listenPort))) {
                    filesMap.forEach((f, h) -> {
                        System.out.println(f + "  -   " + h);
                    });
                }
            });
        }
    }

    public static void pullRequest(Map<String, Map<String, String>> clientListWithFiles, int listenPort, StringBuilder data, String requestedFile, Socket clientSocket) {

        String seedListenPort;

        System.out.println("\n\nDebug: PCK_PULL");
        Pattern pListenPort = Pattern.compile("ListenPort_\\d{5}");
        Matcher mListenPort = pListenPort.matcher(data);
        while (mListenPort.find()) {
            String tmp = mListenPort.group();
            listenPort = Integer.parseInt(tmp.substring(11));
        }
        Pattern pRequestedFile = Pattern.compile("RequestedFile_.+");
        Matcher mRequestedFile = pRequestedFile.matcher(data);
        while (mRequestedFile.find()) {
            String tmp = mRequestedFile.group();
            requestedFile = tmp.substring(14, tmp.length() - 1);
        }
        System.out.println(clientSocket.getInetAddress() + ":" + listenPort + " is asking for file " + requestedFile);

        synchronized (clientListWithFiles) {
            for (Map.Entry<String, Map<String, String>> entry : clientListWithFiles.entrySet()) {
                for (Map.Entry<String, String> entry1 : entry.getValue().entrySet()) {
                    if (entry1.getKey().equals(requestedFile)) {
                        seedListenPort = entry.getKey();
                        System.out.println("Seed 127.0.0.1:" + seedListenPort + " has this file. Redirecting ...");

                        Socket redirectSocket = new Socket();
                        int[] ports = IntStream.rangeClosed(10000, 11000).toArray();

                        for (int port : ports) {
                            try {
                                redirectSocket = new Socket(Inet4Address.getByName("127.0.0.1"), listenPort, Inet4Address.getByName("127.0.0.1"), port);
                                break;
                            } catch (IOException e) {
                                continue;
                            }
                        }
                        try {
                            DataOutputStream sendRedirectInfo = new DataOutputStream(redirectSocket.getOutputStream());
                            sendRedirectInfo.writeBytes("Type_SEED;SeedListenPort_" + seedListenPort + ";RequestedFile_" + requestedFile + ";");
                            sendRedirectInfo.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }

    }

    public static void pullRequestOnClient(StringBuilder data, int listenPort, String requestedFile, Socket socket, Socket sendingSocket) {
        System.out.println("\nDebug: PCK_PULL");
        Pattern pListenPort = Pattern.compile("ListenPort_\\d{5}");
        Matcher mListenPort = pListenPort.matcher(data);
        while(mListenPort.find()) {
            String tmp = mListenPort.group();
            listenPort = Integer.parseInt(tmp.substring(11));
        }
        Pattern pRequestedFile = Pattern.compile("RequestedFile_.+;");
        Matcher mRequestedFile = pRequestedFile.matcher(data);
        while(mRequestedFile.find()) {
            String tmp = mRequestedFile.group();
            requestedFile = tmp.substring(14, tmp.length()-1);
        }
        System.out.println("\n" + socket.getInetAddress() +":" + listenPort + " is asking for file " + requestedFile);

        Path filePath = (Main.isUnix) ? Paths.get(Main.homedir + "/" + requestedFile) : Paths.get(Main.homedir + "\\" + requestedFile);
        System.out.println(filePath.toString());

        if(Files.exists(filePath)) {
            System.out.println("File is available");
            File file = new File(filePath.toString());
            int[] ports = IntStream.rangeClosed(10000, 11000).toArray();

            for (int port : ports) {
                try {
                    sendingSocket = new Socket(Inet4Address.getByName("127.0.0.1"), listenPort, Inet4Address.getByName("127.0.0.1"), port);
                    System.out.println("Send socket created on 127.0.0.1" + port);
                    break;
                } catch (IOException e) {
                    continue;
                }
            }
            byte[] buffor = new byte[1024*1024];
            FileInputStream fis;

            try {
                fis = new FileInputStream(file);
                OutputStream out = sendingSocket.getOutputStream();
                System.out.println("Trying to send file to " + socket.getInetAddress() + ":" + listenPort);

                int countbytes = 0;
                int count;
                while((count = fis.read(buffor)) > 0) {
                    System.out.print(" .");
                    countbytes += count;
                    out.write(buffor, 0, count);
                }
                out.close();
                System.out.println("\nSent " + countbytes + " bytes\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File is not available");
        }
    }

    public static void getRequestedFilename(StringBuilder data) {
        System.out.println("\nDebug: PCK_INFO");
        TCPH2HClient.requestedFileName = data.substring(10);
        System.out.println(" Sent file: " + TCPH2HClient.requestedFileName);
        TCPH2HClient.recivingPush = true;
    }

    public static void checkAvailableFiles(StringBuilder data, int listenPort, Socket sendingSocket) {
        System.out.println("\nDebug: PCK_CHCK");
        Pattern pListenPort = Pattern.compile("ListenPort_\\d{5}");
        Matcher mListenPort = pListenPort.matcher(data);
        while(mListenPort.find()) {
            String tmp = mListenPort.group();
            listenPort = Integer.parseInt(tmp.substring(11));
        }

        int[] ports = IntStream.rangeClosed(10000, 11000).toArray();
        for (int port : ports) {
            try {
                sendingSocket = new Socket(Inet4Address.getByName("127.0.0.1"), listenPort, Inet4Address.getByName("127.0.0.1"), port);
                System.out.println("\nSending file list to 127.0.0.1:" + listenPort +" on port " + port);
                break;
            } catch (IOException e) {
                continue;
            }
        }
        File homedir = new File(Main.homedir.toString());
        DataOutputStream out = null;
        for (final File fileEntry : homedir.listFiles() ) {
            byte[] file;
            byte[] hash;
            try {
                System.out.println("Calculating checksum for " + fileEntry.getName());
                file = Files.readAllBytes(Paths.get(fileEntry.getAbsolutePath()));
                hash = MessageDigest.getInstance("MD5").digest(file);
                out = new DataOutputStream(sendingSocket.getOutputStream());
                out.writeBytes("Type_ANSW; " + fileEntry.getName() + "  -   MD5: " + Utils.bytesToHexString(hash));
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printRespond(StringBuilder data) {
        System.out.println("\nDebug: PCK_ANSW");
        String answer = data.toString().replaceAll("Type_ANSW", "");
        StringTokenizer st = new StringTokenizer(answer, ";");
        System.out.println();
        while(st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }

        System.exit(0);
    }

    public static void sendAvailableFile(StringBuilder data, String requestedFile) {
        System.out.println("\nDebug: PCK_SEED");
        String seedListenPort = "";
        Pattern pSeedListenPort = Pattern.compile("SeedListenPort_\\d{5}");
        Matcher mSeedListenPort = pSeedListenPort.matcher(data);
        while(mSeedListenPort.find()) {
            String tmp = mSeedListenPort.group();
            seedListenPort = tmp.substring(15);
            break;
        }

        Pattern ppRequestedFile = Pattern.compile("RequestedFile_.+;");
        Matcher mmRequestedFile = ppRequestedFile.matcher(data);
        while(mmRequestedFile.find()) {
            String tmpp = mmRequestedFile.group();
            requestedFile = tmpp.substring(14, tmpp.length()-1);
            break;
        }

        System.out.println("Seed port: " + seedListenPort + "\nRequested file: " + requestedFile);

        int[] ports = IntStream.rangeClosed(10000, 11000).toArray();
        Socket redirectSocket = new Socket();
        try {
            for (int port : ports) {
                try {
                    redirectSocket = new Socket(Inet4Address.getByName("127.0.0.1"), Integer.parseInt(seedListenPort), Inet4Address.getByName("127.0.0.1"), port);
                    break;
                } catch (IOException e) {
                    continue;
                }
            }
            DataOutputStream outToRedirection = new DataOutputStream(redirectSocket.getOutputStream());
            outToRedirection.writeBytes("Type_" + "PULL" + ";" +
                    "ListenPort_" + TCPMHClient.listenPort + ";" +
                    "RequestedFile_" + requestedFile + ";");
            outToRedirection.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public static void saveFile(ByteArrayOutputStream baos) {
        System.out.println("\nDebug: PCK_DATA");
        InputStream is2 = new ByteArrayInputStream(baos.toByteArray());
        byte[] bytes = new byte[1024*1024];
        OutputStream out = null;

        try {
            out = (Main.isUnix) ? new FileOutputStream(Main.homedir.toString() + "/" + TCPH2HClient.requestedFileName) : new FileOutputStream(Main.homedir.toString() + "\\" + TCPH2HClient.requestedFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            int count2;
            while ((count2 = is2.read(bytes)) > 0) {
                out.write(bytes, 0, count2);
            }
            is2.close();
            baos.close();
            out.flush();
            out.close();
            System.out.println("\nFile saved in " + Main.homedir.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!TCPH2HClient.recivingPush && !Repl.replActive.get())
            System.exit(0);
    }

}
