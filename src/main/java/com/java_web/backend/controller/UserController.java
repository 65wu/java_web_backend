package com.java_web.backend.controller;

import com.java_web.backend.model.User;
import com.java_web.backend.service.UserRegisterService;
import com.java_web.backend.util.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserRegisterService userRegisterService;
    @PostMapping("/register")
    public MyResponse Register(@RequestBody User newUser) {
        return MyResponse.success(userRegisterService.Register(
                newUser.getPassword(),
                newUser.getName(),
                newUser.getEmail())
        );
    }
    @PostMapping("/login")
    public MyResponse Login(@RequestBody User newUser) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        passwordEncoder.matches(newUser.getPassword(), encodedPassword);
        return MyResponse.success();
    }
}
