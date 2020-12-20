package com.java_web.backend.controller;

import com.java_web.backend.dao.UserRepository;
import com.java_web.backend.model.User;
import com.java_web.backend.util.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public MyResponse Register(@RequestBody User newUser) {
        Date create_date = new Date();
        newUser.setCreated_at(create_date);
        newUser.setStatus(0);
        userRepository.save(newUser);
        return MyResponse.success(newUser);
    }
}
