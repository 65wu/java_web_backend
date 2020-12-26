package com.java_web.backend.controller;

import com.java_web.backend.model.User;
import com.java_web.backend.service.UserService;
import com.java_web.backend.util.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

@RestController
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public MyResponse Register(@RequestBody Map<String, Object> payload) {
        class RegisterEvent {
            @NotNull(message = "password required!")
            private final String password;
            @NotNull(message = "name required!")
            private final String name;
            @NotNull(message = "email required!")
            private final String email;
            RegisterEvent(String password, String name, String email) {
                this.password = password;
                this.name = name;
                this.email = email;
            }
            public String getPassword() {
                return password;
            }
            public String getName() {
                return name;
            }
            public String getEmail() {
                return email;
            }
        }
        RegisterEvent registerEvent = new RegisterEvent(
                (String)payload.get("password"),
                (String)payload.get("name"),
                (String)payload.get("email")
        );
        return userService.Register(
                registerEvent.getPassword(),
                registerEvent.getName(),
                registerEvent.getEmail()
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
