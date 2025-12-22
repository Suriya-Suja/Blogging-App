package com.myproject.bloggingapp.users;

import com.myproject.bloggingapp.users.dtos.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public UserEntity createUser(CreateUserRequest request) {
        var newUser = UserEntity.builder()
                .username(request.getUsername())
//                .password(password)   // TODO: encrypt password
                .email(request.getEmail())
                .build();

        return usersRepository.save(newUser);
    }

    public UserEntity getUser(String username) {
        return usersRepository.findByUsername(username).orElseThrow(() -> new UsersService.UserNotFoundException(username));
    }

    public UserEntity getUser(Long userId) {
        return usersRepository.findById(userId).orElseThrow(() -> new UsersService.UserNotFoundException(userId));
    }

    public UserEntity loginUser(String username, String password) {
        var user = usersRepository.findByUsername(username).orElseThrow(() -> new UsersService.UserNotFoundException(username));
        // TODO: check the password
        return user;
    }

    public static class UserNotFoundException extends IllegalArgumentException {
        public UserNotFoundException(String username) {
            super("User with name " + username + " not found");
        }

        public UserNotFoundException(Long userId) {
            super("User with ID " + userId + " not found");
        }
    }
}
