package controller;

import model.*;

import java.math.BigDecimal;
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

        ticket.addSeller(emp);
        ticket.addDiscount(discount);

        ticket.showLinks("soldTicket", System.out);
        ticket.showLinks("appliedDiscount", System.out);
        emp.showLinks("seller", System.out);
        discount.showLinks("ticketsWithDiscount", System.out);
        movie.showLinks("displayedMovie", System.out);
        movieProjection.showLinks("filmToDisplay", System.out);

        Persistence.saveSystemObjects();
    }
}
