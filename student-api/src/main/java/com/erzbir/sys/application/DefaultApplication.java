package com.erzbir.sys.application;

import com.erzbir.event.Event;
import com.erzbir.event.EventDispatcher;
import com.erzbir.sys.event.StartupEvent;
import com.erzbir.sys.event.StopEvent;

import java.util.concurrent.CompletableFuture;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class DefaultApplication extends AbstractApplication implements Application {
    public static final DefaultApplication INSTANCE = new DefaultApplication();
    protected EventDispatcher eventDispatcher = new com.erzbir.application.DefaultApplication().getEventDispatcher();

    private DefaultApplication() {
    }


    @Override
    public void start() {
        listeners.forEach(lifeCycleListenerRegistry -> CompletableFuture.runAsync(() -> lifeCycleListenerRegistry.listener.onEvent(new StartupEvent(this))));
        eventDispatcher.start();
    }

    @Override
    public void stop() {
        listeners.forEach(lifeCycleListenerRegistry -> CompletableFuture.runAsync(() -> lifeCycleListenerRegistry.listener.onEvent(new StopEvent(this))));
    }

    @Override
    public void dispatchEvent(Event event) {
        eventDispatcher.dispatch(event);
    }
}
