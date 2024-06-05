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
@RequestMapping(value = "/user", produces = "application/json")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public Response<List<User>> list() {
        return Response.ok(userService.list());
    }

    @GetMapping("/get")
    public Response<User> get(@RequestParam String username) {
        return Response.ok(userService.getById(username));
    }

    @PostMapping("/update")
    public Response<Boolean> update(@RequestBody User user) {
        return Response.ok(userService.updateById(user));
    }

    @GetMapping("/delete")
    public Response<Boolean> delete(@RequestParam String username) {
        return Response.ok(userService.removeById(username));
    }
}
