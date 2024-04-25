package com.korea.JpaMission1;

import com.korea.JpaMission1.domain.article.Article;
import com.korea.JpaMission1.domain.article.ArticleRepository;
import com.korea.JpaMission1.domain.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ArticleRepository articleRepository;
//    private final CommentRepository commentRepository;
    private final ArticleService articleService;


    @RequestMapping("/")
    public String main(Model model) {
        List<Article> articleList = articleRepository.findAll();
        if(articleList.isEmpty()){
            articleService.saveDefault("New Title", "");
            return "redirect:/";

        }
        model.addAttribute("articleList", articleList);
        return "main";
    }
}
