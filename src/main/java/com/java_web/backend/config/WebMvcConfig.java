package com.java_web.backend.config;

import com.java_web.backend.util.AuthorizationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
    // 用bean注册工厂方法，否则会导致空指针
    @Bean
    AuthorizationInterceptor authorizationLoginInterceptorFactory() {
        return new AuthorizationInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationLoginInterceptorFactory());
    }
}