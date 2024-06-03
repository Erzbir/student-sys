package com.erzbir.backend.controller;

import com.erzbir.backend.annotation.JsonResponse;
import com.erzbir.backend.service.UserService;
import com.erzbir.backend.util.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Erzbir
 * @Data: 2024/5/29 09:53
 */
@RestController
@RequestMapping(value = "/user", produces = "application/json")
@JsonResponse
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
}
