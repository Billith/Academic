package controller;

import model.*;
import view.AddNewMovieWindow;
import view.AddNewReservationWindow;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Class responsible for starting system and GUI
 */
public class CinemaSystem {

    public static void main(String[] args) throws ValidateDataException {
        createDemoObjects();
        //Persistence.restoreSystemObjects();
        //AddNewMovieWindow.startUI();
        AddNewReservationWindow.startUI();
        Persistence.saveSystemObjects();
    }

    /**
     * Creates various objects in the system for the demonstration purposes.
     * @throws ValidateDataException thrown when users input is illegal
     */
    public static void createDemoObjects() throws ValidateDataException {

        Employee emp1 = new Employee(
                Arrays.asList("Krzysztof", "Jacek"),
                "Krawczyk",
                "k.krawczyk@iluzjon.pl",
                1,
                "63060983292",
                "53 517 50 95",
                "ul. Kościerska 19, 03-640 Warszawa"
        );

        Employee emp2 = new Employee(
                Arrays.asList("Malina"),
                "Wiśniewska",
                "m.wisniewska@iluzjon.pl",
                2,
                "52040564901",
                "88 492 70 15",
                "ul. Pietrusińskiego Jana 35, 61-418 Warszawa"
        );

        Room room1 = new Room(1, 9, true, RoomType.TWO_D);
        try {
            Seat.seatFabric(1, 1, room1);
            Seat.seatFabric(2, 1, room1);
            Seat.seatFabric(3, 1, room1);
            Seat.seatFabric(4, 2, room1);
            Seat.seatFabric(5, 2, room1);
            Seat.seatFabric(6, 2, room1);
            Seat.seatFabric(7, 3, room1);
            Seat.seatFabric(8, 3, room1);
            Seat.seatFabric(9, 3, room1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Room room2 = new Room(2, 6, true, RoomType.TWO_D);
        try {
            Seat.seatFabric(1, 1, room2);
            Seat.seatFabric(2, 1, room2);
            Seat.seatFabric(3, 1, room2);
            Seat.seatFabric(4, 2, room2);
            Seat.seatFabric(5, 2, room2);
            Seat.seatFabric(6, 2, room2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Room room3 = new Room(3, 3, true, RoomType.THREE_D);
        try {
            Seat.seatFabric(1, 1, room3);
            Seat.seatFabric(2, 1, room3);
            Seat.seatFabric(3, 1, room3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Room room4 = new Room(4, 3, true, RoomType.TWO_D);
        try {
            Seat.seatFabric(1, 1, room3);
            Seat.seatFabric(2, 1, room3);
            Seat.seatFabric(3, 1, room3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Room room5 = new Room(5, 3, true, RoomType.TWO_D);
        try {
            Seat.seatFabric(1, 1, room3);
            Seat.seatFabric(2, 1, room3);
            Seat.seatFabric(3, 1, room3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Room room6 = new Room(6, 3, true, RoomType.THREE_D);
        try {
            Seat.seatFabric(1, 1, room3);
            Seat.seatFabric(2, 1, room3);
            Seat.seatFabric(3, 1, room3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Movie movie1 = new Movie(
                "The Green Mile",
                "Frank Darabont",
                "USA",
                2000,
                "Emerytowany strażnik więzienny opowiada przyjaciółce o niezwykłym mężczyźnie, którego skazano na śmierć za zabójstwo dwóch 9-letnich dziewczynek.",
                188,
                16,
                Arrays.asList("Dramat")
        );

        Movie movie2 = new Movie(
                "The Shawshank Redemption",
                "Frank Darabont",
                "USA",
                1995,
                "Adaptacja opowiadania Stephena Kinga. Niesłusznie skazany na dożywocie bankier, stara się przetrwać w brutalnym, więziennym świecie.",
                142,
                16,
                Arrays.asList("Dramat")
        );

        Movie movie3 = new Movie(
                "Forrest Gump",
                "Robert Zemeckis",
                "USA",
                1994,
                "Historia życia Forresta, chłopca o niskim ilorazie inteligencji z niedowładem kończyn, który staje się miliarderem i bohaterem wojny w Wietnamie.",
                142,
                16,
                Arrays.asList("Dramat", "Komedia")
        );

        RoomReservation reservation1 = new RoomReservation(
                LocalDateTime.now().plusDays(1).plusHours(7),
                LocalDateTime.now().plusDays(1).plusHours(9),
                room1,
                new MovieProjection(RoomType.TWO_D, BigDecimal.valueOf(22), movie1)
        );

        RoomReservation reservation2 = new RoomReservation(
                LocalDateTime.now().plusDays(1).plusHours(14),
                LocalDateTime.now().plusDays(1).plusHours(16),
                room1,
                new MovieProjection(RoomType.TWO_D, BigDecimal.valueOf(22), movie2)
        );

        RoomReservation reservation3 = new RoomReservation(
                LocalDateTime.now().plusDays(4).plusHours(14),
                LocalDateTime.now().plusDays(4).plusHours(16),
                room5,
                new MovieProjection(RoomType.TWO_D, BigDecimal.valueOf(22), movie3)
        );

        RoomReservation reservation4 = new RoomReservation(
                LocalDateTime.now().plusDays(6).plusHours(10),
                LocalDateTime.now().plusDays(6).plusHours(13),
                room4,
                new MovieProjection(RoomType.TWO_D, BigDecimal.valueOf(28.50), movie3)
        );

        RoomReservation reservation5 = new RoomReservation(
                LocalDateTime.now().plusDays(3).plusHours(6),
                LocalDateTime.now().plusDays(3).plusHours(8),
                room1,
                new MovieProjection(RoomType.TWO_D, BigDecimal.valueOf(28.50), movie2)
        );

        RoomReservation reservation6 = new RoomReservation(
                LocalDateTime.now().plusDays(5).plusHours(3),
                LocalDateTime.now().plusDays(5).plusHours(5),
                room3,
                new MovieProjection(RoomType.THREE_D, BigDecimal.valueOf(33.25), movie1)
        );

        RoomReservation reservation7 = new RoomReservation(
                LocalDateTime.now().plusDays(6).plusHours(1),
                LocalDateTime.now().plusDays(6).plusHours(3),
                room2,
                new MovieProjection(RoomType.TWO_D, BigDecimal.valueOf(24.99), movie2)
        );

        RoomReservation reservation8 = new RoomReservation(
                LocalDateTime.now().plusDays(2).plusHours(4),
                LocalDateTime.now().plusDays(2).plusHours(6),
                room3,
                new MovieProjection(RoomType.THREE_D, BigDecimal.valueOf(30.99), movie3)
        );

        RoomReservation reservation9 = new RoomReservation(
                LocalDateTime.now().plusDays(4).plusHours(5),
                LocalDateTime.now().plusDays(4).plusHours(7),
                room2,
                new MovieProjection(RoomType.TWO_D, BigDecimal.valueOf(24.99), movie1)
        );

    }

}
