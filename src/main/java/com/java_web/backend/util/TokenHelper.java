package com.java_web.backend.util;

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

    // redis token初始化或重新注册
    public String register(String name, String password) {
        ValueOperations<String, String> valueStr = redisTemplate.opsForValue();
        String token = tokenGenerator.generate(name, password);
        valueStr.set(name, token, 10, TimeUnit.MINUTES);
        valueStr.set(token, name, 10, TimeUnit.MINUTES);
        long birthTime = System.currentTimeMillis();
        valueStr.set(token + name, birthTime + "", 10, TimeUnit.MINUTES);
        return token;
    }
    // 检测token寿命，若过半则更新
    public boolean renew(String name, String token) {
        ValueOperations<String, String> valueStr = redisTemplate.opsForValue();
        String tokenBirthRaw = valueStr.get(token + name);
        if(tokenBirthRaw != null) {
            long tokeBirthTime = Long.parseLong(tokenBirthRaw);
            // 如果token失效已经超过3e5ms，即300s, 5min，对token进行续期
            if (System.currentTimeMillis() - tokeBirthTime > 3e5) {
                redisTemplate.expire(name, 10, TimeUnit.MINUTES);
                redisTemplate.expire(token, 10, TimeUnit.MINUTES);
                long newBirthTime = System.currentTimeMillis();
                valueStr.set(token + name, Long.toString(newBirthTime));
            }
            return true;
        }
        return false;
    }
}
