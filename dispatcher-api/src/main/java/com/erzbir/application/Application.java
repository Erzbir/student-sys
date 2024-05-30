package com.erzbir.application;


import com.erzbir.event.EventDispatcher;

/**
 * @author Erzbir
 * @Data: 2024/2/8 00:41
 */
public interface Application {
    AppConfiguration getConfiguration();

    EventDispatcher getEventDispatcher();

    void join();
}
