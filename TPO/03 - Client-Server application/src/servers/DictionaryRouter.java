package zad1.servers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class DictionaryRouter {

    private static Map<String, Integer> dictionariesMap = new Hashtable<>();

    public DictionaryRouter() throws IOException {
        parseDictionaries();
    }

    public static Integer getLanguageServerPort(String languageCode) {
        try {
            return dictionariesMap.get(languageCode);
        } catch (Exception e) {
            return null;
        }
    }

    private void parseDictionaries() throws IOException {
        Files.walkFileTree(Paths.get("dicts"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws FileNotFoundException {
                int wordCount = parseDictionaryFileAndRunDictionaryServer(file);
                System.out.println("[+] Dictionary " + file.getFileName() + " : " + wordCount + " words parsed");
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private int parseDictionaryFileAndRunDictionaryServer(Path file) throws FileNotFoundException {
        Map<String,String> dictionary = new Hashtable<>();
        File dictionaryFile = new File(file.toUri());
        Scanner reader = new Scanner(dictionaryFile, "UTF-8");
        int wordCounter = 0;
        while (reader.hasNext()) {
            String[] words = reader.nextLine().split(":");
            dictionary.put(words[0], words[1]);
            wordCounter++;
        }
        runDictionaryServer(dictionary, file.getFileName().toString());
        return wordCounter;
    }

    private void runDictionaryServer(Map<String,String> dictionary, String fileName) {
        int ports[] = IntStream.rangeClosed(9000, 10000).toArray();
        for (int port : ports) {
            try {
                ServerSocket testSocket = new ServerSocket(port);
                testSocket.close();
                new DictionaryServer(dictionary, port);
                dictionariesMap.put(fileName, port);
                break;
            } catch (IOException e) {
                continue;
            }
        }
    }

}
