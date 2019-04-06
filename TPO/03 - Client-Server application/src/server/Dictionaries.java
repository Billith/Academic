package zad1.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dictionaries {

    private Map<String, Map<String,String>> dictionariesMap;

    public Dictionaries() throws IOException {
        this.dictionariesMap = new HashMap<>();
        parseDictionaries();
        printAllDictionaries();
    }

    private void parseDictionaries() throws IOException {
        Files.walkFileTree(Paths.get("dicts"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws FileNotFoundException {
                int wordCount = parseDictionaryFile(file);
                System.out.println(file.getFileName() + " : " + wordCount + " words parsed");
                return FileVisitResult.CONTINUE;
            }
        });
        System.out.println();
    }

    private int parseDictionaryFile(Path file) throws FileNotFoundException {
        Map<String,String> dictionary = new HashMap<>();
        File dictionaryFile = new File(file.toUri());
        Scanner reader = new Scanner(dictionaryFile, "UTF-8");
        int wordCounter = 0;
        while (reader.hasNext()) {
            String[] words = reader.nextLine().split(":");
            dictionary.put(words[0], words[1]);
            wordCounter++;
        }
        dictionariesMap.put(file.getFileName().toString(), dictionary);
        return wordCounter;
    }

    public void printAllDictionaries() {
        for (String dictionary : dictionariesMap.keySet()) {
            Map<String, String> languageDictionary = dictionariesMap.get(dictionary);
            for (String word : languageDictionary.keySet()) {
                System.out.println(dictionary + " : " + word + " --> " + languageDictionary.get(word));
            }
            System.out.println();
        }
    }

    public String translateWord(String word, String languageCode) {
        if (dictionariesMap.containsKey(languageCode)) {
            Map<String, String> languageDictionary = dictionariesMap.get(languageCode);
            if (languageDictionary.containsKey(word)) {
                return languageDictionary.get(word);
            } else {
                return "Translation not found";
            }
        } else {
            return "Language not found";
        }
    }

}
