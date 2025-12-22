package com.myproject.bloggingapp.articles;

import com.myproject.bloggingapp.users.UserEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity(name = "articles")
@Getter
@Setter
@Builder
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private String title;

    @NonNull
    @Column(unique = true)
    private String slug;

    @Nullable
    private String subtitle;

    @NonNull
    private String body;

    @CreatedDate
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "authorId", nullable = false)
    private UserEntity author;

    // TODO: add tags
}
