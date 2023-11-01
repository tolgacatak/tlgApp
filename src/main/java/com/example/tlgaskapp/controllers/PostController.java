package com.example.tlgaskapp.controllers;

import com.example.tlgaskapp.entities.PostEntity;
import com.example.tlgaskapp.entities.UserEntity;
import com.example.tlgaskapp.requests.PostCreateRequest;
import com.example.tlgaskapp.requests.PostUpdateRequest;
import com.example.tlgaskapp.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostEntity> getAllPosts(){
        return postService.getAllPosts();
    }
    @GetMapping("/{userId}")
    public PostEntity getOneUser(@PathVariable Long userId){
        return postService.getOneUser(userId);
    }
    @GetMapping("/{postId}")
    public PostEntity getOnePost(@PathVariable Long postId){
        return postService.getOnePostByID(postId);
    }
    @PostMapping
    public PostEntity createOnePost(@RequestBody PostCreateRequest newpPostRequest){
        return postService.createOnePost(newpPostRequest);
    }
    @PutMapping("/{postId}")
    public PostEntity updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdateRequest){
        return postService.updateOnePostById(postId,postUpdateRequest);
    }
    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long deleteId){
        postService.deleteOnePostById(deleteId);
    }
}
