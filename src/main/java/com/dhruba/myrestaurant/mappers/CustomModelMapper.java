package com.dhruba.myrestaurant.mappers;

import com.dhruba.myrestaurant.dtos.UserDto;
import com.dhruba.myrestaurant.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomModelMapper {

    public UserDto userDtoMapper(User user) {
        ModelMapper userDtoModelMapper = new ModelMapper();
        userDtoModelMapper.createTypeMap(User.class, UserDto.class);
        return userDtoModelMapper.map(user, UserDto.class);
    }

}
