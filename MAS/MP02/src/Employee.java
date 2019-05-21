import java.util.ArrayList;
import java.util.List;

public class Employee  {

    private String firstName;
    private String lastName;
    private String pesel;

    private List<EventTicket> soldEventTickets = new ArrayList<>();

    public Employee(String firstName, String lastName, String pesel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
    }

    public void addEventTicket(EventTicket ticket) {
        if(!soldEventTickets.contains(ticket)) {
            Employee ticketSeller = ticket.getSeller();

            if(ticketSeller != null) {
                ticketSeller.soldEventTickets.remove(ticket);
            }

            ticket.setSeller(this);
            soldEventTickets.add(ticket);
        }
    }

    public void removeEventTicket(EventTicket ticket) {
        if(soldEventTickets.contains(ticket)) {
           soldEventTickets.remove(ticket);
        }
    }

    public void printSoldTickets() {
        for(EventTicket ticket : soldEventTickets) {
            System.out.println(ticket);
        }
        System.out.println("------------------------------------");
    }

    public String toString() {
        return String.format("[ %s, firstname=%s, lastname=%s, pesel=%s ]", Employee.class.toString(), firstName, lastName, pesel);
    }

}