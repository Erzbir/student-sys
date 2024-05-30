package com.erzbir.student.component;

import com.erzbir.student.entity.IStudent;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public interface StudentManageComponent extends IComponent {
    void add(IStudent student);

    void remove(IStudent student);

    boolean contains(IStudent student);

    void update(IStudent student);

    List<IStudent> getStudents();

    default Stream<IStudent> stream() {
        return getStudents().stream();
    }
}
