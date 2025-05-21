package com.dhruba.myrestaurant.repos;

import com.dhruba.myrestaurant.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation,Long> {

}
