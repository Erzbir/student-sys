package com.erzbir.student.application;

import com.erzbir.student.annotation.Component;
import com.erzbir.student.component.IComponent;
import com.erzbir.student.event.LifeCycleEvent;
import com.erzbir.student.event.LifeCycleListener;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
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

    @Override
    public void init(Set<Class<?>> classes) {
        loadComponents(classes);
        initComponents();
    }

    public void initComponents() {
        components.values().forEach(IComponent::init);
    }

    public void loadComponents(Set<Class<?>> classes) {
//        Set<Class<?>> componentClasses = ClassScanner.scanAllPackageByAnnotation("com.erzbir.student", Component.class);
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
}
