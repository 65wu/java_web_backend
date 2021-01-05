package com.java_web.backend.service;

import com.java_web.backend.dao.UserManager;
import com.java_web.backend.dao.UserRepository;
import com.java_web.backend.model.po.User;
import com.java_web.backend.util.Md5TokenGenerator;
import com.java_web.backend.util.MyResponse;
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
import java.util.concurrent.TimeUnit;

@Service
@CrossOrigin(origins = "*")
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserManager userManager;
    @Autowired
    private Md5TokenGenerator tokenGenerator;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public MyResponse Register(
            String password,
            String name,
            String email
    ) {
        Integer id = userManager.findIdByName(name);
        if(id != null) {
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
        user.setName(name);
        user.setEmail(email);
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
            String name,
            String rawPassword
    ) {
        Integer id = userManager.findIdByName(name);
        Optional<User> optionalUser = userRepository.findById(id);
        ValueOperations<String, String> valueStr = redisTemplate.opsForValue();

        if(optionalUser.isPresent()) {
            // 准备比对密码
            User user = optionalUser.get();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = user.getPassword();

            if(passwordEncoder.matches(rawPassword, password)) {
                // 先查看token是否存在
                String token = valueStr.get(name);
                if(token != null) {
                    String tokenBirthRaw = valueStr.get(token + name);
                    if(tokenBirthRaw != null) {
                        // 取出token注册时的时间戳
                        long tokeBirthTime = Long.parseLong(tokenBirthRaw);
                        // 若token寿命过半则更新
                        if (System.currentTimeMillis() - tokeBirthTime > 3e5) {
                            redisTemplate.expire(name, 10, TimeUnit.MINUTES);
                            redisTemplate.expire(token, 10, TimeUnit.MINUTES);
                            long newBirthTime = System.currentTimeMillis();
                            valueStr.set(token + name, Long.toString(newBirthTime));
                        }
                        Map<String, Object> result = new HashMap<>();
                        result.put("token", token);
                        return new MyResponse(
                                1,
                                "登录成功",
                                result
                        );
                    }
                }
                // redis token初始化或重新注册
                token = tokenGenerator.generate(name, password);
                valueStr.set(name, token, 10, TimeUnit.MINUTES);
                valueStr.set(token, name, 10, TimeUnit.MINUTES);
                long birthTime = System.currentTimeMillis();
                valueStr.set(token + name, birthTime + "", 10, TimeUnit.MINUTES);
                Map<String, Object> result = new HashMap<>();
                result.put("token", token);
                return new MyResponse(
                        1,
                        "登录成功",
                        result
                );
            }
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
    public MyResponse EditPassword(Integer username, String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(rawPassword.trim());
        try {
            userManager.updateUserPassword(encodedPassword, username);
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
    public MyResponse EditBasic(Integer username, String name, String email) {
        try {
            userManager.updateUserBasic(name, email, username);
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
