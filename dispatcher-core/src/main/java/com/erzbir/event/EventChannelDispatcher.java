package com.erzbir.event;



/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class EventChannelDispatcher<E extends Event> extends EventChannelImpl<E> {
    public static final EventChannelDispatcher<Event> INSTANCE = new EventChannelDispatcher<>(Event.class);

    private EventChannelDispatcher(Class<E> baseEventClass) {
        super(baseEventClass);
    }

    @Override
    public void broadcast(EventContext eventContext) {
        super.broadcast(eventContext);
    }
}
