package tests;

import model.*;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String backupPath = "system_data.bin";

        // Trwalosc ekstensji
        if(Files.exists(Paths.get(backupPath))) {
            deserializeData(backupPath);
        } else {
            createTestObjects();
            serializeData(backupPath);
        }
        ObjectPlus.printAllExtents();

        // Ekstensja
        Customer customer = Customer.getCustomerByLogin("CustomerLogin1");

        // Atrybut złożony
        EventTicketReservation reservation = new EventTicketReservation();
        SocialEvent event = new SocialEvent("event name", "event organizer", new URL("http://event.com"));

        // Atrybut opcjonalny
        Customer customer1 = new Customer("customerName3", "CustomerLastName3", "CustomerLogin3", "customer_email3@email.com");
        try {
            System.out.println(customer1.getPhoneNumber());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println(customer1);

        // Atrybut powtarzalny
        List<MovieProjectionGenre> genreList = new ArrayList<>();
        genreList.add(new MovieProjectionGenre("comedy"));
        genreList.add(new MovieProjectionGenre("romance"));
        new MovieProjection(
                "Movies description",
                ScreeningRoomType.TWO_D,
                "Movie title",
                "Movie director",
                genreList,
                MovieProjectionTranslationType.DUBBING,
                "Poland",
                135,
                LocalDate.now()
        );

        // Atrybut klasowy
        System.out.println("Global discount for all clients: " + Discount.getGlobalDiscount());

        // Atrybut pochodny
        MovieProjectionAgeCategory ageCategory = new MovieProjectionAgeCategory("PEGI 16", 16);
        System.out.println(ageCategory);

        // Metoda klasowa
        EventTicketReservation.removeUnpaidTicketReservations();

        // Przeslonięcie metody
        System.out.println(customer);

        // Przeciążenie metody


    }

    private static void createTestObjects() throws Exception {
        // Przeciazenie metody
        new Customer("customerName1", "CustomerLastName1", "CustomerLogin1", "customer_email1@email.com");
        new Customer("customerName2", "CustomerLastName2", "CustomerLogin2", "customer_email2@email.com", "222 222 222");
        new EventTicketReservation(EventTicketReversationStatus.NOT_PAID);
        new EventTicketReservation();
    }

    private static void deserializeData(String backupPath) throws IOException, ClassNotFoundException {
        ObjectInputStream fis = new ObjectInputStream(new FileInputStream(backupPath));
        ObjectPlus.readExtents(fis);
        System.out.println("\n[+] Extents deserialized!\n");
    }

    private static void serializeData(String backupPath) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(backupPath));
        ObjectPlus.writeExtents(oos);
        System.out.println("\n[+] Extents serialized!");
    }

}
