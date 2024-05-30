package com.erzbir.student.core.component;

import com.erzbir.student.annotation.Component;
import com.erzbir.student.component.AbstractComponent;
import com.erzbir.student.component.LoginComponent;
import com.erzbir.student.entity.IUser;
import com.erzbir.student.entity.UserContainer;
import com.erzbir.student.core.entity.UserContainerProvider;
import com.erzbir.student.event.UserLoginEvent;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
@Component
public class DefaultLoginComponent extends AbstractComponent implements LoginComponent {
    private UserContainer userContainer;

    @Override
    public boolean login(IUser user) {
        IUser user1 = userContainer.get(user.getUsername());
        broadcastEvent(new UserLoginEvent(user));
        if (!(user1 == null)) {
            return user1.getPassword().equals(user.getPassword());
        }
        return false;
    }

    @Override
    public void init() {
        userContainer = UserContainerProvider.getImpl();
        isInit.set(true);
    }
}
