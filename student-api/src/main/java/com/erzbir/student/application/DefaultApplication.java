package com.erzbir.student.application;

import com.erzbir.student.event.StartupEvent;
import com.erzbir.student.event.StopEvent;

import java.util.concurrent.CompletableFuture;

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
        listeners.forEach(lifeCycleListenerRegistry -> CompletableFuture.runAsync(() -> lifeCycleListenerRegistry.listener.onEvent(new StartupEvent(this))));
    }

    @Override
    public void stop() {
        listeners.forEach(lifeCycleListenerRegistry -> CompletableFuture.runAsync(() -> lifeCycleListenerRegistry.listener.onEvent(new StopEvent(this))));
    }
}
