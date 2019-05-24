import abstract_class.CinemaRoom;
import abstract_class.MovieProjection;
import abstract_class.MovieProjectionThreeD;
import abstract_class.MovieProjectionTwoD;
import dynamic.HourlyEmployee;
import dynamic.SalariedEmployee;
import multi_aspect.LongTermTicket;
import multi_inheritance.Copier;
import overlapping.Auction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) throws Exception {

        prepareObjects();

        // Klasa abstr/polimorfizm
        MovieProjection mp1 = new MovieProjectionTwoD("title", "dir", 150);
        MovieProjection mp2 = new MovieProjectionThreeD("title", "dir", 125);

        System.out.println(mp1.getAvailableRooms());
        System.out.println(mp2.getAvailableRooms());

        // Overlapping
        Auction auction = new Auction(
                "title",
                LocalDateTime.now(),
                2,
                new BigDecimal(200),
                new BigDecimal(5),
                new BigDecimal(300)
        );
        auction.bidInAnAuction(new BigDecimal(10));
        auction.buyOutAuction();

        // Wielodziedziczenie
        // Inny przykład użycia, drukowanie zeskanowanego dokumenty (np. funkcja scanAndPrint())
        Copier copier = new Copier("device", "serialNo", "model",
                "producer", "1920x1080", true, 100, true);
        System.out.println(copier.getMaxScanResolution());
        System.out.println(copier.getSpeedOfPrinting());

        // Wieloaspektowe
        LongTermTicket ticket = new LongTermTicket(1, new BigDecimal(20), true, LocalDate.now(),
                LocalDate.of(2019 ,06,30));

        // Dynamic
        SalariedEmployee emp1 = new SalariedEmployee("Łukasz", "Dyduch", "11223344555", 8000);
        HourlyEmployee emp2 = new HourlyEmployee(emp1, 100);
        emp2.setWorkedHours(40);
        System.out.println(emp2.getWorkedHours());

    }

    public static void prepareObjects() {
        CinemaRoom room1 = new CinemaRoom(true, false);
        CinemaRoom room2 = new CinemaRoom(true, false);
        CinemaRoom room3 = new CinemaRoom(true, true);
    }
}
