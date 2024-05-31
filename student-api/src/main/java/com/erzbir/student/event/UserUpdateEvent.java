package com.erzbir.student.event;

import com.erzbir.event.AbstractEvent;
import com.erzbir.student.entity.User;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class UserUpdateEvent extends AbstractEvent implements UserEvent {
    public UserUpdateEvent(User source) {
        super(source);
    }

    @Override
    public User getSource() {
        return (User) source;
    }
}
