package com.erzbir.student.event;

import com.erzbir.student.entity.IUser;
import com.erzbir.event.AbstractEvent;
import com.erzbir.event.Event;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class UserLoginEvent extends AbstractEvent implements Event {
    public UserLoginEvent(IUser source) {
        super(source);
    }

    @Override
    public IUser getSource() {
        return (IUser) source;
    }
}
