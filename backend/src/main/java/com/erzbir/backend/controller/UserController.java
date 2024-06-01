package com.erzbir.backend.controller;

import com.erzbir.backend.annotation.StringResponse;
import com.erzbir.backend.entity.User;
import com.erzbir.backend.service.UserService;
import com.erzbir.backend.util.Response;
import org.springframework.web.bind.annotation.*;

/**
 * @author Erzbir
 * @Data: 2024/5/29 09:53
 */
@RestController
@RequestMapping(value = "/user", produces = "application/json")
@StringResponse
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public Object list() {
        return Response.ok(userService.list());
    }

    @GetMapping("/get")
    public Object get(@RequestParam String username) {
        return Response.ok(userService.getById(username));
    }


    @PostMapping("/register")
    public Object register(@RequestBody User user) {
        return Response.ok(userService.save(user));
    }
}
