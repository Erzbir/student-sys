package com.erzbir.backend.util;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import com.erzbir.backend.entity.User;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Erzbir
 * @Date 2023/8/6
 */
public class JWTUtil {
    public static String createToken(User user, int expireMin) {
        long now = System.currentTimeMillis();
        long newTime = now + Duration.ofMinutes(expireMin).toMillis();
        Map<String, Object> payload = new HashMap<>();
        payload.put(JWTPayload.ISSUED_AT, now);
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        payload.put(JWTPayload.NOT_BEFORE, now);
        payload.put(JWTPayload.SUBJECT, user.getUsername());
        String key = "erzbir.com";
        return cn.hutool.jwt.JWTUtil.createToken(payload, key.getBytes());
    }

    public static String getUsernameFromToken(String token) {
        JWT jwt = cn.hutool.jwt.JWTUtil.parseToken(token);
        return (String) jwt.getPayload("sub");
    }

    public static boolean isTokenExpired(String token) {
        JWT jwt = cn.hutool.jwt.JWTUtil.parseToken(token);
        long exp = Long.parseLong(jwt.getPayload("exp").toString());
        return System.currentTimeMillis() > exp;
    }

    public static boolean validateToken(String token, String username) {
        String usernameFromToken = getUsernameFromToken(token);
        return usernameFromToken.equals(username) && !isTokenExpired(token);
    }
}
