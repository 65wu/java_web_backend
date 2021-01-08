package com.java_web.backend;

import com.java_web.backend.util.TokenHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GenerateValidationTest {
    @Autowired
    TokenHelper tokenHelper;
    @Test
    void testValidation() {
        Integer userId = 0;
        tokenHelper.generateVerificationCode(0);
    }
}
