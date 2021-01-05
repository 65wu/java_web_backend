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
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class AuthorizationInterceptor implements HandlerInterceptor {

    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";
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
            String httpHeaderName = "Authorization";
            String token = request.getParameter(httpHeaderName);
            ValueOperations<String, String> valueStr = redisTemplate.opsForValue();
            String name = "";
            if (token != null && token.length() != 0) {
                name = valueStr.get(token);
            }
            if (name != null && !name.trim().equals("")) {
                long tokeBirthTime = Long.parseLong(Objects.requireNonNull(valueStr.get(token + name)));
                long diff = System.currentTimeMillis() - tokeBirthTime;
                if (diff > 3e7) {
                    redisTemplate.expire(name, 60, TimeUnit.SECONDS);
                    redisTemplate.expire(token, 60, TimeUnit.SECONDS);
                    long newBirthTime = System.currentTimeMillis();
                    valueStr.set(token + name, Long.toString(newBirthTime));
                }
                request.setAttribute(REQUEST_CURRENT_KEY, name);
                return true;
            } else {
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
        }
        request.setAttribute(REQUEST_CURRENT_KEY, null);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
