package com.erzbir.sys.core.component;

import com.erzbir.sys.annotation.Component;
import com.erzbir.sys.application.DefaultApplication;
import com.erzbir.sys.client.Client;
import com.erzbir.sys.client.req.AddReqs;
import com.erzbir.sys.client.resp.Response;
import com.erzbir.sys.component.AbstractComponent;
import com.erzbir.sys.component.RegisterComponent;
import com.erzbir.sys.entity.User;
import com.erzbir.sys.event.UserRegisterEvent;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
@Component
public class DefaultRegisterComponent extends AbstractComponent implements RegisterComponent {
    private final Client client = Client.INSTANCE;


    @Override
    public void init() {
        isInit.set(true);
    }

    @Override
    public boolean register(User user) {
        Response<?> response = client.register(new AddReqs.AddUser(user));
        return response.getSuccess();
    }
}
