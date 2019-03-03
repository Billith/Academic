package zad1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil {
    public static void processDir(String dirName, String resultFileName) {
        Path dirNamePath = Paths.get(dirName);
        try {
            FileChannel target = new FileOutputStream(resultFileName).getChannel();
            Files.walkFileTree(dirNamePath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    FileChannel source = FileChannel.open(file);
                    ByteBuffer buffer = ByteBuffer.allocate(8192);
                    int read_bytes;
                    while ((read_bytes = source.read(buffer)) != -1) {
                        //String content = new String(buffer.array(), 0, read_bytes, "cp1250");
                        ByteBuffer content = Charset.forName("utf-8").encode(
                                new String(buffer.array(), 0, read_bytes, "cp1250")
                        );
                        target.write(content);
                        //target.write(ByteBuffer.wrap(content.getBytes(StandardCharsets.UTF_8)));
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}