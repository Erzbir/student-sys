package com.erzbir.student.event;

import java.util.List;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public interface LifeCycleEventProcessor {
    void fireEvent(LifeCycleEvent lifeCycleEvent);

    List<LifeCycleListener> getListenerContainer();
}
