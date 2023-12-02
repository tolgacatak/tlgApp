package com.example.tlgaskapp.DTO;
//Post işlemi yapıldığında user bilgilerini de elde etmek için
import com.example.tlgaskapp.entities.LikeEntity;
import com.example.tlgaskapp.entities.PostEntity;
import lombok.Data;
import java.util.List;

@Data
public class PostDTO {
    Long id;
    Long userId;
    String userName;
    String text;
    String title;
    List<LikeEntity> postLikes;
    public PostDTO(PostEntity entity, List<LikeEntity> likes){ //Constructor based mapper
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.postLikes=likes;
    }
}
