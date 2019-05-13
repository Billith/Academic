import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lucas on 28.11.2017.
 */
public class TCPH2HClientRequestHandler implements Runnable {

    Socket socket;
    Socket sendingSocket;
    String type = "";
    int listenPort = 0;
    String requestedFile = "";
    StringBuilder data = new StringBuilder();
    ByteArrayOutputStream baos;

    public TCPH2HClientRequestHandler(Socket LisSoc) {
        socket = LisSoc;
    }

    @Override
    public void run() {

        InputStream in;
        baos = new ByteArrayOutputStream();

        try {
            in = socket.getInputStream();
            byte[] buffer = new byte[1024*1024];
            int len;
            while((len = in.read(buffer)) > 0) {
                System.out.print(" .");
                baos.write(buffer, 0, len);
            }
            baos.flush();
            InputStream is1 = new ByteArrayInputStream(baos.toByteArray());

            int ch = is1.read();
            while(ch != -1 && data.length() < 270) {
                data.append((char)ch);
                ch = is1.read();
            }
            is1.close();
            type = "FILE";
            Pattern pType = Pattern.compile("Type_\\w{4}");
            Matcher mType = pType.matcher(data);
            while(mType.find()) {
                type = mType.group();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        switch(type) {
            case "Type_PULL" : Protocol.pullRequestOnClient(data, listenPort, requestedFile, socket, sendingSocket); break;
            case "Type_INFO" : Protocol.getRequestedFilename(data); break;
            case "Type_CHCK" : Protocol.checkAvailableFiles(data, listenPort, sendingSocket); break;
            case "Type_ANSW" : Protocol.printRespond(data); break;
            case "Type_SEED" : Protocol.sendAvailableFile(data, requestedFile); break;
            default: Protocol.saveFile(baos);
        }

    }
}
