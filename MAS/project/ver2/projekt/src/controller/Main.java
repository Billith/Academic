package controller;

import model.*;
import model.oplusplus.ObjectPlus;
import view.AddNewMovieWindow;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        Persistence.restoreSystemObjects();

        Employee emp = new Employee(Arrays.asList("Łukasz"), "Dyduch", "lukas.dyduch@cienema.com",
                1, "1111111111", "777111222", "ul. Warszawska 1, Warszawa");

        Discount discount = new Discount("środy z Iluzjon", "Promocja aktywna tylko w środy", 5);
        Movie movie = new Movie("movie1", "director", "PL", 2018,
                "desc", 120, 14, Arrays.asList("comedy", "romance"));
        MovieProjection movieProjection = new MovieProjection(RoomType.TWO_D, new BigDecimal(20), movie);
        Room room1 = new Room(1, 30, true, RoomType.TWO_D);
        Room room2 = new Room(2, 30, true, RoomType.THREE_D);
        Room room3 = new Room(3, 30, true, RoomType.TWO_D);
        RoomReservation reservation = new RoomReservation(
                LocalDateTime.of(2019, 06, 10, 15, 20),
                LocalDateTime.of(2019, 06, 10, 17,40),
                room1,
                movieProjection
        );
        RoomReservation reservation1 = new RoomReservation(
                LocalDateTime.of(2019, 06, 11, 15, 20),
                LocalDateTime.of(2019, 06, 11, 18,40),
                room2,
                movieProjection
        );
        Seat seat = Seat.seatFabric(1, 1, room1);
        Ticket ticket = new OneTimeTicket(false, TicketType.NORMAL, reservation, seat);

        ticket.setSeller(emp);
        ticket.addDiscount(discount);

        ticket.showLinks("soldTicket", System.out);
        ticket.showLinks("appliedDiscount", System.out);
        emp.showLinks("seller", System.out);
        discount.showLinks("ticketsWithDiscount", System.out);
        movie.showLinks("displayedMovie", System.out);
        movieProjection.showLinks("filmToDisplay", System.out);
        room1.showLinks("reservationRoom", System.out);
        movieProjection.showLinks("reservationEvent", System.out);
        reservation.showLinks("reservedRoom", System.out);
        reservation.showLinks("heldEvent", System.out);

        AddNewMovieWindow.startUI();
        System.out.println(ObjectPlus.getClassExtent(Room.class));

        Persistence.saveSystemObjects();
    }
}
