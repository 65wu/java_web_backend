package com.java_web.backend.service;

import com.java_web.backend.dao.UserRepository;
import com.java_web.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserRegisterService {
    @Autowired
    private UserRepository userRepository;
    public User Register(
            String password,
            String name,
            String email
    ) {
        User user = new User();
        Date date = new Date();

        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setCreated_at(date);
        user.setUpdated_at(date);
        user.setStatus(0);
        userRepository.save(user);
        return user;
    }
}
