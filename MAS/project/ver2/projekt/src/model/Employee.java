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

    //public void sellTicket() TODO ?

}
