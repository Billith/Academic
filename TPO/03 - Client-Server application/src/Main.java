package zad1;

import zad1.client.frontend.MainWindow;
import zad1.server.Server;

public class Main {
    public static void main(String[] args) {
        new Thread(() -> new Server()).start();
        MainWindow.startUI();
    }
}
