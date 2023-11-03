package com.example.tlgaskapp.controllers;

import com.example.tlgaskapp.DTO.CommentCreateDTO;
import com.example.tlgaskapp.DTO.CommentUpdateDto;
import com.example.tlgaskapp.entities.CommentEntity;
import com.example.tlgaskapp.entities.CommentEntity;
import com.example.tlgaskapp.entities.PostEntity;
import com.example.tlgaskapp.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @GetMapping
    public List<CommentEntity> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return commentService.getAllCommentsWithParam(userId, postId);
    }

    @GetMapping("/{commentId}")
    public CommentEntity getOneComment(@PathVariable Long commentId){
        return commentService.getOneCommentById(commentId);
    }
    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long deleteId){
        commentService.deleteOnePostById(deleteId);
    }
    @PostMapping
    public CommentEntity createOneComment(@RequestBody CommentCreateDTO commentCreateDTO){
        return commentService.createOneComment(commentCreateDTO);
    }
    @PutMapping("/{commentId}")
    public CommentEntity updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateDto commentUpdateDto){
        return commentService.updateOneCommentById(commentId, commentUpdateDto);
    }


}