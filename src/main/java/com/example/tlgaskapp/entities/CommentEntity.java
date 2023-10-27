package com.example.tlgaskapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="comments")
@Data
public class CommentEntity {
    @Id
    Long Id;
    Long postId;
    Long userId;
    @Lob
    @Column(columnDefinition = "text")
    String text;
}
