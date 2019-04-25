package zad1;

import zad1.admin.backend.Admin;
import zad1.server.Server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {

        new Thread(() -> new Server()).start();

        Thread.sleep(1000);
        Admin admin = new Admin("127.0.0.1", 9001);
        admin.addTopic("topic1");
        Thread.sleep(1000);
        admin.addTopic("topic2");
        Thread.sleep(1000);
        admin.addTopic("topic3");
        Thread.sleep(1000);
        admin.addTopic("topic4");
        Thread.sleep(1000);
        admin.addTopic("topic4");
        admin.getTopicsList();
        Thread.sleep(1000);
        admin.removeTopic("topic2");
        Thread.sleep(1000);
        admin.getTopicsList();

        //ClientUI.startUI();
    }

}
