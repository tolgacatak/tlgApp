package com.example.tlgaskapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="posts")
public class PostEntity {
    @Id
    Long id;
    Long userId;
    String title;
    @Lob
    @Column(columnDefinition = "text")
    String text;
}
