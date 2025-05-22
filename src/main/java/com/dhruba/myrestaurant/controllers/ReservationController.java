package com.dhruba.myrestaurant.controllers;

import com.dhruba.myrestaurant.dtos.ReservationRequestDto;
import com.dhruba.myrestaurant.dtos.ReservationResponseDto;
import com.dhruba.myrestaurant.entities.Reservation;
import com.dhruba.myrestaurant.mappers.ReservationResponseDtoMapper;
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
    private final ReservationResponseDtoMapper reservationResponseDtoMapper;

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequestDto reservationRequestDto){
        Reservation newReservation = reservationService.createReservation(reservationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationResponseDtoMapper.getReservationDto(newReservation));
    }

    @GetMapping
    public ResponseEntity<?> getAllReservations(){
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getAllReservationDetails());
    }

}
