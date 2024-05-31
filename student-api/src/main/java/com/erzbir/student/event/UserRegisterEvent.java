package com.erzbir.student.event;

import com.erzbir.event.AbstractEvent;
import com.erzbir.event.Event;
import com.erzbir.student.entity.User;

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
