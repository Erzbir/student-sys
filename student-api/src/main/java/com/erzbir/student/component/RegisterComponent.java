package com.erzbir.student.component;

import com.erzbir.student.entity.User;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public interface RegisterComponent extends IComponent {
    boolean register(User user);
}
