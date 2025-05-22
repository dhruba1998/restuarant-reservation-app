package com.dhruba.myrestaurant.mappers;

import com.dhruba.myrestaurant.dtos.ReservationRequestDto;
import com.dhruba.myrestaurant.entities.Reservation;
import com.dhruba.myrestaurant.entities.enums.RestaurantTableStatus;
import com.dhruba.myrestaurant.repos.RestaurantTableRepo;
import com.dhruba.myrestaurant.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationRequestDtoMapper {

    @Autowired
    private RestaurantTableRepo restaurantTableRepo;

    @Autowired
    private UserRepo userRepo;

    public Reservation getReservation(ReservationRequestDto reservationRequestDto){
        Reservation reservation = new Reservation();
        reservation.setUser(userRepo.findById(reservationRequestDto.getUserId()).get());
        reservation.setRestaurantTables(
                reservationRequestDto.getRestaurantTables().stream().
                        map(tableId -> {
                            restaurantTableRepo.findById(tableId).get().setBookingStatus(RestaurantTableStatus.BOOKED);
                            return restaurantTableRepo.findById(tableId).get();
                        }).toList()
        );
        reservation.setReservationDateTime(reservationRequestDto.getReservationDateTime());
        return reservation;
    }
}
