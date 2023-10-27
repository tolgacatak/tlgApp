package com.example.tlgaskapp.repos;

import com.example.tlgaskapp.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
