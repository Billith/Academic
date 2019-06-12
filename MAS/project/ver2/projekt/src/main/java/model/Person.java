package model;

import model.oplusplus.ObjectPlusPlus;

import java.util.List;

public abstract class Person extends ObjectPlusPlus {

    protected List<String> firstNames;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected String address;

    public String toString() {
        return String.format("[ %s, fname=%s, lname=%s, email=%s ]", this.getClass().getSimpleName(), firstNames.get(0), lastName, email);
    }

}
