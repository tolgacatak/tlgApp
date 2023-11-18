package com.example.tlgaskapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="categorys1")
@Data
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    String categoryName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    PostEntity post;


}
