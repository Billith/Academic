package model;

import java.time.LocalDate;
import java.util.List;

/**
 * The class represents client in the system
 */
public class Client extends Person {

    private LocalDate birthDate;

    /**
     * The constructor
     * @param firstNames
     * @param lastName
     * @param email
     * @param phoneNumber
     * @param address
     * @param birthDate
     */
    public Client(List<String> firstNames, String lastName, String email, String phoneNumber, String address, LocalDate birthDate) {
        this.firstNames = firstNames;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthDate = birthDate;
    }

}
