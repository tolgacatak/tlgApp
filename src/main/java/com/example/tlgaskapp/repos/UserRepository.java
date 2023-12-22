package com.example.tlgaskapp.repos;

import com.example.tlgaskapp.entities.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {


    UserEntity findByUserName(String userName); //Jwt için
}
