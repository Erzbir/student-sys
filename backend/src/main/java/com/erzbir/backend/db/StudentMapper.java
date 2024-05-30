package com.erzbir.backend.db;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erzbir.backend.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Erzbir
 * @Data: 2024/5/29 09:51
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    Student add(Student student);

    Student update(Student student);

    Student delete(Long id);

    List<Student> getAllStudents();

    Student getStudentById(Long id);
}
