package com.dhruba.myrestaurant.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private Integer loyaltyPoints = 0;

}
