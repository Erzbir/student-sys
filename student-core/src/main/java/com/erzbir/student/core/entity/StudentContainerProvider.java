package com.erzbir.student.core.entity;

import com.erzbir.student.entity.StudentContainer;

import java.util.ServiceLoader;

/**
 * @author Erzbir
 * @Data: 2023/12/13
 */
public class StudentContainerProvider {
    private static volatile StudentContainer INSTANCE;

    public static StudentContainer getImpl() {
        if (INSTANCE == null) {
            synchronized (StudentContainerProvider.class) {
                if (INSTANCE == null) {
                    INSTANCE = ServiceLoader.load(StudentContainer.class).findFirst().orElseThrow();
                }
            }
        }
        return INSTANCE;
    }
}
