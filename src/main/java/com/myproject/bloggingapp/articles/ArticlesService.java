package com.myproject.bloggingapp.articles;

import com.myproject.bloggingapp.articles.dtos.CreateArticleRequest;
import com.myproject.bloggingapp.articles.dtos.UpdateArticleRequest;
import com.myproject.bloggingapp.users.UsersRepository;
import com.myproject.bloggingapp.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticlesService {
    @Autowired
    private ArticlesRepository articlesRepository;
    @Autowired
    private UsersRepository usersRepository;

    public Iterable<ArticleEntity> getAllArticles() {
        return articlesRepository.findAll();
    }

    public ArticleEntity getArticleBySlug(String slug) {
        var article = articlesRepository.findBySlug(slug);
        if (article == null) {
            throw new ArticleNotFoundException(slug);
        }
        return article;
    }

    public ArticleEntity createArticle(CreateArticleRequest request, Long authorId) {

        var author = usersRepository.findById(authorId).orElseThrow(() -> new UsersService.UserNotFoundException(authorId));

        return articlesRepository.save(ArticleEntity.builder()
                        .title(request.getTitle())
                        // TODO: create a proper slugification function
                        .slug(request.getTitle().toLowerCase().replaceAll("\\s+", "-")) //
                        .body(request.getBody())
                        .subtitle(request.getSubtitle())
                        .author(author)
                        .build()
        );
    }

    public ArticleEntity updateArticle(UpdateArticleRequest request, Long articleId) {
        var article = articlesRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException(articleId));
        if(request.getTitle() != null){
            article.setTitle(request.getTitle());
            article.setSlug(request.getTitle().toLowerCase().replaceAll("\\s+", "-"));
        }

        if(request.getBody() != null){
            article.setBody(request.getBody());
        }

        if(request.getSubtitle() != null){
            article.setSubtitle(request.getSubtitle());
        }

        return articlesRepository.save(article);
    }

    static class ArticleNotFoundException extends IllegalArgumentException {
        public ArticleNotFoundException(String slug) {
            super("Article with slug " + slug + " not found");
        }

        public ArticleNotFoundException(Long id) {
            super("Article with ID" + id + " not found");
        }
    }
}
