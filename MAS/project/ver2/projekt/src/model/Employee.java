package model;

public class Employee extends Person {

    private int employeeId;
    private String pesel;


    public Employee(String firstName, String lastName, String email, int employeeId, String pesel, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.employeeId = employeeId;
        this.pesel = pesel;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

}
