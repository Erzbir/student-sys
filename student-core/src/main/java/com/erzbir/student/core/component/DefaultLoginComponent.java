package com.erzbir.student.core.component;

import cn.hutool.core.util.StrUtil;
import com.erzbir.student.annotation.Component;
import com.erzbir.student.client.Client;
import com.erzbir.student.client.req.LoginReq;
import com.erzbir.student.client.resp.LoginResp;
import com.erzbir.student.component.AbstractComponent;
import com.erzbir.student.component.LoginComponent;
import com.erzbir.student.entity.User;
import com.erzbir.student.event.UserLoginEvent;

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
