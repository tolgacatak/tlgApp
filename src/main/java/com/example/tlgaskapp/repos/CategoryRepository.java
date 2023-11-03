package com.example.tlgaskapp.repos;

import com.example.tlgaskapp.entities.CategoryEntity;
import com.example.tlgaskapp.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    List<CategoryEntity> findByPostId(Long postId);
}
