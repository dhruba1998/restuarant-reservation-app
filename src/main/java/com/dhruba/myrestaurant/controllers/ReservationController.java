package com.dhruba.myrestaurant.controllers;

import com.dhruba.myrestaurant.dtos.ReservationRequestDto;
import com.dhruba.myrestaurant.services.ReservationService;
import jakarta.validation.Valid;
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
    public ResponseEntity<?> createReservation(@RequestBody @Valid ReservationRequestDto reservationRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.createReservation(reservationRequestDto));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllReservations() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reservationService.getAllReservationDetails());
        } catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
