package com.udea.graphvirtual.service;

import com.udea.graphvirtual.entity.Flight;
import com.udea.graphvirtual.entity.Reservation;
import com.udea.graphvirtual.repository.FlightRepository;
import com.udea.graphvirtual.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final FlightRepository flightRepository;

    public ReservationService(ReservationRepository reservationRepository, FlightRepository flightRepository) {
        this.reservationRepository = reservationRepository;
        this.flightRepository = flightRepository;
    }

    public Reservation reserveFlight(Long flightId, String passengerName,String seatNumber) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(()-> new RuntimeException("Flight Not Found"));
        if(flight.getSeatsAvailable() >0){
            flight.setSeatsAvailable(flight.getSeatsAvailable() - 1);
            //Crear la reserva

            Reservation reservation = new Reservation();
            reservation.setPassengerName(passengerName);
            reservation.setSeatNumber(seatNumber);
            reservation.setFlight(flight);
            //Generar el codigo de la reserva
            String reservationCode= generateReservationCode(flight.getFlightNumber());
            reservation.setReservationCode(reservationCode);
            return reservationRepository.save(reservation);
        } else {
            throw new RuntimeException("Not seats available");
        }
    }


    //Metodo para generar un codigo unico de reserva
    private String generateReservationCode(String flightNumber){
        //AV007-XXXXXXXX
        return flightNumber +"-"+ UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }

}

