package com.dhruba.myrestaurant.dtos;

import com.dhruba.myrestaurant.entities.enums.RestaurantTableStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTableDto {

    private Long id;

    private String tableNumber;

    private Integer capacity;

    private Double basePrice;

    private RestaurantTableStatus bookingStatus = RestaurantTableStatus.AVAILABLE;

}
