package com.dhruba.myrestaurant.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDto {

    private Long userId;

    private List<Long> restaurantTables;

    private LocalDateTime reservationDateTime;
}
