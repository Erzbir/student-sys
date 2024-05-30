package com.erzbir.student.component;

import com.erzbir.student.entity.IUser;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public interface LoginComponent extends IComponent {
    boolean login(IUser user);
}
