package com.java_web.backend;

import com.java_web.backend.util.Md5TokenGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenGeneratorTest {
    @Autowired
    private Md5TokenGenerator tokenGenerator;
    @Test
    public void generateTest() {
        String username = "65wu";
        String password = "123456";
        String value = tokenGenerator.generate(username, password);
        System.out.println(value);
    }
}
