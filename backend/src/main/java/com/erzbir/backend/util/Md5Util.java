package com.erzbir.backend.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Erzbir
 * @Data: 2024/5/29 11:21
 */
public class Md5Util {
    public static String encrypt(String origin) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        byte[] bytes = origin.getBytes();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(bytes);
            byte[] md = md5.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String decrypt(String md5) {
        return "";
    }
}
