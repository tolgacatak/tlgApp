package com.example.tlgaskapp.services;

import com.example.tlgaskapp.entities.UserEntity;
import com.example.tlgaskapp.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity saveOneUser(UserEntity newUser) {
        return userRepository.save(newUser);
    }

    public UserEntity getOneUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public UserEntity updateOneUser(Long userId, UserEntity newUser) {
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

    public void deleteId(Long userId) {
        userRepository.deleteById(userId);
    }
}
