package com.erzbir.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.erzbir.backend.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

/**
 * @author Erzbir
 * @Data: 2024/5/28 14:16
 */
public interface UserService extends IService<User> {
    Boolean auth(@NotBlank String username, @NotBlank String password);
}
