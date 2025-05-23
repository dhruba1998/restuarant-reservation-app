package com.dhruba.myrestaurant.services;

import com.dhruba.myrestaurant.dtos.ReservationRequestDto;
import com.dhruba.myrestaurant.dtos.ReservationResponseDto;
import com.dhruba.myrestaurant.entities.Reservation;
import com.dhruba.myrestaurant.entities.User;
import com.dhruba.myrestaurant.mappers.ReservationRequestDtoMapper;
import com.dhruba.myrestaurant.mappers.ReservationResponseDtoMapper;
import com.dhruba.myrestaurant.repos.ReservationRepo;
import com.dhruba.myrestaurant.repos.RestaurantTableRepo;
import com.dhruba.myrestaurant.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepo reservationRepo;

    private final UserRepo userRepo;

    private final RestaurantTableRepo restaurantTableRepo;
    
    private final ReservationRequestDtoMapper reservationRequestDtoMapper;

    private final ReservationResponseDtoMapper reservationResponseDtoMapper;

    public List<ReservationResponseDto> getAllReservationDetails(){
        List<Reservation> reservationList = reservationRepo.findAll();
        if(reservationList.isEmpty()){
            throw new RuntimeException("No reservation found");
        }
        return reservationList.stream().map(reservationResponseDtoMapper::getReservationDto).toList();
    }

    public ReservationResponseDto createReservation(ReservationRequestDto reservationRequestDto){
        Optional<User> user = userRepo.findById(reservationRequestDto.getUserId());
        if(user.isEmpty()){
            throw new RuntimeException(String.format("User with user ID %d is not found",reservationRequestDto.getUserId()));
        }
        List<String> tableList = reservationRequestDto.getRestaurantTables().stream()
                .filter(tableID -> restaurantTableRepo.findByTableNumber(tableID).isPresent()).toList();
        if(tableList.isEmpty()){
            throw new RuntimeException("Given table IDs not found");
        }
        Reservation reservation = reservationRequestDtoMapper.getReservation(reservationRequestDto);
        Reservation newReservation = reservationRepo.save(reservation);
        return reservationResponseDtoMapper.getReservationDto(newReservation);
    }
}
