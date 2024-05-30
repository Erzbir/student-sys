package com.erzbir.application;

import com.erzbir.event.EventDispatcher;
import com.erzbir.event.PollingEventDispatcher;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class DefaultApplication implements Application {
    private AppConfiguration appConfiguration;
    private EventDispatcher eventDispatcher = new PollingEventDispatcher();

    @Override
    public AppConfiguration getConfiguration() {
        return appConfiguration;
    }

    @Override
    public EventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }

    @Override
    public void join() {
    }
}
