package com.myproject.bloggingapp.users;

import com.myproject.bloggingapp.common.ErrorResponse;
import com.myproject.bloggingapp.users.dtos.CreateUserRequest;
import com.myproject.bloggingapp.users.dtos.UserResponse;
import com.myproject.bloggingapp.users.dtos.LoginUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersMapper usersMapper;

    @PostMapping
    public ResponseEntity<UserResponse> signUpUser(@RequestBody CreateUserRequest request){
        var user = usersService.createUser(request);
        URI savedUserUri = URI.create("/users/" + user.getId());

        return ResponseEntity.created(savedUserUri).body(usersMapper.toUserResponse(user));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody LoginUserRequest request){
        var user = usersService.loginUser(request.getUsername(), request.getPassword());

        return ResponseEntity.ok(usersMapper.toUserResponse(user));
    }

    @ExceptionHandler({
            UsersService.UserNotFoundException.class,
    })
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
        String message;
        HttpStatus status;

        if(ex instanceof UsersService.UserNotFoundException){
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
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
