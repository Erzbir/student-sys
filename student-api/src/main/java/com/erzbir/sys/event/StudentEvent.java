package com.erzbir.sys.event;

import com.erzbir.event.Event;
import com.erzbir.sys.entity.Student;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public interface StudentEvent extends Event {
    @Override
    Student getSource();
}
