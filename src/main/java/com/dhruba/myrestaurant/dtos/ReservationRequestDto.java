package com.dhruba.myrestaurant.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDto {

    @NotNull
    private Long userId;

    @NotNull
    @Size(min = 1,max = 5)
    private List<String> restaurantTables;

    @NotNull
    @Future
    private LocalDateTime reservationDateTime;
}
