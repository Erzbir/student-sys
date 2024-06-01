package com.erzbir.sys.core.component;

import com.erzbir.sys.annotation.Component;
import com.erzbir.sys.client.Client;
import com.erzbir.sys.client.req.AddReqs;
import com.erzbir.sys.client.req.DeleteReqs;
import com.erzbir.sys.client.req.QueryReqs;
import com.erzbir.sys.client.req.UpdateReqs;
import com.erzbir.sys.client.resp.Response;
import com.erzbir.sys.component.AbstractComponent;
import com.erzbir.sys.component.StudentManageComponent;
import com.erzbir.sys.entity.Student;

import java.util.List;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
@Component
public class DefaultStudentManageComponent extends AbstractComponent implements StudentManageComponent {
    private final Client client = Client.INSTANCE;

    @Override
    public void add(Student student) {
        client.addStudent(new AddReqs.AddStudent(student));
    }

    @Override
    public void remove(Student student) {
        client.deleteStudent(new DeleteReqs.DeleteStudent(student.getId()));
    }

    @Override
    public boolean contains(Student student) {
        Response<Student> response = client.queryStudentById(new QueryReqs.QueryStudentById(student.getId()));
        return !response.getData().getName().isEmpty();
    }

    @Override
    public void update(Student student) {
        client.updateStudent(new UpdateReqs.UpdateStudent(student));
    }

    @Override
    public List<Student> getStudents() {
        return client.queryAllStudents(new QueryReqs.QueryAllStudents()).getData();
    }

    @Override
    public void init() {
        isInit.set(true);
    }
}
