package com.java_web.backend.util;

import org.springframework.stereotype.Component;

@Component
public interface TokenGenerator {
    String generate(String... strings);
}