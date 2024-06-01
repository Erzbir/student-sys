package com.erzbir.backend.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import com.erzbir.backend.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Erzbir
 * @Date 2023/8/6
 */
public class JWTUtil {
    public static String createToken(User user) {
        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(DateField.MINUTE, 1000); // Token 有效期为 1000 分钟
        Map<String, Object> payload = new HashMap<>();
        payload.put(JWTPayload.ISSUED_AT, now);
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        payload.put(JWTPayload.NOT_BEFORE, now);
        payload.put("username", user.getUsername());
        String key = "erzbir.com"; // 盐
        return cn.hutool.jwt.JWTUtil.createToken(payload, key.getBytes());
    }

    public static String parseToken(String token) {
        final JWT jwt = cn.hutool.jwt.JWTUtil.parseToken(token);
        return jwt.getPayload("username").toString();
    }
}
