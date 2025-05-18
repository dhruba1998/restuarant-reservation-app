package com.dhruba.myrestaurant.services;

import com.dhruba.myrestaurant.entities.RestaurantTable;
import com.dhruba.myrestaurant.entities.enums.RestaurantTableStatus;
import com.dhruba.myrestaurant.repos.RestaurantTableRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantTableService {

    private final RestaurantTableRepo restaurantTableRepo;

    public RestaurantTable createTable(RestaurantTable table){
        return restaurantTableRepo.save(table);
    }

    public List<RestaurantTable> getAllTableDetails(){
        return restaurantTableRepo.findAll();
    }

    public List<RestaurantTable> getTableByBookingStatus(RestaurantTableStatus status){
        return restaurantTableRepo.findByBookingStatus(status).get();
    }

    public RestaurantTable updateTablePrice(Long tableId, Double tablePrice){
        RestaurantTable table = restaurantTableRepo.findById(tableId).get();
        table.setBasePrice(tablePrice);
        return restaurantTableRepo.save(table);
    }
}
