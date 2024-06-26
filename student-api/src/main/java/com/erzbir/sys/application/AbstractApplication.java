package com.erzbir.sys.application;

import com.erzbir.sys.annotation.Component;
import com.erzbir.sys.component.IComponent;
import com.erzbir.sys.event.LifeCycleEvent;
import com.erzbir.sys.event.LifeCycleListener;
import com.erzbir.sys.setting.Setting;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public abstract class AbstractApplication implements Application, ApplicationEventPublisher {
    protected Set<LifeCycleListenerRegistry> listeners = new LinkedHashSet<>();
    protected final static Map<Class<IComponent>, IComponent> components = new ConcurrentHashMap<>();
    protected Setting setting;

    @Override
    public void init(Set<Class<?>> classes, Setting setting) {
        this.setting = setting;
        loadComponents(classes);
        initComponents();
    }

    public void initComponents() {
        components.values().forEach(IComponent::init);
    }

    public void loadComponents(Set<Class<?>> classes) {
        classes.stream().filter(c -> c.isAnnotationPresent(Component.class) && IComponent.class.isAssignableFrom(c)).forEach(componentClass -> {
            try {
                IComponent component = (IComponent) componentClass.getConstructor().newInstance();
                registerComponent(component);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void registerComponent(IComponent component) {
        Class<IComponent> componentInter = getComponentInter(component);
        if (componentInter != null) {
            IComponent oldComponent = components.get(componentInter);
            if (oldComponent != null) {
                if (component.getPriority() < oldComponent.getPriority()) {
                    return;
                }
            }
            components.put(componentInter, component);
        }
    }

    private Class<IComponent> getComponentInter(IComponent component) {
        Class<? extends IComponent> componentClass = component.getClass();
        Class<?>[] interfaces = componentClass.getInterfaces();
        for (Class<?> inter : interfaces) {
            if (IComponent.class.isAssignableFrom(inter)) {
                return (Class<IComponent>) inter;
            }
        }
        return null;
    }

    @Override
    public <E extends IComponent> E getComponent(Class<E> componentInter) {
        return (E) components.get(componentInter);
    }

    @Override
    public void publishEvent(LifeCycleEvent event) {
        for (LifeCycleListenerRegistry listenerRegistry : listeners) {
            if (listenerRegistry.eventType.isInstance(event)) {
                listenerRegistry.listener.onEvent(event);
            }
        }
    }

    @Override
    public <E extends LifeCycleEvent> void addLifeCycleListener(LifeCycleListener<E> lifeCycleListener, Class<E> eventType) {
        listeners.add(new LifeCycleListenerRegistry(lifeCycleListener, eventType));
    }

    @Override
    public Setting getSetting() {
        return setting;
    }
}
