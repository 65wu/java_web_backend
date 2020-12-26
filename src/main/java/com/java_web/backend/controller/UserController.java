package com.java_web.backend.controller;

import com.java_web.backend.model.User;
import com.java_web.backend.model.UserRegisterEvent;
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
    public MyResponse Register(@RequestBody @Valid UserRegisterEvent user) {
        return userService.Register(
                user.getPassword(),
                user.getName(),
                user.getEmail()
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
