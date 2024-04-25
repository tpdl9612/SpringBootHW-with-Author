package com.korea.JpaMission1.domain.article;

import com.korea.JpaMission1.domain.comment.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "article")
    List<Comment> commentList = new ArrayList<>();
}
