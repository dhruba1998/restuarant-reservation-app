package com.dhruba.myrestaurant.controllers;

import com.dhruba.myrestaurant.dtos.UserDto;
import com.dhruba.myrestaurant.entities.User;
import com.dhruba.myrestaurant.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody @Valid User user) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already registered / phone format is incorrect");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserDto> users = userService.getAllUsers();
            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body("No user found");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(users);
            }
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while fetching users");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
        } catch(RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody User user) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.updateUser(userId, user));
        } catch(RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(String.format("UserID %d is deleted successfully", userId));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
