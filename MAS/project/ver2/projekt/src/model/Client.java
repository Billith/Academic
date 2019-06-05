package model;

import java.time.LocalDate;

public class Client extends Person {

    private LocalDate birthDate;

    public Client(String firstName, String lastName, String email, String phoneNumber, String address, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthDate = birthDate;
    }

}
