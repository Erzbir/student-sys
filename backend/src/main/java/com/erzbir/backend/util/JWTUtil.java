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
    /**
     * 生成系统用户token
     */
    public static String createToken(User user) {
        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(DateField.MINUTE, 10); // Token 有效期为 10 分钟
        Map<String, Object> payload = new HashMap<>();
        // 签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        // 过期时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        // 生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        // 载荷
        payload.put("username", user.getUsername());
        String key = "erzbir.com"; // 盐
        return cn.hutool.jwt.JWTUtil.createToken(payload, key.getBytes());
    }

    /**
     * 解析token
     */
    public static Long parseToken(String token) {
        final JWT jwt = cn.hutool.jwt.JWTUtil.parseToken(token);
        String userId = jwt.getPayload("userId").toString();
        return Long.parseLong(userId);
    }
}
