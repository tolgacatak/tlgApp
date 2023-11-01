package com.example.tlgaskapp.services;

import com.example.tlgaskapp.entities.PostEntity;
import com.example.tlgaskapp.entities.UserEntity;
import com.example.tlgaskapp.repos.PostRepository;
import com.example.tlgaskapp.DTO.PostCreateDTO;
import com.example.tlgaskapp.DTO.PostUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserService userService;
    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public void deleteOnePostById(Long deleteId) {
        postRepository.deleteById(deleteId);
    }

    public List<PostEntity> getAllPosts() {
        return postRepository.findAll();
    }

    public PostEntity getOneUser(Long userId) {
        return postRepository.findById(userId).orElse(null);
    }

    public PostEntity getOnePostByID(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public PostEntity createOnePost(PostCreateDTO newPostDTO) {
        UserEntity user=userService.getOneUser(newPostDTO.getUserId()); //Validation, böyle bir user var mı için
        if(user == null)
            return null;
        PostEntity toSave = new PostEntity();
        toSave.setId(newPostDTO.getId());
        toSave.setText(newPostDTO.getText());
        toSave.setTitle(newPostDTO.getTitle());
        toSave.setUser(user);

        return postRepository.save(toSave);
    }

    public PostEntity updateOnePostById(Long postId, PostUpdateDTO postUpdateDTO) {
        Optional<PostEntity> post = postRepository.findById(postId); //id ile post var mı kontrolü
        if(post.isPresent()){
            PostEntity toUpdate = post.get();
            toUpdate.setText(postUpdateDTO.getText());
            toUpdate.setTitle(postUpdateDTO.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }
}
