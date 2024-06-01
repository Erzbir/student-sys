package com.erzbir.sys.component;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public interface IComponent extends Priority {
    void init();

    void disable();

    void active();

    boolean isInit();

    boolean isActive();
}
