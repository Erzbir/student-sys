package com.erzbir.backend.context;

import com.erzbir.backend.entity.User;

/**
 * @author Erzbir
 * @Data: 2024/5/29 10:38
 */
public class LoginUser {
    private static final ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static void setUser(User user) {
        userThreadLocal.set(user);
    }

    public static User getUser() {
        return userThreadLocal.get();
    }

    public static void remove() {
        userThreadLocal.remove();
    }
}
