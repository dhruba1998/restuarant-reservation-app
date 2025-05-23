package com.dhruba.myrestaurant.services;

import com.dhruba.myrestaurant.dtos.RestaurantTableDto;
import com.dhruba.myrestaurant.entities.RestaurantTable;
import com.dhruba.myrestaurant.entities.enums.RestaurantTableStatus;
import com.dhruba.myrestaurant.mappers.RestaurantTableDtoMapper;
import com.dhruba.myrestaurant.repos.RestaurantTableRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantTableService {

    private final RestaurantTableRepo restaurantTableRepo;

    private final RestaurantTableDtoMapper restaurantTableDtoMapper;

    public RestaurantTableDto createTable(RestaurantTable table) {
        RestaurantTable newTable = restaurantTableRepo.save(table);
        return restaurantTableDtoMapper.getRestaurantTableDto(newTable);
    }

    public List<RestaurantTableDto> getAllTableDetails() {
        return restaurantTableRepo.findAll().stream().map(restaurantTableDtoMapper::getRestaurantTableDto).toList();
    }

    public List<RestaurantTableDto> getTableByBookingStatus(RestaurantTableStatus status) {
        Optional<List<RestaurantTable>> tableList = restaurantTableRepo.findByBookingStatus(status);
        return tableList.map(restaurantTables -> restaurantTables.stream().map(restaurantTableDtoMapper::getRestaurantTableDto).toList()).orElseGet(List::of);
    }

    public RestaurantTableDto updateTablePrice(String tableNumber, Double tablePrice) {
        Optional<RestaurantTable> table = restaurantTableRepo.findByTableNumber(tableNumber);
        if (table.isPresent()) {
            table.get().setBasePrice(tablePrice);
            restaurantTableRepo.save(table.get());
            return restaurantTableDtoMapper.getRestaurantTableDto(table.get());
        }
        throw new RuntimeException(String.format("Table with table ID %s is not found", tableNumber));
    }
}
