package com.erzbir.backend.controller;

import com.erzbir.backend.entity.User;
import com.erzbir.backend.service.UserService;
import com.erzbir.backend.util.JWTUtil;
import com.erzbir.backend.util.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Erzbir
 * @Data: 2024/5/29 08:54
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final HttpServletRequest request;

    public AuthController(UserService userService, HttpServletRequest request) {
        this.userService = userService;
        this.request = request;
    }

    @PostMapping("/login")
    public Response<String> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Boolean auth = userService.auth(username, password);
        if (!auth) {
            return Response.error("认证失败");
        }
        HttpSession session = request.getSession();
        String token = JWTUtil.createToken(User.builder().username(username).build());
        session.setAttribute("username", username);
        return Response.ok(token);
    }
}
