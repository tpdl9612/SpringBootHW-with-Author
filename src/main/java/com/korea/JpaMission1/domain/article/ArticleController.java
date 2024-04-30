package com.korea.JpaMission1.domain.article;

import com.korea.JpaMission1.domain.user.SiteUser;
import com.korea.JpaMission1.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleRepository articleRepository;
    private final ArticleService articleService;
    private final UserService userService;
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/write")
    public String writeForm() {
        return "article_form";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/write")
    public String write(@RequestParam("title")String title,
                        @RequestParam("content")String content,
                        Principal principal) {
        SiteUser siteUser = userService.getUser(principal.getName());
        if(title.trim().length() ==0){
            title = "제목 없음";
        }
        if(content.trim().length()==0){
            content = "냉무";
        }
        articleService.saveDefault(title, content, siteUser);

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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/update/{articleId}")
    public String updateForm(@PathVariable ("articleId")int articleId,
                             Principal principal, Model model){
        Article article = articleService.findArticleById(articleId);
        if(!article.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }
        model.addAttribute("article", article);
        return "update_form";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update/{articleId}")
    public String update(@PathVariable("articleId")int articleId,
                         @RequestParam("title")String title,
                         @RequestParam("content")String content,
                         Principal principal){
        Article article = articleService.findArticleById(articleId);
        if(!article.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }
        if(title.trim().length() == 0){
            title = "제목 없음";
        }
        if(content.trim().length()==0){
            content = "냉무";
        }
        this.articleService.update(article, title, content);

        return "redirect:/detail/"+article.getId();
    }





}
