package com.erzbir.student.client.req;

/**
 * @author Erzbir
 * @version 1.0
 * @since 2024/5/31
 */
public interface QueryReqs {
    record QueryStudentById(Long id) {

    }

    record QueryAllStudents() {

    }

    record QueryUserByName(String username) {

    }

    record QueryAllUsers() {

    }

}

