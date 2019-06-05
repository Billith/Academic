package model;

import java.util.ArrayList;
import java.util.List;

public class Employee extends Person {

    private int employeeId;
    private String pesel;

    private List<Ticket> soldTickets = new ArrayList<>();

    public Employee(String[] firstNames, String lastName, String email, int employeeId,
                    String pesel, String phoneNumber, String address) {
        this.firstNames = firstNames;
        this.lastName = lastName;
        this.email = email;
        this.employeeId = employeeId;
        this.pesel = pesel;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public void addSoldTicket(Ticket ticket) {
        if(!soldTickets.contains(ticket)) {
            soldTickets.add(ticket);
        }
    }

    public void removeSoldTicket(Ticket ticket) throws Exception {
        if(soldTickets.contains(ticket)) {
            this.soldTickets.remove(ticket);
        } else {
            throw new Exception("[!] This employee didn't sold this ticket");
        }
    }

    //public void sellTicket() TODO

}
