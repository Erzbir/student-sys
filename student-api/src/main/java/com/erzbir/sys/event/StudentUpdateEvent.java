package com.erzbir.sys.event;

import com.erzbir.event.AbstractEvent;
import com.erzbir.sys.entity.Student;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class StudentUpdateEvent extends AbstractEvent implements StudentEvent {
    public StudentUpdateEvent(Student source) {
        super(source);
    }

    @Override
    public Student getSource() {
        return (Student) source;
    }
}
