package model;

import model.oplusplus.ObjectPlus;

public abstract class Person extends ObjectPlus {

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected String address;

    public String toString() {
        return String.format("[ %s, fname=%s, lname=%s, email=%s ]", this.getClass().getSimpleName(), firstName, lastName, email);
    }

}
