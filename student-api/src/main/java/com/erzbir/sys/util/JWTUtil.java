package com.erzbir.sys.util;

import cn.hutool.jwt.JWT;

/**
 * @author Erzbir
 * @Date 2023/8/6
 */
public class JWTUtil {

    public static boolean isTokenExpired(String token) {
        JWT jwt = cn.hutool.jwt.JWTUtil.parseToken(token);
        long exp = Long.parseLong(jwt.getPayload("exp").toString());
        return System.currentTimeMillis() > exp;
    }
}
