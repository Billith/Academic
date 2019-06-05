package controller;

import model.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Persistence.restoreSystemObjects();

        Employee emp = new Employee(new String[]{"Łukasz"}, "Dyduch", "lukas.dyduch@cienema.com", 1, "1111111111", "777111222", "ul. Warszawska 1, Warszawa");
        Ticket ticket = new OneTimeTicket(false, TicketType.NORMAL);
        Discount discount = new Discount("środy z Iluzjon", "Promocja aktywna tylko w środy", 5);

        ticket.addSeller(emp);
        ticket.addDiscount(discount);

        ticket.showLinks("soldTicket", System.out);
        ticket.showLinks("appliedDiscount", System.out);
        emp.showLinks("seller", System.out);
        discount.showLinks("ticketsWithDiscount", System.out);

        Persistence.saveSystemObjects();
    }
}
