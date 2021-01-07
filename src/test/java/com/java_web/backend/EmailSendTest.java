package com.java_web.backend;

import com.java_web.backend.util.EmailSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailSendTest {
    @Autowired
    EmailSender emailSender;
    @Test
    public void sendEmail(){
        emailSender.sendMail(
                "904795395@qq.com",
                "smtp服务测试",
                "您的验证码为123456"
        );
    }
}
