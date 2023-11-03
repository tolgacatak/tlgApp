package com.example.tlgaskapp.services;

import com.example.tlgaskapp.DTO.CommentCreateDTO;
import com.example.tlgaskapp.DTO.CommentUpdateDto;
import com.example.tlgaskapp.entities.CommentEntity;
import com.example.tlgaskapp.entities.PostEntity;
import com.example.tlgaskapp.entities.UserEntity;
import com.example.tlgaskapp.repos.CommentRepository;
import com.example.tlgaskapp.repos.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(PostService postService, UserService userService, CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }


    public void deleteOnePostById(Long deleteId) {
        commentRepository.deleteById(deleteId);
    }

    public CommentEntity getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public CommentEntity createOneComment(CommentCreateDTO commentCreateDTO) {
        UserEntity user = userService.getOneUserById(commentCreateDTO.getUserId());
        PostEntity post = postService.getOnePostById(commentCreateDTO.getPostId());

        if(user != null && post != null){
            CommentEntity commentToSave = new CommentEntity();
            commentToSave.setId(commentCreateDTO.getId());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(commentCreateDTO.getText());
            return commentRepository.save(commentToSave);
        }
        else
            return null;
    }

    public CommentEntity updateOneCommentById(Long commentId, CommentUpdateDto commentUpdateDto) {
        Optional<CommentEntity> comment = commentRepository.findById(commentId);
        if(comment.isPresent()){
            CommentEntity commentToUpdate = comment.get();
            commentToUpdate.setText(commentUpdateDto.getText());
            return commentRepository.save(commentToUpdate);
        }
        return null;
    }

    public List<CommentEntity> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }else if(userId.isPresent()){
            return commentRepository.findByUserId(userId.get());
        }else if(postId.isPresent()){
            return commentRepository.findByPostId(postId.get());
        }else{
            return commentRepository.findAll();
        }
    }
}
