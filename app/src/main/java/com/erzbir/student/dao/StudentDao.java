package com.erzbir.student.dao;

import androidx.room.*;
import com.erzbir.student.entity.Student;
import com.erzbir.student.entity.User;

import java.util.List;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
@Dao
public interface StudentDao {
    @Query("SELECT * FROM Student")
    List<Student> getAll();

    @Query("SELECT * FROM Student WHERE id = :id")
    Student findById(Long id);

    @Update
    void update(Student student);

    @Insert
    void insertAll(Student... students);

    @Insert
    void insert(Student student);

    @Delete
    void delete(Student student);
}
