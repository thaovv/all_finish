package com.huce.quiz_app.helper;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;

@Component
public class JwtUtil {
    @Autowired
    private JwtDecoder jwtDecoder;

    public Jwt decodeToken(String token) {
        try {
            return jwtDecoder.decode(token);
        } catch (JwtException e) {
            throw new IllegalArgumentException("Invalid or expired JWT token");
        }
    }

    public String getClaimFromToken(String token, String claimKey) {
        Jwt jwt = decodeToken(token);
        return jwt.getClaimAsString(claimKey);
    }


}
