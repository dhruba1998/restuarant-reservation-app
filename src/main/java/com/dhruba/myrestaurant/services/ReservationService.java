package com.dhruba.myrestaurant.services;

import com.dhruba.myrestaurant.dtos.ReservationRequestDto;
import com.dhruba.myrestaurant.entities.Reservation;
import com.dhruba.myrestaurant.mappers.ReservationRequestDtoMapper;
import com.dhruba.myrestaurant.repos.ReservationRepo;
import com.dhruba.myrestaurant.repos.RestaurantTableRepo;
import com.dhruba.myrestaurant.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepo reservationRepo;

    private final UserRepo userRepo;

    private final RestaurantTableRepo restaurantTableRepo;
    
    private final ReservationRequestDtoMapper reservationRequestDtoMapper;

    public List<Reservation> getAllReservationDetails(){
        return reservationRepo.findAll();
    }

    public Reservation createReservation(ReservationRequestDto reservationRequestDto){
        Reservation reservation = reservationRequestDtoMapper.getReservation(reservationRequestDto);
        return reservationRepo.save(reservation);
    }
}
