package com.erzbir.student.event;

import com.erzbir.event.AbstractEvent;
import com.erzbir.student.entity.IStudent;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class StudentUpdateEvent extends AbstractEvent implements StudentEvent {
    public StudentUpdateEvent(IStudent source) {
        super(source);
    }

    @Override
    public IStudent getSource() {
        return (IStudent) source;
    }
}
