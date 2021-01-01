package com.java_web.backend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class RedisReceiver {
    private static final Logger logger = LoggerFactory.getLogger(RedisReceiver.class);
    private final AtomicInteger counter = new AtomicInteger();

    public void receiveMessage(String message) {
        logger.info("Received <" + message + ">");
        counter.incrementAndGet();
    }
    public int getCount() {
        return counter.get();
    }

    public static void main(String[] args) {
        RedisReceiver rr = new RedisReceiver();
        rr.receiveMessage(String.valueOf(rr.getCount()));
    }
}
