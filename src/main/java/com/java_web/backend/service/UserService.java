package com.java_web.backend.service;

import com.java_web.backend.dao.UserRepository;
import com.java_web.backend.model.User;
import com.java_web.backend.util.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public MyResponse Register(
            String password,
            String name,
            String email
    ) {
        User user = new User();
        Date date = new Date();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password.trim());

        user.setPassword(encodedPassword);
        user.setName(name);
        user.setEmail(email);
        user.setCreated_at(date);
        user.setUpdated_at(date);
        user.setStatus(0);

        userRepository.save(user);
        return new MyResponse(
                1,
                "注册成功",
                user
        );
    }

    public MyResponse Login(
            Integer id,
            String rawPassword
    ) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(passwordEncoder.matches(rawPassword, user.getPassword()))
                return new MyResponse(
                        1,
                        "登录成功"
                );
            else
                return new MyResponse(
                        0,
                        "用户名或密码错误"
                );
        } else return new MyResponse(
                0,
                "用户名不存在"
        );
    }
}