package com.erzbir.backend.db;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erzbir.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Erzbir
 * @Data: 2024/5/29 09:42
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
