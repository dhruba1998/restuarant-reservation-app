package com.dhruba.myrestaurant.controllers;

import com.dhruba.myrestaurant.entities.RestaurantTable;
import com.dhruba.myrestaurant.entities.enums.RestaurantTableStatus;
import com.dhruba.myrestaurant.services.RestaurantTableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tables")
public class RestaurantTableController {

    private final RestaurantTableService restaurantTableService;

    @PostMapping
    public ResponseEntity<?> registerTable(@RequestBody @Valid RestaurantTable table){
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantTableService.createTable(table));
    }

    @GetMapping
    public ResponseEntity<?> getAllTableDetails(){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantTableService.getAllTableDetails());
    }

    @GetMapping("/{bookingStatus}")
    public ResponseEntity<?> getTableByBookingStatus(@PathVariable RestaurantTableStatus bookingStatus){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantTableService.getTableByBookingStatus(bookingStatus));
    }

    @PutMapping("/{tableId}/price/{newPrice}")
    public ResponseEntity<?> updateTablePrice(@PathVariable Long tableId, @PathVariable Double newPrice){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantTableService.updateTablePrice(tableId,newPrice));
    }
}
