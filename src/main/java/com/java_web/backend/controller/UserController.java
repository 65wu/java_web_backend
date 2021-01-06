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
import java.net.http.HttpHeaders;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public MyResponse Register(@RequestBody @Valid UserRegisterTransfer urt) {
        return userService.Register(
                urt.getPassword(),
                urt.getUsername(),
                urt.getEmail(),
                urt.getNickname()
        );
    }
    @PostMapping("/login")
    public MyResponse Login(@RequestBody @Valid UserLoginTransfer user) {
        return userService.Login(
                user.getUsername(),
                user.getPassword()
        );
    }
    @AuthToken
    @PutMapping("/edit/password")
    public MyResponse EditPassword(@RequestHeader("Token") String token, @RequestBody @Valid UserEditPasswordTransfer user) {
        return userService.EditPassword(
                token,
                user.getPassword()
        );
    }
    @AuthToken
    @PutMapping("/edit/basic")
    public MyResponse EditBasic(@RequestHeader("Token") String token, @RequestBody @Valid UserEditBasicTransfer user) {
        return userService.EditBasic(
                token,
                user.getUsername(),
                user.getEmail()
        );
    }
}
