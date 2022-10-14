package com.trip.tripapplication.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePassengerDto {

    private long id;
    private String firstName;
    private String lastname;
    private String phoneNumber;
    private String mail;
}
