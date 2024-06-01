package com.erzbir.sys.component;

import com.erzbir.sys.entity.Student;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public interface StudentManageComponent extends IComponent {
    void add(Student student);

    void remove(Student student);

    boolean contains(Student student);

    void update(Student student);

    List<Student> getStudents();
}
