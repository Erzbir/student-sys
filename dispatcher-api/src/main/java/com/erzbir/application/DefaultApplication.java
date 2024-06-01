package com.erzbir.application;

import com.erzbir.event.EventDispatcher;

import java.util.ServiceLoader;

/**
 * @author Erzbir
 * @version 1.0
 * @since 2024/6/2
 */
public class DefaultApplication implements Application {
    private Application delegate = ServiceLoader.load(Application.class).findFirst().orElseThrow();

    @Override
    public AppConfiguration getConfiguration() {
        return delegate.getConfiguration();
    }

    @Override
    public EventDispatcher getEventDispatcher() {
        return delegate.getEventDispatcher();
    }

    @Override
    public void join() {
        delegate.join();
    }
}
