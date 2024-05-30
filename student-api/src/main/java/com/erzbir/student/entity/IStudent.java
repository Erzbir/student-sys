package com.erzbir.student.entity;

import java.io.Serializable;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public interface IStudent extends Serializable {
    Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);

    public String getSex();

    public void setSex(String sex);

    public String getMajor();

    public void setMajor(String major);

    public String getGrade();

    public void setGrade(String grade);
}
