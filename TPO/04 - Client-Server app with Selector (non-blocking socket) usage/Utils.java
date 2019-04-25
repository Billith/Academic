package zad1;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Utils {

    public static String readStringFromStream(InputStream stream) throws IOException {
        String output = "";
        byte[] buffer = new byte[8192];
        int readBytesLength;
        while ((readBytesLength = stream.read(buffer)) != -1) {
            byte[] smallBuffer = new byte[readBytesLength];
            for(int i=0; i < readBytesLength; i++) {
                smallBuffer[i] = buffer[i];
            }
            output += new String(smallBuffer);
        }
        return output;
    }

    public static String readStringFromStreamUntil(InputStream stream, String breaker) throws IOException {
        String output = "";
        byte[] buffer = new byte[8192];
        int readBytesLength;
        while ((readBytesLength = stream.read(buffer)) != -1) {
            byte[] smallBuffer = new byte[readBytesLength];
            for(int i=0; i < readBytesLength; i++) {
                smallBuffer[i] = buffer[i];
            }
            String part = new String(smallBuffer);
            if (part.contains(breaker)) {
                output += part.split(breaker)[0];
                return output;
            } else {
                output += part;
            }
        }
        return output;
    }

    public static String readStringFromChannel(SocketChannel channel) throws IOException {
        String output = "";
        ByteBuffer buffer = ByteBuffer.allocate(8192);
        int readBytesLength;
        while ((readBytesLength = channel.read(buffer)) > 0) {
            byte[] smallBuffer = new byte[readBytesLength];
            for(int i=0; i < readBytesLength; i++) {
                smallBuffer[i] = buffer.array()[i];
            }
            output += new String(smallBuffer);
        }
        return output;
    }

}
