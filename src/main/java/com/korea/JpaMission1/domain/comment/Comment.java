package com.korea.JpaMission1.domain.comment;

import com.korea.JpaMission1.domain.article.Article;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @ManyToOne
    Article article;
}
