package com.erzbir.backend.controller;

import com.erzbir.backend.entity.User;
import com.erzbir.backend.service.UserService;
import com.erzbir.backend.util.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Erzbir
 * @Data: 2024/5/29 09:53
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public Response<List<User>> list() {
        return Response.ok(userService.list());
    }


    @PostMapping("/register")
    public Response<Boolean> register(@RequestBody User user) {
        return Response.ok(userService.save(user));
    }
}
