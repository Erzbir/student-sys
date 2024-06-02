package com.erzbir.sys.core.component;

import com.erzbir.sys.annotation.Component;
import com.erzbir.sys.application.DefaultApplication;
import com.erzbir.sys.client.Client;
import com.erzbir.sys.client.req.LoginReq;
import com.erzbir.sys.client.resp.Response;
import com.erzbir.sys.component.AbstractComponent;
import com.erzbir.sys.component.LoginComponent;
import com.erzbir.sys.entity.User;
import com.erzbir.sys.event.UserLoginEvent;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
@Component
public class DefaultLoginComponent extends AbstractComponent implements LoginComponent {
    private final Client client = Client.INSTANCE;

    @Override
    public boolean login(User user) {
        Response<String> resp = client.login(new LoginReq(user));
        String data = resp.getData();
        if (data == null || data.isEmpty()) return false;
        boolean blank = data.isBlank();
        return !blank;
    }

    @Override
    public void init() {
        isInit.set(true);
    }
}
