package com.example.tlgaskapp.controllers;

import com.example.tlgaskapp.DTO.LikeCreateDTO;
import com.example.tlgaskapp.entities.LikeEntity;
import com.example.tlgaskapp.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }
    @GetMapping
    public List<LikeEntity> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return likeService.getAllLikesWithParam(userId,postId);
    }
    @DeleteMapping("/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId){
        likeService.deleteOneLikeById(likeId);
    }
    @GetMapping("/{likeId}")
    public LikeEntity getOneLike(@PathVariable Long likeId){
        return likeService.getOneLikeById(likeId);
    }
    @PostMapping("/{likeId}")
    public LikeEntity createOneLike(@RequestBody LikeCreateDTO likeCreateDTO){
        return likeService.createOneLike(likeCreateDTO);
    }



}
