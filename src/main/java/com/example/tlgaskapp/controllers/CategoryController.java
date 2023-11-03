package com.example.tlgaskapp.controllers;

import com.example.tlgaskapp.DTO.CategoryCreateDTO;
import com.example.tlgaskapp.DTO.CategoryUpdateDTo;
import com.example.tlgaskapp.DTO.PostUpdateDTO;
import com.example.tlgaskapp.entities.CategoryEntity;
import com.example.tlgaskapp.entities.LikeEntity;
import com.example.tlgaskapp.entities.PostEntity;
import com.example.tlgaskapp.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorys")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    public List<CategoryEntity> getAllCategory(@RequestParam Optional<Long> postId){
        return categoryService.getAllCategoriesWithParam(postId);
    }
    @GetMapping("/{categoryId}")
    public CategoryEntity getOneCategory(@PathVariable Long categoryId){
        return categoryService.getOneCategoryById(categoryId);
    }
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId){
        categoryService.deleteOneCategoryById(categoryId);
    }
    @PostMapping
    public CategoryEntity createOneCategory(@RequestBody CategoryCreateDTO categoryCreateDTO){
        return categoryService.createOneCategory(categoryCreateDTO);
    }
    @PutMapping("/{categoryId}")
    public CategoryEntity updateOneCategory(@PathVariable Long categoryId, @RequestBody CategoryUpdateDTo categoryUpdateDTo){
        return categoryService.updateOneCategoryById(categoryId,categoryUpdateDTo);
    }

}
