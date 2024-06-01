package com.erzbir.sys.util;


import com.erzbir.sys.entity.User;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class SavedUser {
    public final static String REM_PASSWORD_KEY = "REM_PASSWORD";
    public final static String USERNAME_KEY = "USERNAME";
    public final static String PASSWORD_KEY = "PASSWORD";

    private static final ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static User getUser() {
        return userThreadLocal.get();
    }

    public static void setUser(User user) {
        userThreadLocal.set(user);
    }
}
