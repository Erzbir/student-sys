package com.erzbir.student.core.component.proxy;

import com.erzbir.student.component.AbstractComponent;
import com.erzbir.student.component.IComponent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class ComponentProxy<E extends IComponent> extends AbstractComponent implements IComponent {
    private E delegate;

    public ComponentProxy(E delegate) {
        this.delegate = delegate;
    }

    public E getProxy() {
        Class<? extends IComponent> aClass = delegate.getClass();
        return (E) Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), new CheckHandler());
    }

    @Override
    public void init() {
        delegate.init();
    }

    class CheckHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            String name = method.getName();
//            if (!name.equals("isInit") && !name.equals("isActive") && !name.equals("init")) {
//                if (!delegate.isInit() || !delegate.isActive()) {
//                    throw new UnsupportedOperationException();
//                }
//            }
            method.setAccessible(true);
            return method.invoke(delegate, args);
        }
    }
}
