package com.korea.JpaMission1.domain.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleRepository articleRepository;
    private final ArticleService articleService;

    @GetMapping("/write")
    public String writeForm() {
        return "article_form";
    }

    @PostMapping("/write")
    public String write(@RequestParam("title")String title,
                        @RequestParam("content")String content) {
        if(title.trim().length() ==0){
            title = "제목 없음";
        }
        if(content.trim().length()==0){
            content = "냉무";
        }
        articleService.saveDefault(title, content);

        return "redirect:/";
    }

    @GetMapping("/detail/{articleId}")
    public String detail(@PathVariable("articleId")int id,
                         Model model) {
        Article article = articleService.findArticleById(id);

        model.addAttribute("article", article);

        return "detail";
    }

    @PostMapping("/delete/{articleId}")
    public String delete(@PathVariable("articleId")int articleId) {
        Article article = articleService.findArticleById(articleId);
        articleRepository.delete(article);

        return "redirect:/";
    }

    @GetMapping("/update/{articleId}")
    public String updateForm(@PathVariable ("articleId")int articleId,
                             Model model){
        Article article = articleService.findArticleById(articleId);
        model.addAttribute("article", article);
        return "update_form";
    }
    @PostMapping("/update/{articleId}")
    public String update(@PathVariable("articleId")int articleId,
                         @RequestParam("title")String title,
                         @RequestParam("content")String content){
        Article article = articleService.findArticleById(articleId);
        if(title.trim().length() == 0){
            title = "제목 없음";
        }
        if(content.trim().length()==0){
            content = "냉무";
        }
        article.setTitle(title);
        article.setContent(content);

        articleRepository.save(article);
        return "redirect:/detail/"+article.getId();
    }





}
