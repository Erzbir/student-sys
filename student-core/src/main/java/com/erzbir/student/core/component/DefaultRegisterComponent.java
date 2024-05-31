package com.erzbir.student.core.component;

import com.erzbir.student.annotation.Component;
import com.erzbir.student.client.Client;
import com.erzbir.student.client.req.AddReqs;
import com.erzbir.student.client.resp.Response;
import com.erzbir.student.component.AbstractComponent;
import com.erzbir.student.component.RegisterComponent;
import com.erzbir.student.entity.User;

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
