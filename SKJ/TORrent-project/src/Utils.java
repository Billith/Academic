import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.nio.file.*;
import java.security.MessageDigest;
import java.util.stream.IntStream;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Created by Lucas on 21.11.2017.
 */
public class Utils {
    public static void createHomeDirectory(Path homedir, int instanceNumber) {
        try {
            Files.createDirectory(Paths.get(homedir.toString() + instanceNumber));
            Main.homedir = Paths.get(homedir.toString() + instanceNumber);
        } catch (FileAlreadyExistsException r) {
            while(true) {
                if(Files.exists(Paths.get(homedir.toString() + instanceNumber))) {
                    instanceNumber++;
                } else {
                    try {
                        Files.createDirectory(Paths.get(homedir.toString() + instanceNumber));
                        Main.homedir = Paths.get(homedir.toString() + instanceNumber);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeHomeDirectory() {
        try {
            File[] files = new File(Main.homedir.toString()).listFiles();
            if(files != null) {
                for(File f : files) {
                    if (f.isDirectory())
                        removeHomeDirectory();
                    else
                        f.delete();
                }
            }
            Files.delete(Main.homedir);
        } catch (IOException e) {
            System.out.println("[!] Failed to remove homedir");
            e.printStackTrace();
        }

    }

    public static void watchForDirectory(Path path, String serverIpAddress, String serverPort, String listenPort) {

        FileSystem fs = path.getFileSystem();
        try (WatchService service = fs.newWatchService()) {
            int[] ports = IntStream.rangeClosed(10000, 11000).toArray();
            path.register(service, ENTRY_CREATE, ENTRY_DELETE);
            WatchKey key;

            while(true) {
                key = service.take();
                WatchEvent.Kind<?> kind;

                for(WatchEvent<?> watchEvent : key.pollEvents()) {
                    kind = watchEvent.kind();
                    if (OVERFLOW == kind) {
                        continue;
                    } else if (ENTRY_CREATE == kind) {
                        Path newPath = ((WatchEvent<Path>) watchEvent)
                                .context();
                        System.out.println("Debug: New path created: " + newPath);

                        byte[] file, hash;

                        while(true) {
                            try {
                                file = (Main.isUnix) ? Files.readAllBytes(Paths.get(Main.homedir.toString() + "/" + newPath)) : Files.readAllBytes(Paths.get(Main.homedir.toString() + "\\" + newPath));
                                hash = MessageDigest.getInstance("MD5").digest(file);
                                break;
                            } catch (FileSystemException ee) {
                                System.out.println("File is locked. Waiting for file");
                                Thread.currentThread().sleep(5000);
                                continue;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        Socket sendSocket = null;

                        for (int port : ports) {
                            try {
                                sendSocket = new Socket(Inet4Address.getByName(serverIpAddress), Integer.parseInt(serverPort), Inet4Address.getByName("127.0.0.1"), port);
                                break;
                            } catch (IOException e) {
                                continue;
                            }
                        }
                        try {
                            DataOutputStream outToServer = new DataOutputStream(sendSocket.getOutputStream());
                            outToServer.writeBytes("Type_CRET;ListenPort_" + listenPort + ";" + newPath + ":" + bytesToHexString(hash));
                            outToServer.close();
                        } catch (Exception e) {

                        }
                    } else if (ENTRY_DELETE == kind) {
                        Path newPath = ((WatchEvent<Path>) watchEvent).context();
                        System.out.println("Debug: Path deleted: " + newPath);

                        Socket sendSocket = null;
                        for (int port : ports) {
                            try {
                                sendSocket = new Socket(Inet4Address.getByName(serverIpAddress), Integer.parseInt(serverPort), Inet4Address.getByName("127.0.0.1"), port);
                                break;
                            } catch (IOException e) {
                                continue;
                            }
                        }
                        try {
                            DataOutputStream outToServer = new DataOutputStream(sendSocket.getOutputStream());
                            outToServer.writeBytes("Type_DELT;ListenPort_" + listenPort + ";" + newPath);
                            outToServer.close();
                        } catch (Exception e) {

                        }
                    }
                }
                if (!key.reset()) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String bytesToHexString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes){
            sb.append(String.format("%02x", b&0xff));
        }
        return sb.toString();
    }

}