package com.udea.graphvirtual.resolver;

import com.udea.graphvirtual.entity.Reservation;
import com.udea.graphvirtual.service.ReservationService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ReservationController {

    private final ReservationService reservationService;


    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @MutationMapping
    public Reservation reserveFlight(@Argument Long flighId,@Argument String passengerName,@Argument String seatNumber) {
        return reservationService.reserveFlight(flighId,passengerName,seatNumber);
    }


}


