package com.java_web.backend.controller;

import com.java_web.backend.model.dto.User.EditBasicTransfer;
import com.java_web.backend.model.dto.User.EditPasswordTransfer;
import com.java_web.backend.model.dto.User.LoginTransfer;
import com.java_web.backend.model.dto.User.RegisterTransfer;
import com.java_web.backend.service.UserService;
import com.java_web.backend.util.AuthToken;
import com.java_web.backend.util.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public MyResponse Register(@RequestBody @Valid RegisterTransfer urt) {
        return userService.Register(
                urt.getPassword(),
                urt.getUsername(),
                urt.getEmail(),
                urt.getNickname()
        );
    }
    @PostMapping("/login")
    public MyResponse Login(@RequestBody @Valid LoginTransfer user) {
        return userService.Login(
                user.getUsername(),
                user.getPassword()
        );
    }
    @AuthToken
    @PutMapping("/edit/password")
    public MyResponse EditPassword(@RequestHeader("Token") String token, @RequestBody @Valid EditPasswordTransfer user) {
        return userService.EditPassword(
                token,
                user.getPassword()
        );
    }
    @AuthToken
    @PutMapping("/edit/basic")
    public MyResponse EditBasic(@RequestHeader("Token") String token, @RequestBody @Valid EditBasicTransfer user) {
        return userService.EditBasic(
                token,
                user.getNickname(),
                user.getEmail()
        );
    }
    @AuthToken
    @GetMapping("/info")
    public MyResponse GetUserInfo(@RequestHeader("Token") String token) {
        return userService.GetUserInfo(
                token
        );
    }
}
