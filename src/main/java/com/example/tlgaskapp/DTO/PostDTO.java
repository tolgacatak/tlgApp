package com.example.tlgaskapp.DTO;
//Post işlemi yapıldığında user bilgilerini de elde etmek için
import com.example.tlgaskapp.entities.PostEntity;
import lombok.Data;

@Data
public class PostDTO {
    Long id;
    Long userId;
    String userName;
    String text;
    String title;

    public PostDTO(PostEntity entity){ //Constructor based mapper
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.title = entity.getTitle();
        this.text = entity.getText();
    }
}
