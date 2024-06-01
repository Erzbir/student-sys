package com.erzbir.sys.application;

import com.erzbir.sys.event.LifeCycleEvent;
import com.erzbir.sys.event.LifeCycleListener;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public interface ApplicationEventPublisher {
    void publishEvent(LifeCycleEvent event);

    class LifeCycleListenerRegistry {
        public final LifeCycleListener listener;
        public final Class<? extends LifeCycleEvent> eventType;

        public LifeCycleListenerRegistry(LifeCycleListener<? extends LifeCycleEvent> listener, Class<? extends LifeCycleEvent> eventType) {
            this.eventType = eventType;
            this.listener = listener;
        }
    }
}
