package com.dhruba.myrestaurant.services;

import com.dhruba.myrestaurant.entities.User;
import com.dhruba.myrestaurant.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long userId){
        return userRepo.findById(userId).get();
    }

    public void updateUser(Long userId,User user){
        User oldUser = userRepo.findById(userId).get();
        if(oldUser!=null){
            oldUser.setName(user.getName());
            oldUser.setEmail(user.getEmail());
            oldUser.setPhone(user.getPhone());
            userRepo.save(oldUser);
        }

    }

    public void deleteUser(Long userId){
        userRepo.deleteById(userId);
    }
}
