package com.java_web.backend.controller;

import com.java_web.backend.dao.UserRepository;
import com.java_web.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<User> Register(@RequestBody User newUser) {
        userRepository.save(newUser);
        return ResponseEntity.accepted().body(newUser);
    }
}
