package com.erzbir.student.component;

import com.erzbir.student.entity.IUser;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public interface UserManageComponent extends IComponent {
    void update(IUser user);

    IUser getUser(String key);
}
