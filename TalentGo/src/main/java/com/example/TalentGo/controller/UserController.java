package com.example.TalentGo.controller;

import com.example.TalentGo.entity.User;
import com.example.TalentGo.model.ApiResponse;
import com.example.TalentGo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/post")
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
        User saved = userService.createUser(user);
        return ResponseEntity.ok(new ApiResponse<>(true, "User created successfully", saved));
    }
}

