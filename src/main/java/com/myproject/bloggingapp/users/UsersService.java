package com.myproject.bloggingapp.users;

import com.myproject.bloggingapp.security.JwtService;
import com.myproject.bloggingapp.users.dtos.CreateUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final PasswordEncoder passwordEncoder;


    public UserEntity createUser(CreateUserRequest request) {
//        var newUser = UserEntity.builder()
//                .username(request.getUsername())
////                .password(password)   // TODO: encrypt password
//                .email(request.getEmail())
//                .build();

        var newUser = usersMapper.toEntity(request);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return usersRepository.save(newUser);
    }

    public UserEntity getUser(String username) {
        return usersRepository.findByUsername(username).orElseThrow(() -> new UsersService.UserNotFoundException(username));
    }

    public UserEntity getUser(Long userId) {
        return usersRepository.findById(userId).orElseThrow(() -> new UsersService.UserNotFoundException(userId));
    }

    public UserEntity loginUser(String username, String password) {
        var user = getUser(username);
        var passMatch = passwordEncoder.matches(password, user.getPassword());
        if(!passMatch) {
            throw new InvalidCredentialsException();
        }

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

    public static class InvalidCredentialsException extends IllegalArgumentException {
        public InvalidCredentialsException() {
            super("Invalid username or password");
        }
    }
}
