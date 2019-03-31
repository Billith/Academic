package zad1;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public static String readStringFromStream(InputStream stream) throws IOException {
        String output = "";
        byte[] buffer = new byte[8192];
        int readBytesLength = 0;
        while ((readBytesLength = stream.read(buffer)) != -1) {
            byte[] smallBuffer = new byte[readBytesLength];
            for(int i=0; i < readBytesLength; i++) {
                smallBuffer[i] = buffer[i];
            }
            output += new String(smallBuffer);
        }
        return output;
    }

}
