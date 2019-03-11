package zad1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil {
    public static void processDir(String dirName, String resultFileName) {
        try {
            OpenOption[] options = new OpenOption[] { StandardOpenOption.CREATE, StandardOpenOption.WRITE };
            FileChannel target = FileChannel.open(Paths.get(resultFileName), options);
            Files.walkFileTree(Paths.get(dirName), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    FileChannel source = FileChannel.open(file);
                    ByteBuffer buffer = ByteBuffer.allocate(8192);
                    int read_bytes;
                    while ((read_bytes = source.read(buffer)) != -1) {
                        byte[] bufferBytesCopy = new byte[read_bytes];
                        for (int i=0; i<read_bytes;i++) {
                            bufferBytesCopy[i] = buffer.get(i);
                        }
                        CharBuffer encodedFilesContent = Charset.forName("cp1250").decode(ByteBuffer.wrap(bufferBytesCopy));
                        ByteBuffer decodedFilesContent = Charset.forName("utf-8").encode(encodedFilesContent);
                        target.write(decodedFilesContent);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}