package com.erzbir.student.event;

import com.erzbir.event.Event;
import com.erzbir.student.entity.IStudent;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public interface StudentEvent extends Event {
    @Override
    IStudent getSource();
}
