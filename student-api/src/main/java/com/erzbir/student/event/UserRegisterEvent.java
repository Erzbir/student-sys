package com.erzbir.student.event;

import com.erzbir.student.entity.IUser;
import com.erzbir.event.AbstractEvent;
import com.erzbir.event.Event;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class UserRegisterEvent extends AbstractEvent implements Event {
    public UserRegisterEvent(IUser source) {
        super(source);
    }

    @Override
    public IUser getSource() {
        return (IUser) source;
    }
}
