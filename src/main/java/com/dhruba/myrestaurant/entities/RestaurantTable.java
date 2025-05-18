package com.dhruba.myrestaurant.entities;

import com.dhruba.myrestaurant.entities.enums.RestaurantTableStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurant_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "table_number",unique = true)
    @NotBlank(message = "Table number is required")
    private String tableNumber;

    @Column(name = "table_capacity")
    @Min(value = 2,message = "Minimum capacity is 2")
    @Max(value = 12,message = "Maximum capacity is 12")
    private Integer capacity;

    @Column(name = "base_price")
    @Positive(message = "Price must be positive")
    private Double basePrice;

    @Column(name = "booking_status")
    @Enumerated(EnumType.STRING)
    private RestaurantTableStatus bookingStatus = RestaurantTableStatus.AVAILABLE;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private Reservation reservation;

}

