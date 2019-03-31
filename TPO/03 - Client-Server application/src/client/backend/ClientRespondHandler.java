package zad1.client.backend;

import zad1.Utils;
import zad1.client.frontend.MainWindow;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientRespondHandler {

    Socket respondSocket;

    public ClientRespondHandler(Socket respondSocket) {
        this.respondSocket = respondSocket;
        getResponse();
    }

    public void getResponse() {
        try {
            InputStream respondSocketStream = this.respondSocket.getInputStream();
            String output = Utils.readStringFromStream(respondSocketStream);
            System.out.println("[Client] Got server response : " + output);
            if(MainWindow.serverResponseTextField != null)
                MainWindow.serverResponseTextField.setText(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
