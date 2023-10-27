package com.example.tlgaskapp.repos;

import com.example.tlgaskapp.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

}
