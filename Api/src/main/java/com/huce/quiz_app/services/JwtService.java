package com.huce.quiz_app.services;

import com.huce.quiz_app.helper.JwtUtil;
import com.huce.quiz_app.iservices.IJwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

@Service
public class JwtService implements IJwtService {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Long getUserId(HttpServletRequest request) {
        try {
            return Long.parseLong(jwtUtil.getClaimFromToken(getToken(request), "userId"));
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to extract userId");
        }
    }

    @Override
    public String getToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid or missing Authorization header");
        }

        String token = authorizationHeader.substring(7); // Bỏ tiền tố "Bearer "

        if (!isValidToken(token)) {
            throw new IllegalArgumentException("Invalid or expired token");
        }

        return token;
    }


    @Override
    public boolean isValidToken(String token) {
        try {
            jwtUtil.decodeToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}