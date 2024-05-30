package com.erzbir.student.component;

import com.erzbir.event.Event;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public interface IComponent extends Priority {
    void init();

    void disable();

    void active();

    boolean isInit();

    boolean isActive();

    void broadcastEvent(Event event);
}
