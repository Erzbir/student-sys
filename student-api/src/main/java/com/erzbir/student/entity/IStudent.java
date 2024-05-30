package com.erzbir.student.entity;

import java.io.Serializable;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public interface IStudent extends Serializable {
    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getGender();

    void setGender(String sex);

    String getMajor();

    void setMajor(String major);

    String getGrade();

    void setGrade(String grade);
}
