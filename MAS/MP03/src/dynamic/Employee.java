package dynamic;

import o_plus_plus.ObjectPlusPlus;

public abstract class Employee extends ObjectPlusPlus {

    protected String firstName;
    protected String lastName;
    protected String pesel;

    public Employee(String firstName, String lastName, String pesel) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
    }

}
