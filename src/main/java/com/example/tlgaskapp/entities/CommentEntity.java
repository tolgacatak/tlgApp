package com.example.tlgaskapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name="comments")
@Data
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    String text;


}

