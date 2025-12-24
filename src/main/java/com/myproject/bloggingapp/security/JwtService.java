package com.myproject.bloggingapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    // TODO: Move the key to a seperate .properties file (don't put it in git)
    private static final String JWT_SECRET = "jwt-secret";
    private Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);

    public String generateToken(Long userId){
        return JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(new Date())
                //.withExpiresAt() // TODO: setup an expiry parameter
                .sign(algorithm);
    }

    public Long getUserIdFromToken(String token){
        var decodedJwt = JWT.decode(token);
        var userID = Long.valueOf(decodedJwt.getSubject());

        return userID;
    }

}
