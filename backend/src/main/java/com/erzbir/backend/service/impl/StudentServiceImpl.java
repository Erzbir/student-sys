package com.erzbir.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erzbir.backend.db.StudentMapper;
import com.erzbir.backend.entity.Student;
import com.erzbir.backend.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @author Erzbir
 * @Data: 2024/5/29 09:53
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
