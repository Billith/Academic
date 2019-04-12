package zad1;

import zad1.client.frontend.MainWindow;
import zad1.servers.DictionaryRouter;
import zad1.servers.ProxyServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new Thread(() -> new ProxyServer()).start();    // starts proxy server
        new DictionaryRouter();                         // parses dictionaries and run dictionary servers
        MainWindow.startUI();                           // starts client UI
    }
}
