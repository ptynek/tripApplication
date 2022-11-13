package com.trip.tripapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mail {

    private final String mailTo;
    private final String subject;
    private final String message;
}
