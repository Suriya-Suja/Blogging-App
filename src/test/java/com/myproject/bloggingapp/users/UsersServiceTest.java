package com.myproject.bloggingapp.users;

import com.myproject.bloggingapp.users.dtos.CreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;

@SpringBootTest
@ActiveProfiles("test")
public class UsersServiceTest {

    @Autowired
    UsersService usersService;

    @Test
    void can_create_users(){

        var user = usersService.createUser(new CreateUserRequest(
                "suriya",
                "pass1234",
                "suriya@gmail.com"
        ));

        Assertions.assertNotNull(user);
        Assertions.assertEquals("suriya",user.getUsername());

    }
}
