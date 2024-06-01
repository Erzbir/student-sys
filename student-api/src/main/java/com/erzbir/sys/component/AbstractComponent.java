package com.erzbir.sys.component;

import com.erzbir.application.DefaultApplication;
import com.erzbir.event.Event;
import com.erzbir.event.EventDispatcher;
import com.erzbir.event.GlobalEventChannel;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public abstract class AbstractComponent implements IComponent {
    protected AtomicBoolean active = new AtomicBoolean();
    protected AtomicBoolean isInit = new AtomicBoolean();


    public AbstractComponent() {
        isInit.set(false);
        active.set(true);
    }

    @Override
    public Integer getPriority() {
        return 0;
    }

    @Override
    public boolean isInit() {
        return isInit.get();
    }

    @Override
    public boolean isActive() {
        return active.get();
    }

    public abstract void init();

    @Override
    public void disable() {
        active.set(false);
    }

    @Override
    public void active() {
        active.set(true);
    }
}
