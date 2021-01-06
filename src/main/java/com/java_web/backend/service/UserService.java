package com.java_web.backend.service;

import com.java_web.backend.dao.UserManager;
import com.java_web.backend.dao.UserRepository;
import com.java_web.backend.model.po.User;
import com.java_web.backend.util.Md5TokenGenerator;
import com.java_web.backend.util.MyResponse;
import com.java_web.backend.util.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@CrossOrigin(origins = "*")
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserManager userManager;
    @Autowired
    private TokenHelper tokenHelper;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public MyResponse Register(
            String password,
            String username,
            String email,
            String nickname
    ) {
        Integer id = userManager.findIdByUsername(username);
        if (id != null) {
            return new MyResponse(
                    1,
                    "用户名被占用，注册失败"
            );
        }

        // user类初始化
        User user = new User();
        // 时间初始化
        Date date = new Date();
        // 密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password.trim());
        //写入数据库
        user.setPassword(encodedPassword);
        user.setUsername(username);
        user.setEmail(email);
        user.setNickname(nickname);
        user.setCreated_at(date);
        user.setUpdated_at(date);
        user.setStatus(0);

        userRepository.save(user);
        return new MyResponse(
                1,
                "注册成功"
        );
    }

    public MyResponse Login(
            String username,
            String rawPassword
    ) {
        Integer id = userManager.findIdByUsername(username);
        if(id != null) {
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                // 准备比对密码
                User user = optionalUser.get();
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String password = user.getPassword();

                if (passwordEncoder.matches(rawPassword, password)) {
                    ValueOperations<String, String> valueStr = redisTemplate.opsForValue();
                    // 先查看token是否存在
                    String token = valueStr.get(username);
                    if (token != null) {
                        if(tokenHelper.renew(username, token)) {
                            Map<String, Object> result = new HashMap<>();
                            result.put("token", token);
                            return new MyResponse(
                                    1,
                                    "登录成功",
                                    result
                            );
                        }
                    }
                    token = tokenHelper.register(username, password);
                    Map<String, Object> result = new HashMap<>();
                    result.put("token", token);
                    return new MyResponse(
                            1,
                            "登录成功",
                            result
                    );
                } else
                    return new MyResponse(
                            0,
                            "用户名或密码错误"
                    );
            }
        }
        return new MyResponse(
                0,
                "用户名不存在"
        );
    }

    public MyResponse EditPassword(String token, String rawPassword) {
        ValueOperations<String, String> valueStr = redisTemplate.opsForValue();
        String username = valueStr.get(token);
        Integer userId = userManager.findIdByUsername(username);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(rawPassword.trim());
        try {
            userManager.updateUserPassword(encodedPassword, userId);
            return new MyResponse(
                    1,
                    "密码修改成功"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new MyResponse(
                    0,
                    "密码修改失败"
            );
        }
    }

    public MyResponse EditBasic(String token, String nickname, String email) {
        ValueOperations<String, String> valueStr = redisTemplate.opsForValue();
        String username = valueStr.get(token);
        Integer userId = userManager.findIdByUsername(username);
        try {
            userManager.updateUserBasic(email, nickname, userId);
            return new MyResponse(
                    1,
                    "基本信息修改成功"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new MyResponse(
                    0,
                    "基本信息修改失败"
            );
        }
    }
}
