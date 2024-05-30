package com.erzbir.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erzbir.backend.db.UserMapper;
import com.erzbir.backend.entity.User;
import com.erzbir.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Erzbir
 * @Data: 2024/5/29 09:52
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public Boolean auth(String username, String password) {
        User user = getById(username);
        if (user == null) {
            return false;
        }
        log.info(user.toString());
        log.info(password);
        log.info(String.valueOf(user.getPassword().equals(password)));
        return user.getPassword().equals(password);
    }
}
