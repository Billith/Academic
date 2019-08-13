package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The class represents employee in the system
 */
public class Employee extends Person {

    private int employeeId;
    private String pesel;

    /**
     * List of sold ticket by the employee
     */
    private List<Ticket> soldTickets = new ArrayList<>();

    /**
     * The constructor
     * @param firstNames
     * @param lastName
     * @param email
     * @param employeeId unique ID assigned by cinema staff
     * @param pesel
     * @param phoneNumber
     * @param address
     */
    public Employee(List<String> firstNames, String lastName, String email, int employeeId,
                    String pesel, String phoneNumber, String address) {
        this.firstNames = firstNames;
        this.lastName = lastName;
        this.email = email;
        this.employeeId = employeeId;
        this.pesel = pesel;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

}
