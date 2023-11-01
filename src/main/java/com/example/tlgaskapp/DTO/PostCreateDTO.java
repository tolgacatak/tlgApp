package com.example.tlgaskapp.DTO;

import lombok.Data;

@Data
public class PostCreateDTO {
    Long id;
    String text;
    String title;
    Long userId;
}
