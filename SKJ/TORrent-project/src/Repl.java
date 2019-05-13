import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Repl {

    static AtomicBoolean shareEnabled = new AtomicBoolean(false);
    static AtomicBoolean replActive = new AtomicBoolean(false);
    String cmd;
    Scanner input = new Scanner(System.in);
    int instanceNumber;
    Map<String, Boolean> options;

    public Repl(int instanceNumber, Map<String, Boolean> options) {
        this.instanceNumber = instanceNumber;
        this.options = options;
        replActive.set(true);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (!Main.isHomedirCuston)
                Utils.removeHomeDirectory();
            System.out.println("Bye!");
        }));

        System.out.println("Welcome to TORrent, simple file share software\n\n" +
                "Type 'help' for command list\n" +
                "     'quit' or 'exit' to back to terminal");

        Utils.createHomeDirectory(Main.homedir, this.instanceNumber);

        for(;;) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(">> ");
            cmd = input.nextLine();
            new CommandHandler(cmd, options);
        }
    }
}
