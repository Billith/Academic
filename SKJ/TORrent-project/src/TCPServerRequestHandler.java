import java.io.*;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Created by Lucas on 18.11.2017.
 */
public class TCPServerRequestHandler implements Runnable {

    Socket clientSocket;
    Map<String, Map<String, String>> clientListWithFiles;
    Map<String, Timestamp> clientListWithDate;
    String type = "";
    int listenPort = 0;
    String requestedFile = "";
    StringBuilder data = new StringBuilder();

    public TCPServerRequestHandler(Socket soc, Map<String, Map<String, String>> cLWF, Map<String, Timestamp> cLWD) {
        this.clientSocket = soc;
        this.clientListWithFiles = cLWF;
        this.clientListWithDate = cLWD;
    }

    @Override
    public void run() {

        InputStream in;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            in = clientSocket.getInputStream();
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
            Pattern pListenPort = Pattern.compile("ListenPort_\\d{5}");
            Matcher mListenPort = pListenPort.matcher(data);
            while(mListenPort.find()) {
                String tmp = mListenPort.group();
                listenPort = Integer.parseInt(tmp.substring(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        switch(type) {
            case "Type_JOIN" : Protocol.join(clientListWithDate, listenPort); break;
            case "Type_KEEP" : Protocol.keepAlive(clientListWithDate, listenPort, data); break;
            case "Type_CRET" : Protocol.fileDetected(clientListWithFiles, listenPort, data); break;
            case "Type_DELT" : Protocol.fileDeleted(clientListWithFiles, listenPort, data); break;
            case "Type_PULL" : Protocol.pullRequest(clientListWithFiles, listenPort, data, requestedFile, clientSocket); break;
        }
    }

}
