package zad1;

import zad1.client.backend.Client;
import zad1.client.frontend.MainWindow;
import zad1.server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        new Thread(() -> new Server()).start();

        Thread.currentThread().sleep(1000);

        //Client client = new Client();
        //client.sendTranslationRequest("usta", "DE", 1337);
        new Thread(() -> MainWindow.startUI()).start();
    }
}
