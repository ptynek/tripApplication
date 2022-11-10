package com.trip.tripapplication.domain;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "PASSENGERS")
public class Passengers {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @NotNull
    @Column(name = "MAIL")
    private String mail;

    @NotNull
    @Column(name = "ACTIVE")
    private boolean active;

    @NotNull
    @Column(name = "LOGGED_IN")
    private boolean loggedIn;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Passengers(long id, String firstName, String lastName, String phoneNumber, String mail, boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.active = active;
    }

}
