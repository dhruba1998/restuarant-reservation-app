package com.dhruba.myrestaurant.dtos;

import com.dhruba.myrestaurant.entities.RestaurantTable;
import com.dhruba.myrestaurant.entities.User;
import com.dhruba.myrestaurant.entities.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDto {

    private Long id;

    private LocalDateTime reservationDateTime;

    private LocalDateTime reservationMadeAt;

    private ReservationStatus reservationStatus = ReservationStatus.PENDING;

    private Boolean depositPaid = false;

    private UserDto user;

    private List<RestaurantTableDto> restaurantTables = new ArrayList<>();
}
