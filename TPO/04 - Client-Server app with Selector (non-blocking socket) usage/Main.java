package zad1;

import zad1.admin.frontend.AdminUI;
import zad1.client.frontend.ClientUI;
import zad1.server.Server;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        //new Server();
        new Thread(() -> new Server()).start();

        //ClientUI.startUI();
        AdminUI.startUI();
    }

}
