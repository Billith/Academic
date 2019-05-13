import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lucas on 16.11.2017.
 */
public class Main {

    static Path homedir;
    static boolean isHomedirCuston = false;
    static boolean isUnix = false;

    public static void main(String[] args) {

        int instanceNumber = 1;
        String ipAddress;
        String fileName;
        String port;

        Map<String, Boolean> options = new HashMap<>();
        options.put("h2h", true);
        options.put("mh", false);
        options.put("tcp", true);
        options.put("udp", false);

        String HostOS = System.getProperty("os.name", "unknown");
        homedir = (HostOS.equals("Windows 10")) ? Paths.get("C:\\TORrent_") : Paths.get(System.getProperty("user.home") + "/TORrent_");
        isUnix = (HostOS.equals("Windows 10")) ? false : true;

        if(args.length == 0 || (args.length != 0 && args[0].equals("--help"))) {
            System.out.println("\nusage: TORrent [OPTIONS] [FILE]\n" +
                               " --tcp                           przesylanie plikow protokolem TCP (domyślnie)\n" +
     // TODO in future (maybe) " --udp                           przesylanie plikow protokolem UDP\n" +
                               " --h2h                           ustawienie trybu host2host (domyślnie)\n" +
                               " --mh                            ustawienie trybu multihost\n" +
                               " --server                        tryb serwera\n" +
                               " --push <host_ip> <port> <file>  wrzucamy na wybrany host dany plik\n" +
                               " --pull <host_ip> <port> <file>  ściągamy z wybranego hosta dany plik\n" +
                               " --share                         rozpoczecie udostępniania plików\n" +
                               " --share <server_ip> <port>      rozpoczecie udostępniania plików w trybie mh\n" +
                               " --check <host_ip <port>         sprawdza dostepne pliki na hoscie\n");
        } else if(args.length != 0){
            for (int i=0; i<args.length; i++) {
                if (args[i].equals("--h2h")) {
                    options.put("h2h", true);
                    options.put("mh", false);
                } else if (args[i].equals("--mh")) {
                    options.put("h2h", false);
                    options.put("mh", true);
                } else if (args[i].equals("--tcp")) {
                    options.put("tcp", true);
                    options.put("udp", false);
                } else if (args[i].equals("--udp")) {
                    options.put("tcp", false);
                    options.put("udp", true);
                } else if (args[i].equals("--push")) {
                    ipAddress = args[i+1];
                    port = args[i+2];
                    fileName = args[i+3];
                    i+=3;
                    System.out.println("Sending file " + fileName + " to " + ipAddress + ":" + port  + " ...");
                    new TCPH2HClient(fileName, ipAddress, port, false);
                } else if (args[i].equals("--pull")) {
                    if(options.get("h2h") && !options.get("mh")){
                        ipAddress = args[i + 1];
                        port = args[i + 2];
                        fileName = args[i + 3];
                        i += 3;
                        Utils.createHomeDirectory(homedir, instanceNumber);
                        System.out.println("Asking " + ipAddress + ":" + port + " for file " + fileName + "...");
                        System.out.println("Debug: H2H mode");
                        new TCPH2HClient(fileName, ipAddress, port, true);
                    } else {
                        ipAddress = args[i + 1];
                        port = args[i + 2];
                        fileName = args[i + 3];
                        i += 3;
                        Utils.createHomeDirectory(homedir, instanceNumber);
                        System.out.println("Asking server for file " + fileName + "...");
                        System.out.println("Listening for file on 127.0.0.1:");
                        new TCPMHClient(ipAddress, port, fileName, false);
                    }
                } else if (args[i].equals("--server")) {
                    new Thread(() -> {
                        while(true) {
                            if(TCPServer.isStarted.get() == true) {
                                System.out.println("Server started!\n");break;
                            }
                        }
                    }).start();
                    new TCPServer();
                } else if (args[i].equals("--share")) {
                    if(options.get("h2h") && !options.get("mh")) {
                        Utils.createHomeDirectory(homedir, instanceNumber);
                        System.out.println("Debug: H2H mode");
                        System.out.print("Start sharing files on 127.0.0.1:");
                        new TCPH2HClient();
                    } else {
                        ipAddress = args[i+1];
                        port = args[i+2];
                        Utils.createHomeDirectory(homedir, instanceNumber);
                        System.out.println("Debug: MH mode");
                        System.out.print("Start sharing files on 127.0.0.1:");
                        new TCPMHClient(ipAddress, port, null, true);
                    }
                } else if (args[i].equals(("--check"))) {
                    ipAddress = args[i+1];
                    port = args[i+2];
                    i+=2;
                    System.out.println("Asking " + ipAddress + ":" + port + " for file list");
                    new TCPH2HClient(ipAddress, port);
                } else if (args[i].equals("--interactive")) {
                    if (args.length != 1) {
                        System.out.println("[!] Interactive mode can not be run with other options. Terminating.");
                        break;
                    }
                    new Repl(instanceNumber, options);
                }
            }
        }
    }

}
