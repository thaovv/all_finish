package com.huce.quiz_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    @Autowired
    private JwtDecoder jwtDecoder;

//    public Jwt decodeToken(String token) {
//        try {
//            return jwtDecoder.decode(token);
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Invalid or expired JWT token", e);
//        }
//    }
}