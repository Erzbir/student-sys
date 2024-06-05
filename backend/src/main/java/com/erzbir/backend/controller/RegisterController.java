package com.erzbir.backend.controller;

import com.erzbir.backend.entity.User;
import com.erzbir.backend.service.UserService;
import com.erzbir.backend.util.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Erzbir
 * @version 1.0
 * @since 2024/6/3
 */
@RestController
@RequestMapping(value = "/user", produces = "application/json")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Response<Boolean> register(@RequestBody User user) {
        if (userService.getById(user.getUsername()) != null) {
            return Response.error("Username is already in use");
        }
        return Response.ok(userService.save(user));
    }
}
