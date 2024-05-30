package com.erzbir.event;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
@SuppressWarnings("rawtypes")
public record ListenerDescription(Class<Event> eventType, Listener listener) {
}