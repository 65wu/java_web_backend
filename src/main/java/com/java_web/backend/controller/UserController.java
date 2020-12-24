package com.java_web.backend.controller;

import com.java_web.backend.model.User;
import com.java_web.backend.service.UserService;
import com.java_web.backend.util.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public MyResponse Register(@RequestBody @Valid User newUser) {
        return userService.Register(
                newUser.getPassword(),
                newUser.getName(),
                newUser.getEmail()
        );
    }
    @PostMapping("/login")
    public MyResponse Login(@RequestBody User user) {
        return userService.Login(
                user.getUsername(),
                user.getPassword()
        );
    }
}
