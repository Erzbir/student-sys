package com.erzbir.sys.core.component;

import cn.hutool.core.util.StrUtil;
import com.erzbir.sys.annotation.Component;
import com.erzbir.sys.client.Client;
import com.erzbir.sys.client.req.LoginReq;
import com.erzbir.sys.client.resp.LoginResp;
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
        broadcastEvent(new UserLoginEvent(user));
        LoginResp resp = client.login(new LoginReq(user));
        return StrUtil.isNotBlank(resp.getToken()) && StrUtil.isNotBlank(resp.getUsername());
    }

    @Override
    public void init() {
        isInit.set(true);
    }
}
