package com.erzbir.sys.event;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public interface LifeCycleEvent {
    Object getSource();

    long timestamp();
}
