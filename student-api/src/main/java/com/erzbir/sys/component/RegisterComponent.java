package com.erzbir.sys.component;

import com.erzbir.sys.entity.User;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public interface RegisterComponent extends IComponent {
    boolean register(User user);
}
