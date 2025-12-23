package com.myproject.bloggingapp.users.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.Setter;
import org.springframework.lang.NonNull;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
public class CreateUserRequest {
    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String email;
}
