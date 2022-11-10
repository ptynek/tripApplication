package com.trip.tripapplication.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PassengersDto {

    private long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String mail;
    private boolean active;
    private boolean loggedIn;

    public boolean isActive() {
        return active;
    }


}
