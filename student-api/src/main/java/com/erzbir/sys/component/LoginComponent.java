package com.erzbir.sys.component;

import com.erzbir.sys.entity.User;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public interface LoginComponent extends IComponent {
    boolean login(User user);
}
