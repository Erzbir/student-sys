package com.erzbir.student.application;

import com.erzbir.student.event.LifeCycleEvent;
import com.erzbir.student.event.LifeCycleListener;

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
