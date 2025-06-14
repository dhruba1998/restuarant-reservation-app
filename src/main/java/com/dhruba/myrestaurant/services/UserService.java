package com.dhruba.myrestaurant.services;

import com.dhruba.myrestaurant.dtos.UserDto;
import com.dhruba.myrestaurant.entities.User;
import com.dhruba.myrestaurant.mappers.CustomModelMapper;
import com.dhruba.myrestaurant.repos.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;

    private CustomModelMapper modelMapper;

    private UserDto userDto;

    @Autowired
    public UserService(UserRepo userRepo, CustomModelMapper modelMapper, UserDto userDto) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.userDto = userDto;
    }

    public UserDto createUser(User user) {
        user.setLoyaltyPoints(0);
        User newUser = userRepo.save(user);
        userDto.setId(newUser.getId());
        userDto.setName(newUser.getName());
        userDto.setEmail(newUser.getEmail());
        userDto.setPhone(newUser.getPhone());
        userDto.setLoyaltyPoints(newUser.getLoyaltyPoints());
        return userDto;
    }

    public List<UserDto> getAllUsers() {
        return userRepo.findAll().stream().map(user -> modelMapper.userDtoMapper(user)).toList();
    }

    public UserDto getUserById(Long userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isPresent()) {
            return modelMapper.userDtoMapper(user.get());
        }
        throw new RuntimeException(String.format("UserID %d is not found", userId));
    }

    public UserDto updateUser(Long userId, User updatedUser) {
        Optional<User> existingUser = userRepo.findById(userId);
        if (existingUser.isPresent()) {
            ModelMapper modelMapper1 = new ModelMapper();
            modelMapper1.getConfiguration().setSkipNullEnabled(true);
            modelMapper1.map(updatedUser, existingUser.get());
            userRepo.save(existingUser.get());
            return modelMapper.userDtoMapper(existingUser.get());
        }
        throw new RuntimeException("User not updated");
    }

    public void deleteUser(Long userId) {
        if(userRepo.findById(userId).isPresent()){
            userRepo.deleteById(userId);
        }
        throw new RuntimeException(String.format("UserID %d is not found",userId));
    }
}
