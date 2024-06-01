package com.erzbir.sys.application;

import com.erzbir.event.Event;
import com.erzbir.sys.component.IComponent;
import com.erzbir.sys.event.LifeCycleEvent;
import com.erzbir.sys.event.LifeCycleListener;
import com.erzbir.sys.setting.Setting;

import java.util.Set;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public interface Application extends LifeCycle {
    <E extends IComponent> E getComponent(Class<E> componentInter);

    <E extends LifeCycleEvent>

    void addLifeCycleListener(LifeCycleListener<E> lifeCycleListener, Class<E> eventType);

    void init(Set<Class<?>> classes, Setting setting);

    void registerComponent(IComponent component);

    Setting getSetting();

    void dispatchEvent(Event event);
}
