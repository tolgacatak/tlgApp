package com.example.tlgaskapp.repos;

import com.example.tlgaskapp.entities.CommentEntity;
import com.example.tlgaskapp.entities.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    List<LikeEntity> findByUserIdAndPostId(Long userId, Long postId);
    List<LikeEntity> findByUserId(Long userId);
    List<LikeEntity> findByPostId(Long postId);
}
