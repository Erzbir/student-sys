package com.erzbir.student.core.entity;

import com.erzbir.student.entity.UserContainer;

import java.util.ServiceLoader;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class UserContainerProvider {
    private static volatile UserContainer INSTANCE;

    public static UserContainer getImpl() {
        if (INSTANCE == null) {
            synchronized (UserContainerProvider.class) {
                if (INSTANCE == null) {
                    INSTANCE = ServiceLoader.load(UserContainer.class).findFirst().orElseThrow();
                }
            }
        }
        return INSTANCE;
    }
}
