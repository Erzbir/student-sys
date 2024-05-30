package com.erzbir.student.event;

import com.erzbir.student.entity.IStudent;
import com.erzbir.event.AbstractEvent;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class StudentAddEvent extends AbstractEvent implements StudentEvent {
    public StudentAddEvent(IStudent source) {
        super(source);
    }

    @Override
    public IStudent getSource() {
        return (IStudent) source;
    }
}
