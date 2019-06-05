package controller;

import model.Employee;
import model.OneTimeTicket;
import model.Ticket;
import model.TicketType;
import model.oplusplus.ObjectPlus;

public class Main {
    public static void main(String[] args) throws Exception {
        Persistence.restoreSystemObjects();

        Employee emp = new Employee(new String[]{"Łukasz"}, "Dyduch", "lukas.dyduch@cienema.com", 1, "1111111111", "777111222", "ul. Warszawska 1, Warszawa");
        Ticket ticket = new OneTimeTicket(false, TicketType.NORMAL);
        ticket.addSeller(emp);

        ticket.showLinks("soldTicket", System.out);
        emp.showLinks("seller", System.out);

        //System.out.println(ObjectPlus.getClassExtent(Employee.class).get(0));

        Persistence.saveSystemObjects();
    }
}
