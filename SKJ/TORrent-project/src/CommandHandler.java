import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class CommandHandler {

    public CommandHandler(String cmd, Map<String, Boolean> options) {
        switch(cmd.split(" ")[0]) {
            case "quit"     : System.exit(0);
            case "exit"     : System.exit(0);
            case "help"     : printCommands(); break;
            case "info"     : printInfo(options); break;
            case "set"      : setParser(cmd, options); break;
            case "homedir"  : printHomeDir(); break;
            case "share"    : shareParser(cmd, options); break;
            case "pull"     : pullFromShare();
            case ""         : break;
            default         : System.out.println("[!] Invalid command\n");
        }
    }

    public void printCommands() {
        System.out.println(
                "quit                          terminate program\n" +
                "exit                          terminate program\n" +
                "help                          list all commands\n" +
                "homedir                       print home directory path\n" +
                "info                          print current mode\n" +
                "share [start|status|stop]     manage share module\n" +
                "set [mode|homedir] args...    sets user options\n"
        );
    }

    public void printHomeDir() {
        System.out.println("Home directory path: " + Main.homedir + "\n");
    }

    public void printInfo(Map<String, Boolean> options) {
        System.out.print("Mode: ");
        if (options.get("h2h") && !options.get("mh"))
            System.out.println("host to host\n");
        else if (options.get("mh") && !options.get("h2h"))
            System.out.println("multi host\n");
        else
            System.out.println("unknown\n");
    }

    public void setParser(String cmd, Map<String, Boolean> options) {
        String[] cmdArgs = cmd.split(" ");
        if (cmdArgs[1].equals("mode")) {
            if (!cmdArgs[2].equals("h2h") && !cmdArgs[2].equals("mh")) {
                System.out.println("[!] Invalid mode!\n");
                return;
            }

            if (cmdArgs[2].equals("h2h")) {
                options.put("h2h", true);
                options.put("mh", false);
                System.out.println("[+] Mode ==> host to host\n");
            } else {
                options.put("h2h", false);
                options.put("mh", true);
                System.out.println("[+] Mode ==> multi host\n");
            }
        } else if (cmdArgs[1].equals("homedir") && cmdArgs.length == 3) {
            File homedir = new File(cmdArgs[2]);
            if (homedir.exists() && homedir.isDirectory()) {
                Main.homedir = homedir.toPath();
                Main.isHomedirCuston = true;
            } else {
                System.out.println("[!] Wrong path\n");
            }
        } else {
            System.out.println("[!] Invalid command\n");
        }

    }

    public void shareParser(String cmd, Map<String, Boolean> options) {
        String[] cmdArgs = cmd.split(" ");
        if (cmdArgs[1].equals("start")) {
            Repl.shareEnabled.set(true);
            new Thread( () -> startSharing(options)).start();
        } else if (cmdArgs[1].equals("stop")) {
            Repl.shareEnabled.set(false);
        } else if (cmdArgs[1].equals("status")) {
            System.out.printf("Share enabled: %b\n\n", Repl.shareEnabled.get());
        } else {
            System.out.println("[!] Invalid command\n");
        }

    }

    public void startSharing(Map<String, Boolean> options) {
        if(options.get("h2h") && !options.get("mh")) {
            System.out.print("[+] Started sharing files on 127.0.0.1:");
            new TCPH2HClient();
        } else {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter server IP address: ");
            String ipAddress = input.nextLine();
            System.out.print("Enter server port: ");
            String port = input.nextLine();
            System.out.print("[+] Started sharing files on 127.0.0.1:");
            new Thread(() -> new TCPMHClient(ipAddress, port, null, true));
        }
    }

    public void pullFromShare() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter server IP address: ");
        String ipAddress = input.nextLine();
        System.out.print("Enter server port: ");
        String port = input.nextLine();
        System.out.print("Enter file to download: ");
        String fileName = input.nextLine();
        new TCPH2HClient(fileName, ipAddress, port, true);
    }

}
