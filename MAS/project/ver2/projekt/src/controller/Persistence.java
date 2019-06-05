package controller;

import model.oplusplus.ObjectPlus;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Persistence {

    private static Path persistenceFilePath = Paths.get("system.bin");

    public static void restoreSystemObjects() {
        if (Files.exists(persistenceFilePath)) {
            try {
                ObjectPlus.readExtents(new ObjectInputStream(new FileInputStream(persistenceFilePath.toString())));
                System.out.println("[+] System data restored");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("[!] Failed to read system data from file!");
            }
        }
    }

    public static void saveSystemObjects() {
        try {
            ObjectPlus.writeExtents(new ObjectOutputStream(new FileOutputStream(persistenceFilePath.toString())));
            System.out.println("[+] System data saved");
        } catch (IOException e) {
            System.out.println("[!] Failed to save system data to file!");
        }
    }

}
