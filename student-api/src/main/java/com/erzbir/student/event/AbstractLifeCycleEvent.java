package com.erzbir.student.event;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public abstract class AbstractLifeCycleEvent implements LifeCycleEvent {
    protected Object source;
    protected long timestamp;

    public AbstractLifeCycleEvent(Object source) {
        this.source = source;
        timestamp = System.currentTimeMillis();
    }

    @Override
    public long timestamp() {
        return timestamp;
    }

}
