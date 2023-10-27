package com.example.tlgaskapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="likes")
public class LikeEntity {
    @Id
    Long id;
    Long postId;
    Long userId;

}
