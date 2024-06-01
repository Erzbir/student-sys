package com.erzbir.sys.event;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class StartupEvent extends AbstractLifeCycleEvent {
    public StartupEvent(Object source) {
        super(source);
    }

    @Override
    public Object getSource() {
        return source;
    }
}
