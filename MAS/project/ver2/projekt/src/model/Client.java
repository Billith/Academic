package model;

import java.time.LocalDate;

public class Client extends Person {

    private LocalDate birthDate;

    public Client(String[] firstNames, String lastName, String email, String phoneNumber, String address, LocalDate birthDate) {
        this.firstNames = firstNames;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthDate = birthDate;
    }

}
