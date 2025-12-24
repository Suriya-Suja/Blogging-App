package com.myproject.bloggingapp.articles;

import com.myproject.bloggingapp.users.UserEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticlesController {

    @GetMapping
    public String getArticles(){
        return "get all articles";
    }

    @GetMapping("/{id}")
    public String getArticlesById(@PathVariable("id") String id){
        return "get articles by id " + id;
    }

    @PostMapping
    public String createArticle(@AuthenticationPrincipal UserEntity user){
        return "Article created by " + user.getUsername();
    }
}
