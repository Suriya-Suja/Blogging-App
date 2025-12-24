package com.myproject.bloggingapp.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JWTServiceTests {

    JwtService jwtService=new JwtService();

    @Test
    void canCreateJwtFromUserId() {
        var jwt = jwtService.generateToken(100L);

        assertNotNull(jwt);
    }
}
