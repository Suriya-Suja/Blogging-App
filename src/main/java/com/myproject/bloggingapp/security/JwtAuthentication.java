package com.myproject.bloggingapp.security;


import com.myproject.bloggingapp.users.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class JwtAuthentication implements Authentication {
    String jwt;
    UserEntity userEntity;

    public JwtAuthentication(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    /**
     * Return the credentials of the {@code Authentication} request.
     * For eg, the password, the Bearer token, the cookie.
     * @return
     */
    @Override
    public String getCredentials() {
        return jwt;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    /**
     * Return the principal of the {@code Authentication} request.
     * The 'principle' is the entity that is being authenticated.
     * In this case it's the User.
     * @return
     */
    @Override
    public UserEntity getPrincipal() {
        return userEntity;
    }

    @Override
    public boolean isAuthenticated() {
        return (userEntity != null);
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public boolean equals(Object another) {
        return false;
    }

    @Override
    public String getName() {
        return "";
    }

}
