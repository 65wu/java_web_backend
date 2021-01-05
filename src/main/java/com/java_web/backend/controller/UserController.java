package com.java_web.backend.controller;

import com.java_web.backend.model.dto.UserEditBasicTransfer;
import com.java_web.backend.model.dto.UserEditPasswordTransfer;
import com.java_web.backend.model.dto.UserLoginTransfer;
import com.java_web.backend.model.dto.UserRegisterTransfer;
import com.java_web.backend.service.UserService;
import com.java_web.backend.util.AuthToken;
import com.java_web.backend.util.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public MyResponse Register(@RequestBody @Valid UserRegisterTransfer urt) {
        return userService.Register(
                urt.getPassword(),
                urt.getName(),
                urt.getEmail()
        );
    }
    @PostMapping("/login")
    public MyResponse Login(@RequestBody @Valid UserLoginTransfer user) {
        return userService.Login(
                user.getName(),
                user.getPassword()
        );
    }
    @AuthToken
    @PutMapping("/edit/password")
    public MyResponse EditPassword(@RequestBody @Valid UserEditPasswordTransfer user) {
        return userService.EditPassword(
                user.getUsername(),
                user.getPassword()
        );
    }
    @AuthToken
    @PutMapping("/edit/basic")
    public MyResponse EditBasic(@RequestBody @Valid UserEditBasicTransfer user) {
        return userService.EditBasic(
                user.getUsername(),
                user.getName(),
                user.getEmail()
        );
    }
}
