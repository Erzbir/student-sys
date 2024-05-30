package com.erzbir.student.core.component;

import com.erzbir.student.annotation.Component;
import com.erzbir.student.component.AbstractComponent;
import com.erzbir.student.component.RegisterComponent;
import com.erzbir.student.entity.IUser;
import com.erzbir.student.entity.UserContainer;
import com.erzbir.student.core.entity.UserContainerProvider;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
@Component
public class DefaultRegisterComponent extends AbstractComponent implements RegisterComponent {
    private UserContainer userContainer;

    @Override
    public void init() {
        userContainer = UserContainerProvider.getImpl();
        isInit.set(true);
    }

    @Override
    public boolean register(IUser user) {
        userContainer.add(user);
        return true;
    }
}
