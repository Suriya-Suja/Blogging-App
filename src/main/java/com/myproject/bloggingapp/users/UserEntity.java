package com.myproject.bloggingapp.users;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "users")
@Getter
@Setter
@Builder
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    @NonNull
    private String username;

    @Column(name = "email", nullable = false)
    @NonNull
    private String email;

    @Column(name = "bio", nullable = true)
    @Nullable
    private String bio;

    @Column(name = "image", nullable = true)
    @Nullable
    private String image;
}
