package com.example.tlgaskapp.DTO;

import lombok.Data;

@Data
public class CategoryCreateDTO {
    Long id;
    Long postId;
    String categoryName;
}
