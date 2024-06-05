package com.erzbir.backend.controller;

import cn.hutool.json.JSONUtil;
import com.erzbir.backend.context.LoginUser;
import com.erzbir.backend.entity.User;
import com.erzbir.backend.service.UserService;
import com.erzbir.backend.util.JWTUtil;
import com.erzbir.backend.util.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Erzbir
 * @Data: 2024/5/29 08:54
 */
@RestController
@RequestMapping(value = "/auth", produces = "application/json")
public class AuthController {
    private final UserService userService;
    private final HttpServletRequest request;

    public AuthController(UserService userService, HttpServletRequest request) {
        this.userService = userService;
        this.request = request;
    }

    @PostMapping("/login")
    public Response<String> login(@RequestBody User user) {
        Boolean auth = userService.auth(user.getUsername(), user.getPassword());
        if (!auth) {
            return Response.error("Auth failed");
        }
        HttpSession session = request.getSession();
        String token = JWTUtil.createToken(User.builder().username(user.getUsername()).build(), 10000);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        session.setAttribute("username", user.getUsername());
        LoginUser.setUser(user);
        return Response.ok(JSONUtil.toJsonStr(map));
    }
}
