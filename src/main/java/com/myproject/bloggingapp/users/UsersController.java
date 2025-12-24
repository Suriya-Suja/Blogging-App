package com.myproject.bloggingapp.users;

import com.myproject.bloggingapp.common.ErrorResponse;
import com.myproject.bloggingapp.security.JwtService;
import com.myproject.bloggingapp.users.dtos.CreateUserRequest;
import com.myproject.bloggingapp.users.dtos.UserResponse;
import com.myproject.bloggingapp.users.dtos.LoginUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final UsersService usersService;
    private final UsersMapper usersMapper;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<UserResponse> signUpUser(@RequestBody CreateUserRequest request){
        var user = usersService.createUser(request);
        URI savedUserUri = URI.create("/users/" + user.getId());
        var userResponse = usersMapper.toUserResponse(user);
        userResponse.setToken(
                jwtService.generateToken(user.getId())
        );

        return ResponseEntity.created(savedUserUri).body(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody LoginUserRequest request){
        var user = usersService.loginUser(request.getUsername(), request.getPassword());
        var userResponse = usersMapper.toUserResponse(user);
        userResponse.setToken(
                jwtService.generateToken(user.getId())
        );

        return ResponseEntity.ok(userResponse);
    }

    @ExceptionHandler({
            UsersService.UserNotFoundException.class,
            UsersService.InvalidCredentialsException.class
    })
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
        String message;
        HttpStatus status;

        if(ex instanceof UsersService.UserNotFoundException){
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        }
        if(ex instanceof UsersService.InvalidCredentialsException){
            message = ex.getMessage();
            status = HttpStatus.UNAUTHORIZED;
        } else {
            message = "something went wrong";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .message(message)
                .build();
        return ResponseEntity.status(status).body(errorResponse);
    }
}
