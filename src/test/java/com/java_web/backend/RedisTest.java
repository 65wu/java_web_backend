package com.java_web.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class RedisTest {
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Test
    public void testString(){
        ValueOperations<String, String> valueStr = redisTemplate.opsForValue();
        //写入单条数据测试
        valueStr.set("singleTest","singleValue");
        //读取
        String goodsName = valueStr.get("singleTest");
        System.out.println(goodsName);
        //写入
        Map<String,String> map = new HashMap<>();
        map.put("multiTest0","1");
        map.put("multiTest1","2");
        map.put("multiTest2","3");

        valueStr.multiSet(map);
        //读取
        System.out.println("========================================");
        List<String> list = new ArrayList<>();
        list.add("multiTest0");
        list.add("multiTest1");
        list.add("multiTest2");

        List<String> listKeys = valueStr.multiGet(list);
        assert listKeys != null;
        for (String key : listKeys) {
            System.out.println(key);
        }
    }
}

