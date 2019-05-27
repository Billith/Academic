package com.iluzjon.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public abstract class Person {

    @NotNull
    @NotEmpty
    protected String name;

    @NotNull
    @NotEmpty
    protected String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
