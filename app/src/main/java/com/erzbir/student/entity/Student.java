package com.erzbir.student.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
@Entity(tableName = "student")
public class Student implements IStudent {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "sex")
    private String sex;
    @ColumnInfo(name = "major")
    private String major;
    @ColumnInfo(name = "grade")
    private String grade;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSex() {
        return sex;
    }

    @Override
    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String getMajor() {
        return major;
    }

    @Override
    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String getGrade() {
        return grade;
    }

    @Override
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

        public Builder sex(String sex) {
            student.setSex(sex);
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