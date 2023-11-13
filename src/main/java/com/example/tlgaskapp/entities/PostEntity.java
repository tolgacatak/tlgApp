package com.example.tlgaskapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Data

@Entity
@Table(name = "posts")
public class PostEntity {
    @Id
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    UserEntity user;

    String title;

    String text;


}
