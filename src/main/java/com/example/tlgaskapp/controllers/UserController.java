package com.example.tlgaskapp.controllers;
import com.example.tlgaskapp.entities.UserEntity;
import com.example.tlgaskapp.repos.UserRepository;
import com.example.tlgaskapp.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping
    public List<UserEntity> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity newUser){
        return userService.saveOneUser(newUser);
    }
    @GetMapping("/{userId}")
    public UserEntity getOneUser(@PathVariable Long userId){
        return userService.getOneUser(userId);
    }
    @PutMapping("/{userId}")
    public UserEntity updateUser(@PathVariable Long userId, @RequestBody UserEntity newUser){
        return userService.updateOneUser(userId, newUser);
    }
    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userService.deleteId(userId);
    }

}
