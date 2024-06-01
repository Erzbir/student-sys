package com.erzbir.sys.util;

import com.google.gson.Gson;

/**
 * @author Erzbir
 * @version 1.0
 * @since 2024/6/1
 */
public class JSONUtil {
    public static String toJsonStr(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object, object.getClass());
    }

    public static <T> T toBean(String jsonStr, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, clazz);
    }
}
