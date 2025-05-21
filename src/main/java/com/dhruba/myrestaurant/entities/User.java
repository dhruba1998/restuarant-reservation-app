package com.dhruba.myrestaurant.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank
    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    @NotBlank
    @Pattern(regexp = "^[1-9]\\d{9}$", message = "Invalid phone number")
    private String phone;

    private Integer loyaltyPoints = 0;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value = "user_ref")
    private List<Reservation> reservations = new ArrayList<>();

}
