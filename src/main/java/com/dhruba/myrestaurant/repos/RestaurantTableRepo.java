package com.dhruba.myrestaurant.repos;

import com.dhruba.myrestaurant.entities.RestaurantTable;
import com.dhruba.myrestaurant.entities.enums.RestaurantTableStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantTableRepo extends JpaRepository<RestaurantTable,Long> {

    Optional<List<RestaurantTable>> findByBookingStatus(RestaurantTableStatus bookingStatus);

}
