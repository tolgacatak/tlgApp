package com.example.tlgaskapp.repos;

import com.example.tlgaskapp.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByUserIdAndPostId(Long userId, Long postId);
    List<CommentEntity> findByUserId(Long userId);
    List<CommentEntity> findByPostId(Long postId);

}
