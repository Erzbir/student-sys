package com.erzbir.student.event;

import com.erzbir.event.Event;
import com.erzbir.student.entity.User;

/**
 * @author Erzbir
 * @version 1.0
 * @since 2024/5/31
 */
public interface UserEvent extends Event {
    @Override
    User getSource();
}
