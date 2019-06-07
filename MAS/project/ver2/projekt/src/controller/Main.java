package controller;

import model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        Persistence.restoreSystemObjects();

        Employee emp = new Employee(Arrays.asList("Łukasz"), "Dyduch", "lukas.dyduch@cienema.com",
                1, "1111111111", "777111222", "ul. Warszawska 1, Warszawa");
        Ticket ticket = new OneTimeTicket(false, TicketType.NORMAL);
        Discount discount = new Discount("środy z Iluzjon", "Promocja aktywna tylko w środy", 5);
        Movie movie = new Movie("title", "director", "PL", 2018,
                "desc", 120, 14, Arrays.asList("comedy", "romance"));
        MovieProjection movieProjection = new MovieProjection(RoomType.TWO_D, new BigDecimal(20), movie);
        Room room = new Room(1, 30, true, RoomType.TWO_D);
        RoomReservation reservation = new RoomReservation(
                LocalDateTime.of(2019, 11, 1, 15, 20),
                LocalDateTime.of(2019, 11, 1, 17,40),
                room,
                movieProjection
        );

        ticket.addSeller(emp);
        ticket.addDiscount(discount);

        ticket.showLinks("soldTicket", System.out);
        ticket.showLinks("appliedDiscount", System.out);
        emp.showLinks("seller", System.out);
        discount.showLinks("ticketsWithDiscount", System.out);
        movie.showLinks("displayedMovie", System.out);
        movieProjection.showLinks("filmToDisplay", System.out);
        room.showLinks("reservationRoom", System.out);
        movieProjection.showLinks("reservationEvent", System.out);
        reservation.showLinks("reservedRoom", System.out);
        reservation.showLinks("heldEvent", System.out);

        Persistence.saveSystemObjects();
    }
}
