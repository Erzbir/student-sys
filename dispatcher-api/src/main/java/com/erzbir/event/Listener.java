package com.erzbir.event;

/**
 * @author Erzbir
 */
public interface Listener<E extends Event> {
    ListenerResult onEvent(E event);
}