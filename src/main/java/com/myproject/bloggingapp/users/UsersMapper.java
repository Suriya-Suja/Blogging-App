package com.myproject.bloggingapp.users;

import com.myproject.bloggingapp.users.dtos.CreateUserRequest;
import com.myproject.bloggingapp.users.dtos.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UserEntity toEntity(CreateUserRequest createUserRequest);
    UserResponse toUserResponse(UserEntity user);
}
