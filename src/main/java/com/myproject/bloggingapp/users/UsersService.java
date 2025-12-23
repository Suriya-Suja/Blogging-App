package com.myproject.bloggingapp.users;

import com.myproject.bloggingapp.users.dtos.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersMapper usersMapper;

    public UserEntity createUser(CreateUserRequest request) {
//        var newUser = UserEntity.builder()
//                .username(request.getUsername())
////                .password(password)   // TODO: encrypt password
//                .email(request.getEmail())
//                .build();

        // TODO: save and encrypt password as well
        var newUser = usersMapper.toEntity(request);

        return usersRepository.save(newUser);
    }

    public UserEntity getUser(String username) {
        return usersRepository.findByUsername(username).orElseThrow(() -> new UsersService.UserNotFoundException(username));
    }

    public UserEntity getUser(Long userId) {
        return usersRepository.findById(userId).orElseThrow(() -> new UsersService.UserNotFoundException(userId));
    }

    public UserEntity loginUser(String username, String password) {
        // TODO: check the password
        return getUser(username);
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
