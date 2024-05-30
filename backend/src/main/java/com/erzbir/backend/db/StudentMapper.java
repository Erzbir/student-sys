package com.erzbir.backend.db;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erzbir.backend.entity.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Erzbir
 * @Data: 2024/5/29 09:51
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
