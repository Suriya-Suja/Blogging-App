package com.myproject.bloggingapp.security;

import com.myproject.bloggingapp.users.UsersService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationManager implements AuthenticationManager {

    private final JwtService jwtService;
    private final UsersService usersService;

    public JwtAuthenticationManager(JwtService jwtService, UsersService usersService) {
        this.jwtService = jwtService;
        this.usersService = usersService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof JwtAuthentication){
            var jwtAuthentication = (JwtAuthentication) authentication;
            var jwt = jwtAuthentication.getCredentials();
            var userId = jwtService.getUserIdFromToken(jwt);
            var userEntity = usersService.getUser(userId);

            jwtAuthentication.userEntity = userEntity;
            jwtAuthentication.setAuthenticated(true);

            return jwtAuthentication;
        }

        throw new IllegalAccessError("Cannot authenticate with non-JWT authentication");
    }
}
