package com.erzbir.event;


/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class GlobalEventChannelProviderImpl implements InternalGlobalEventProvider {
    @Override
    public EventChannel<Event> getInstance() {
        return EventChannelDispatcher.INSTANCE;
    }
}
