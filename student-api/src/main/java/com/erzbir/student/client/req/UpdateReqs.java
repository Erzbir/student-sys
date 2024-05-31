package com.erzbir.student.client.req;


import com.erzbir.student.entity.Student;
import com.erzbir.student.entity.User;

/**
 * @author Erzbir
 * @version 1.0
 * @since 2024/5/31
 */
public interface UpdateReqs {

    record UpdateStudent(Student student) {

    }

    record UpdateUser(User user) {

    }
}
