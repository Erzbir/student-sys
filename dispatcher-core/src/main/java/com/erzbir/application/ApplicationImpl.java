package com.erzbir.application;

import com.erzbir.event.EventDispatcher;
import com.erzbir.event.NotificationEventDispatcher;
import com.erzbir.event.PollingEventDispatcher;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class ApplicationImpl implements Application {
    private AppConfiguration appConfiguration;
    private EventDispatcher eventDispatcher = new NotificationEventDispatcher();

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
