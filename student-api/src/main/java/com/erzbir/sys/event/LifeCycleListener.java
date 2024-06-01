package com.erzbir.sys.event;

import java.util.EventListener;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public interface LifeCycleListener<E extends LifeCycleEvent> extends EventListener {
    void onEvent(E event);
}
