package com.erzbir.student.entity;

import java.io.Serializable;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class Student implements Serializable {
    private Long id = 0L;
    private String name = "";
    private String gender = "";
    private String major = "";
    private String grade = "";

    public Student(Long id, String name, String gender, String major, String grade) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.major = major;
        this.grade = grade;
    }

    private Student() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String sex) {
        this.gender = sex;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public static class Builder {
        private final Student student;

        public Builder() {
            student = new Student();
        }

        public Builder id(Long id) {
            student.setId(id);
            return this;
        }

        public Builder name(String name) {
            student.setName(name);
            return this;
        }

        public Builder gender(String gender) {
            student.setGender(gender);
            return this;
        }

        public Builder major(String major) {
            student.setMajor(major);
            return this;
        }

        public Builder grade(String grade) {
            student.setGrade(grade);
            return this;
        }

        public Student build() {
            return student;
        }
    }
}