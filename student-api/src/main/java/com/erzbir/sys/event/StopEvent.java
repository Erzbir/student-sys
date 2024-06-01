package com.erzbir.sys.event;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class StopEvent extends AbstractLifeCycleEvent {
    public StopEvent(Object source) {
        super(source);
    }

    @Override
    public Object getSource() {
        return source;
    }

}
