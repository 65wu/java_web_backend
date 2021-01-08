package com.java_web.backend.util;

import com.java_web.backend.dao.User.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TokenHelper {
    @Autowired
    private Md5TokenGenerator tokenGenerator;
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserManager userManager;

    // redis token初始化或重新注册
    public String register(String username, String password) {
        ValueOperations<String, String> valueStr = redisTemplate.opsForValue();
        String token = tokenGenerator.generate(username, password);
        valueStr.set(username, token, 10, TimeUnit.MINUTES);
        valueStr.set(token, username, 10, TimeUnit.MINUTES);
        long birthTime = System.currentTimeMillis();
        valueStr.set(token + username, birthTime + "", 10, TimeUnit.MINUTES);
        return token;
    }
    // 检测token寿命，若过半则更新
    public boolean renew(String username, String token) {
        ValueOperations<String, String> valueStr = redisTemplate.opsForValue();
        String tokenBirthRaw = valueStr.get(token + username);
        if(tokenBirthRaw != null) {
            long tokeBirthTime = Long.parseLong(tokenBirthRaw);
            // 如果token失效已经超过3e5ms，即300s, 5min，对token进行续期
            if (System.currentTimeMillis() - tokeBirthTime > 3e5) {
                redisTemplate.expire(username, 10, TimeUnit.MINUTES);
                redisTemplate.expire(token, 10, TimeUnit.MINUTES);
                long newBirthTime = System.currentTimeMillis();
                valueStr.set(token + username, Long.toString(newBirthTime), 10, TimeUnit.MINUTES);
            }
            return true;
        }
        return false;
    }
    // 通过token，先找到username，再换为userId
    public Integer getUserId(String token) {
        ValueOperations<String, String> valueStr = redisTemplate.opsForValue();
        String username = valueStr.get(token);
        return userManager.findIdByUsername(username);
    }
    // 用userId生成邮箱验证码
    public void generateVerificationCode(Integer userId) {
        int code = (int)(Math.random() * (1000000));
        ValueOperations<String, String> valueStr = redisTemplate.opsForValue();
        valueStr.set(userId + "", code + "", 5, TimeUnit.MINUTES);
    }
}
