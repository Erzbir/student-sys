package com.erzbir.sys.event;

import com.erzbir.event.AbstractEvent;
import com.erzbir.sys.entity.User;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class UserRegisterEvent extends AbstractEvent implements UserEvent {
    public UserRegisterEvent(User source) {
        super(source);
    }

    @Override
    public User getSource() {
        return (User) source;
    }
}
