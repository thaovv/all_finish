package com.huce.quiz_app.iservices;

import jakarta.servlet.http.HttpServletRequest;

public interface IJwtService {
    Long getUserId(HttpServletRequest request);
    boolean isValidToken(String token);
    String getToken(HttpServletRequest request);
}
