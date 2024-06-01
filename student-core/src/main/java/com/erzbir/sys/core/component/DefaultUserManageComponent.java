package com.erzbir.sys.core.component;

import com.erzbir.sys.annotation.Component;
import com.erzbir.sys.client.Client;
import com.erzbir.sys.client.req.QueryReqs;
import com.erzbir.sys.client.req.UpdateReqs;
import com.erzbir.sys.component.AbstractComponent;
import com.erzbir.sys.component.UserManageComponent;
import com.erzbir.sys.entity.User;

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
