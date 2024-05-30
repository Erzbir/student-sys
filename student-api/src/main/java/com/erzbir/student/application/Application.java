package com.erzbir.student.application;

import com.erzbir.student.component.IComponent;
import com.erzbir.student.event.LifeCycleEvent;
import com.erzbir.student.event.LifeCycleListener;

import java.util.Set;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public interface Application extends LifeCycle {
    <E extends IComponent> E getComponent(Class<E> componentInter);

    <E extends LifeCycleEvent>

    void addLifeCycleListener(LifeCycleListener<E> lifeCycleListener, Class<E> eventType);

    void init(Set<Class<?>> classes);

    void registerComponent(IComponent component);
}
