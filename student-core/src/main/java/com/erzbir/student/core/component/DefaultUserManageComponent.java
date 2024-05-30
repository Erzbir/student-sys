package com.erzbir.student.core.component;

import com.erzbir.student.annotation.Component;
import com.erzbir.student.component.AbstractComponent;
import com.erzbir.student.component.UserManageComponent;
import com.erzbir.student.entity.IUser;
import com.erzbir.student.entity.UserContainer;
import com.erzbir.student.core.entity.UserContainerProvider;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
@Component
public class DefaultUserManageComponent extends AbstractComponent implements UserManageComponent {
    private UserContainer userContainer;

    @Override
    public void init() {
        userContainer = UserContainerProvider.getImpl();
    }

    @Override
    public void update(IUser user) {
        userContainer.update(user);
    }

    @Override
    public IUser getUser(String key) {
        return userContainer.get(key);
    }
}
