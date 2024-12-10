package com.huce.quiz_app.iservices;

import com.huce.quiz_app.entities.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> getUserById(Long id);
}