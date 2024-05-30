package com.erzbir.student.core.component;

import com.erzbir.student.annotation.Component;
import com.erzbir.student.component.AbstractComponent;
import com.erzbir.student.component.StudentManageComponent;
import com.erzbir.student.entity.StudentContainer;
import com.erzbir.student.core.entity.StudentContainerProvider;
import com.erzbir.student.entity.IStudent;

import java.util.List;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
@Component
public class DefaultStudentManageComponent extends AbstractComponent implements StudentManageComponent {
    private StudentContainer studentContainer;

    @Override
    public void add(IStudent student) {
        studentContainer.add(student);
    }

    @Override
    public void remove(IStudent student) {
        studentContainer.remove(student.getId());
    }

    @Override
    public boolean contains(IStudent student) {
        return studentContainer.contains(student);
    }

    @Override
    public void update(IStudent student) {
        studentContainer.update(student);
    }

    @Override
    public List<IStudent> getStudents() {
        return studentContainer.getItems().values().stream().toList();
    }

    @Override
    public void init() {
        studentContainer = StudentContainerProvider.getImpl();
        isInit.set(true);
    }
}
