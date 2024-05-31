package com.erzbir.student.client.req;

import com.erzbir.student.entity.Student;
import com.erzbir.student.entity.User;

/**
 * @author Erzbir
 * @version 1.0
 * @since 2024/5/31
 */
public interface AddReqs {
    record AddStudent(Student student) {

    }

    record AddUser(User user) {

    }
}