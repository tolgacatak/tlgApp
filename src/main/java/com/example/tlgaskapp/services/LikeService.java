package com.example.tlgaskapp.services;

import com.example.tlgaskapp.DTO.LikeCreateDTO;
import com.example.tlgaskapp.entities.LikeEntity;
import com.example.tlgaskapp.entities.PostEntity;
import com.example.tlgaskapp.entities.UserEntity;
import com.example.tlgaskapp.repos.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class LikeService {
    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<LikeEntity> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<LikeEntity> list;

        if(userId.isPresent() && postId.isPresent()){
            list = likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
        } else if (userId.isPresent()) {
            list = likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            list = likeRepository.findByPostId(postId.get());
        } else{
            list = likeRepository.findAll();
        }
       return list.stream().map(like -> new LikeEntity()).collect(Collectors.toList());

    }

    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }

    public LikeEntity getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public LikeEntity createOneLike(LikeCreateDTO likeCreateDTO) {
        UserEntity user = userService.getOneUserById(likeCreateDTO.getUserId());
        PostEntity post = postService.getOnePostById(likeCreateDTO.getPostId());
        if(user != null && post != null){
            LikeEntity likeToSave = new LikeEntity();
            likeToSave.setId(likeCreateDTO.getId());
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            return likeRepository.save(likeToSave);
        }else{
            return null;
        }
    }
}
