package com.trip.tripapplication.facade;

public class RouteProcessingExceptions extends Exception {

    public static String ERR_CITY_NOT_ACTIVE = "City is not active";
    public static String ERR_PASSENGER_NOT_ACTIVE = "Passenger is not active";
    public static String ERR_PASSENGER_NOT_LOGGED_IN = "Passenger is not logged in";

    public RouteProcessingExceptions(String message){
        super(message);
    }
}
