package zad1.client.backend;

import zad1.Utils;
import zad1.client.frontend.MainWindow;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientRespondHandler {

    private Socket respondSocket;

    public ClientRespondHandler(Socket respondSocket) {
        this.respondSocket = respondSocket;
        getResponse();
    }

    private void getResponse() {
        try {
            InputStream respondSocketStream = this.respondSocket.getInputStream();
            String output = Utils.readStringFromStream(respondSocketStream);
            System.out.println("[Client] Got servers response : " + output);
            if(MainWindow.outputTextField != null)
                MainWindow.outputTextField.setText(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
