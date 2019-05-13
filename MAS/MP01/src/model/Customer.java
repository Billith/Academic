package model;

import java.util.List;

public class Customer extends Person {

    private static int customerCounter = 0;

    String login;
    String emailAddress;
    String phoneNumber;

    public Customer(String firstName, String lastName, String login, String emailAddress) throws Exception {
        if (login == null || emailAddress == null) {
            throw new Exception("Invalid parameters");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.emailAddress = emailAddress;
    }

    public Customer(String firstName, String lastName, String login, String emailAddress, String phoneNumber) throws Exception {
        this(firstName, lastName, login, emailAddress);
        this.phoneNumber = phoneNumber;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPhoneNumber() throws Exception {
        if (phoneNumber == null) {
            throw new Exception("Customer didn't provide phone number");
        }
        return this.phoneNumber;
    }

    public void changePhoneNumber(String phoneNumber) throws Exception {
        if (phoneNumber == null) {
            throw new Exception("Customer didn't provide phone number");
        }
        this.phoneNumber = phoneNumber;
    }

    public static Customer getCustomerByLogin(String login) {
        List<ObjectPlus> listOfCustomers = ObjectPlus.getClassExtent(Customer.class);
        for (Object customerFromExtent : listOfCustomers) {
            if (((Customer) customerFromExtent).getLogin().equals(login)) {
                return (Customer) customerFromExtent;
            }
        }
        return null;
    }

    public String toString() {
        if (this.phoneNumber == null) {
            return String.format("[ %s, firstname=%s, lastname=%s, login=%s, email_address=%s ]",
                    this.getClass().toString().replace(' ', '='),
                    this.firstName,
                    this.lastName,
                    this.login,
                    this.emailAddress
            );
        }
        return String.format("[ %s, firstname=%s, lastname=%s, login=%s, email_address=%s, phone=%s ]",
                this.getClass().toString().replace(' ', '='),
                this.firstName,
                this.lastName,
                this.login,
                this.emailAddress,
                this.phoneNumber
        );
    }

}
