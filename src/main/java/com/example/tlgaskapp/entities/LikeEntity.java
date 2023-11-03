package com.example.tlgaskapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity

@Table(name="likes")
@Data
public class LikeEntity {
    @Id

    Long id;
    @ManyToOne(fetch = FetchType.LAZY) /*User Objesini Db den hemen çekme, post çekildiğinde user objesi gelmeyecek */
    @JoinColumn(name = "postId", nullable = false) /* userId ile User tablosuna bağlanıyoruz*/
    @OnDelete(action = OnDeleteAction.CASCADE) /* bir user silindiğinde postlar silinir*/
    @JsonIgnore
    PostEntity post;
    @ManyToOne(fetch = FetchType.LAZY) /*User Objesini Db den hemen çekme, post çekildiğinde user objesi gelmeyecek */
    @JoinColumn(name = "userId", nullable = false) /* userId ile User tablosuna bağlanıyoruz*/
    @OnDelete(action = OnDeleteAction.CASCADE) /* bir user silindiğinde postlar silinir*/
    @JsonIgnore
    UserEntity user;

}
