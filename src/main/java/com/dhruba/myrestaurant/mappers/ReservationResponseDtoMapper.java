package com.dhruba.myrestaurant.mappers;

import com.dhruba.myrestaurant.dtos.ReservationResponseDto;
import com.dhruba.myrestaurant.dtos.RestaurantTableDto;
import com.dhruba.myrestaurant.dtos.UserDto;
import com.dhruba.myrestaurant.entities.Reservation;
import com.dhruba.myrestaurant.entities.enums.RestaurantTableStatus;
import com.dhruba.myrestaurant.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationResponseDtoMapper {

    @Autowired
    private UserRepo userRepo;

    private ReservationResponseDto reservationResponseDto;

    public ReservationResponseDto getReservationDto(Reservation reservation){
        ReservationResponseDto reservationDto = new ReservationResponseDto();
        reservationDto.setId(reservation.getId());

        UserDto userDto = new UserDto();
        userDto.setId(reservation.getUser().getId());
        userDto.setName(reservation.getUser().getName());
        userDto.setPhone(reservation.getUser().getPhone());
        userDto.setEmail(reservation.getUser().getEmail());

        reservationDto.setUser(userDto);
        reservationDto.setReservationDateTime(reservation.getReservationDateTime());
        reservationDto.setReservationStatus(reservation.getReservationStatus());
        reservationDto.setReservationMadeAt(reservation.getReservationMadeAt());
        reservationDto.setDepositPaid(reservation.getDepositPaid());

        List<RestaurantTableDto> restaurantTableDtoList = reservation.getRestaurantTables().stream()
                .map(table ->
                    new RestaurantTableDto(table.getId(), table.getTableNumber(), table.getCapacity(), table.getBasePrice(), RestaurantTableStatus.BOOKED)
                ).toList();

        reservationDto.setRestaurantTables(restaurantTableDtoList);
        return reservationDto;
    }

}
