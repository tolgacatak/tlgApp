package com.example.tlgaskapp.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "users")

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id auto inc.
    Long id;

    String userName;
    String password;

    public UserEntity(Long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public UserEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
