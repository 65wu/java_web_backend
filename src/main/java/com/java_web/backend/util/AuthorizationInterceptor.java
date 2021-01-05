package com.java_web.backend.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class AuthorizationInterceptor implements HandlerInterceptor {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.getAnnotation(AuthToken.class) != null || handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null) {
            // 获取请求头中的token值
            String httpHeaderName = "Token";
            String token = request.getHeader(httpHeaderName);
            // 用token查找name
            ValueOperations<String, String> valueStr = redisTemplate.opsForValue();
            if (token != null && token.length() != 0) {
                String name = valueStr.get(token);
                if (name != null) {
                    String tokenBirthRaw = valueStr.get(token + name);
                    if (tokenBirthRaw != null) {
                        long tokeBirthTime = Long.parseLong(tokenBirthRaw);
                        System.out.println("token birth time: " + tokeBirthTime);
                        // 如果token失效已经超过3e5ms，即300s, 5min，对token进行续期
                        if (System.currentTimeMillis() - tokeBirthTime > 3e5) {
                            redisTemplate.expire(name, 10, TimeUnit.MINUTES);
                            redisTemplate.expire(token, 10, TimeUnit.MINUTES);
                            long newBirthTime = System.currentTimeMillis();
                            valueStr.set(token + name, Long.toString(newBirthTime));
                        }
                        return true;
                    }
                }
            }
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node = mapper.createObjectNode();
            PrintWriter out = null;
            try {
                int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;
                response.setStatus(unauthorizedErrorCode);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                node.put("code", ((HttpServletResponse) response).getStatus());
                node.put("message", String.valueOf(HttpStatus.UNAUTHORIZED));
                out = response.getWriter();
                out.println(node);
                return false;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != out) {
                    out.flush();
                    out.close();
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
