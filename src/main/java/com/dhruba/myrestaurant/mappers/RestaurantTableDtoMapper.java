package com.dhruba.myrestaurant.mappers;

import com.dhruba.myrestaurant.dtos.RestaurantTableDto;
import com.dhruba.myrestaurant.entities.RestaurantTable;
import com.dhruba.myrestaurant.repos.RestaurantTableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantTableDtoMapper {

    @Autowired
    private RestaurantTableRepo restaurantTableRepo;

    public RestaurantTableDto getRestaurantTableDto(RestaurantTable restaurantTable){
        RestaurantTableDto restaurantTableDto = new RestaurantTableDto();
        restaurantTableDto.setId(restaurantTable.getId());
        restaurantTableDto.setTableNumber(restaurantTable.getTableNumber());
        restaurantTableDto.setCapacity(restaurantTable.getCapacity());
        restaurantTableDto.setBasePrice(restaurantTable.getBasePrice());
        restaurantTableDto.setBookingStatus(restaurantTable.getBookingStatus());

        return restaurantTableDto;
    }

}
