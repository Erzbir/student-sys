package com.erzbir.student.event;

import com.erzbir.event.AbstractEvent;
import com.erzbir.student.entity.Student;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class StudentAddEvent extends AbstractEvent implements StudentEvent {
    public StudentAddEvent(Student source) {
        super(source);
    }

    @Override
    public Student getSource() {
        return (Student) source;
    }
}
