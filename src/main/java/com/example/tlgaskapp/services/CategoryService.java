package com.example.tlgaskapp.services;

import com.example.tlgaskapp.DTO.CategoryCreateDTO;
import com.example.tlgaskapp.DTO.CategoryUpdateDTo;
import com.example.tlgaskapp.DTO.PostUpdateDTO;
import com.example.tlgaskapp.entities.CategoryEntity;
import com.example.tlgaskapp.entities.PostEntity;
import com.example.tlgaskapp.repos.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private PostService postService;

    public CategoryService(CategoryRepository categoryRepository, PostService postService) {
        this.categoryRepository = categoryRepository;
        this.postService = postService;
    }

    public List<CategoryEntity> getAllCategoriesWithParam(Optional<Long> postId) {
        if(postId.isPresent())
            return categoryRepository.findByPostId(postId.get());
        else{
            return categoryRepository.findAll();
        }
    }

    public CategoryEntity getOneCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public void deleteOneCategoryById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public CategoryEntity createOneCategory(CategoryCreateDTO categoryCreateDTO) {
        PostEntity post = postService.getOnePostById(categoryCreateDTO.getPostId());
        if(post != null){
            CategoryEntity toSaveCategory = new CategoryEntity();
            toSaveCategory.setId(categoryCreateDTO.getId());
            toSaveCategory.setCategoryName(categoryCreateDTO.getCategoryName());
            toSaveCategory.setPost(post);
            return categoryRepository.save(toSaveCategory);
        }else{
            return null;
        }
    }

    public CategoryEntity updateOneCategoryById(Long categoryId, CategoryUpdateDTo categoryUpdateDTo) {
        Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
        if(category.isPresent()){
            CategoryEntity toUpdateCategory = category.get();
            toUpdateCategory.setCategoryName(categoryUpdateDTo.getCategoryName());
            return categoryRepository.save(toUpdateCategory);

        }
        else{
            return null;
        }
    }
}
