package com.example.tlgaskapp.controllers;

import com.example.tlgaskapp.entities.UserEntity;
import com.example.tlgaskapp.repos.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @GetMapping
    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }
    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity newUser){
        return userRepository.save(newUser);
    }
    @GetMapping("/{userId}")
    public UserEntity getOneUser(@PathVariable Long userId){
        return userRepository.findById(userId).orElse(null);
    }
    @PutMapping("/{userId}")
    public UserEntity updateUser(@PathVariable Long userId, @RequestBody UserEntity newUser){
        Optional<UserEntity> user = userRepository.findById(userId);
        if(user.isPresent()){
            UserEntity foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        }
        else{
            return null;
        }
    }
    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userRepository.deleteById(userId);
    }
}
