package com.trip.tripapplication.scheduler;

import com.trip.tripapplication.domain.Mail;
import com.trip.tripapplication.domain.Route;
import com.trip.tripapplication.service.EmailService;
import com.trip.tripapplication.service.RouteDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final RouteDbService routeDbService;
    private final EmailService emailService;

    public List<Route> routeList() {
        List<Route> routeList = routeDbService.getAllRoutes().stream()
                .filter(route -> route.getDateOfTrip().isAfter(LocalDateTime.now()) &&
                        route.getDateOfTrip().isBefore(LocalDateTime.now().plusDays(1)))
                .collect(Collectors.toList());

        return Optional.ofNullable(routeList)
                .orElse(Collections.emptyList());
    }

    public String getStringDateOfTrip(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return date.format(formatter);
    }

    @Scheduled(cron = "0 0 10 * * * ")
    public void sentInformationToCustomer(){
        for(Route trip:routeList()){
            String message = "Hello " + trip.getPassengers().getFirstName() + "" +
                    " \n You ordered trip for " + getStringDateOfTrip(trip.getDateOfTrip());
            emailService.send(
                    new Mail(
                            trip.getPassengers().getMail(),
                            "Trip reminder",
                            message
                    )
            );
        }
    }


}
