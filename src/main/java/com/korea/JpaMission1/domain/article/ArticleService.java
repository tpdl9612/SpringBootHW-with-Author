package com.korea.JpaMission1.domain.article;

import com.korea.JpaMission1.domain.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Article saveDefault(String title, String content, SiteUser user){
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());
        article.setAuthor(user);
        return articleRepository.save(article);
    }

    public Article findArticleById(int id) {
        Optional <Article> article = articleRepository.findById(id);
        return article.orElseThrow(() -> new RuntimeException("찾을 수 없는 값입니다."));
    }
}
