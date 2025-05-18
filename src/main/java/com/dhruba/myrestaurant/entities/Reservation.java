package com.dhruba.myrestaurant.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservation_date_time", nullable = false)
    @Future(message = "Reservation must be in future")
    private LocalDateTime reservationDateTime;

    @Column(name = "reservation_made_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime reservationMadeAt;

    @Column(name = "reservation_status")
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @Column(name = "deposit_paid")
    private Boolean depositPaid = false;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "id")
    private List<RestaurantTable> restaurantTables = new ArrayList<>();
}

enum ReservationStatus {
    CONFIRMED, CANCELLED, COMPLETED
}
