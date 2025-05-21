package com.dhruba.myrestaurant.controllers;

import com.dhruba.myrestaurant.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public void createReservation(){

    }

    @GetMapping
    public ResponseEntity<?> getAllReservations(){
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getAllReservationDetails());
    }

}
