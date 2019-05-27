package model;

public class Employee extends Person {

    private int employeeId;
    private String pesel;
    private String phoneNumber;

    public Employee(String firstName, String lastName, String email, int employeeId, String pesel, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.employeeId = employeeId;
        this.pesel = pesel;
        this.phoneNumber = phoneNumber;
    }

}
