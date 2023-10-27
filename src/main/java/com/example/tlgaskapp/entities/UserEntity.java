package com.example.tlgaskapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user1")
@Data
public class UserEntity {
    @Id
    Long id;

    String userName;
    String password;


}
