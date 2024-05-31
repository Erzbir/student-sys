package com.erzbir.student.core.component;

import com.erzbir.student.annotation.Component;
import com.erzbir.student.client.Client;
import com.erzbir.student.client.req.QueryReqs;
import com.erzbir.student.client.req.UpdateReqs;
import com.erzbir.student.component.AbstractComponent;
import com.erzbir.student.component.UserManageComponent;
import com.erzbir.student.entity.User;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
@Component
public class DefaultUserManageComponent extends AbstractComponent implements UserManageComponent {
    private final Client client = Client.INSTANCE;

    @Override
    public void init() {
        isInit.set(true);
    }

    @Override
    public void update(User user) {
        client.updateUser(new UpdateReqs.UpdateUser(user));
    }

    @Override
    public User getUser(String key) {
        return client.queryUserByName(new QueryReqs.QueryUserByName(key)).getData();
    }
}
