package com.dhruba.myrestaurant.controllers;

import com.dhruba.myrestaurant.dtos.RestaurantTableDto;
import com.dhruba.myrestaurant.entities.RestaurantTable;
import com.dhruba.myrestaurant.entities.enums.RestaurantTableStatus;
import com.dhruba.myrestaurant.services.RestaurantTableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tables")
public class RestaurantTableController {

    private final RestaurantTableService restaurantTableService;

    @PostMapping
    public ResponseEntity<?> registerTable(@RequestBody @Valid RestaurantTable table) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurantTableService.createTable(table));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Table is not created");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllTableDetails() {
        try {
            List<RestaurantTableDto> tableList = restaurantTableService.getAllTableDetails();
            if(!tableList.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(tableList);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No table details found");
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while fetching table details");
        }
    }

    @GetMapping("/{bookingStatus}")
    public ResponseEntity<?> getTableByBookingStatus(@PathVariable RestaurantTableStatus bookingStatus) {
        try {
            List<RestaurantTableDto> tableList = restaurantTableService.getTableByBookingStatus(bookingStatus);
            if(!tableList.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(tableList);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No table details found");
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while fetching table details");
        }
    }

    @PutMapping("/{tableNumber}/price/{newPrice}")
    public ResponseEntity<?> updateTablePrice(@PathVariable String tableNumber, @PathVariable Double newPrice) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurantTableService.updateTablePrice(tableNumber,
                    newPrice));
        } catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
