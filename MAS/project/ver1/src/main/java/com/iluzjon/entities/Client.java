package com.iluzjon.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Client extends com.iluzjon.entities.Person {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String matchingPassword;

//    public Client(String name, String surname, String username, String email) {
//        this.name = name;
//        this.surname = surname;
//        this.username = username;
//        this.email = email;
//    }
//
//    public Client() {
//
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String toString() {
        return String.format("[ %s, name=%s, surname=%s, username=%s, email_address=%s ]",
                this.getClass().toString().replace(' ', '='),
                this.name,
                this.surname,
                this.username,
                this.email
        );
    }

}