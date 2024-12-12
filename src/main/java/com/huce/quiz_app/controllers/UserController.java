package com.huce.quiz_app.controllers;

import com.huce.quiz_app.dto.Request.ApiResponse;
import com.huce.quiz_app.dto.Request.AuthenticationRequest;
import com.huce.quiz_app.dto.Request.UserCreationRequest;
import com.huce.quiz_app.dto.Response.AuthenticationResponse;
import com.huce.quiz_app.entities.User;
import com.huce.quiz_app.services.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

//    @PostMapping("/register")
//    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){
//        ApiResponse<User> apiResponse = new ApiResponse<>();
//        apiResponse.setResult(userService.createUser(request));
//        return apiResponse;
//    }
@PostMapping("/register")
ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
    User user = userService.createUser(request);
    return ApiResponse.<User>builder()
            .result(user)
            .message("Đăng ký thành công!")
            .build();
}
    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result = userService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .message("Đăng nhập thành công!")
                .build();
    }
//    @GetMapping("/all")
//    List<User> getAllUsers(){
//        return userService.getAllUsers();
//    }


}