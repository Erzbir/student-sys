package com.erzbir.student.application;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class DefaultApplication extends AbstractApplication implements Application {
    public static final DefaultApplication INSTANCE = new DefaultApplication();

    private DefaultApplication() {
    }


    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
