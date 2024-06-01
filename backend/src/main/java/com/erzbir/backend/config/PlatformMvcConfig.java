package com.erzbir.backend.config;

import com.erzbir.backend.entity.User;
import com.erzbir.backend.service.UserService;
import com.erzbir.backend.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Erzbir
 * @Data: 2024/5/29 10:46
 */
@Configuration
public class PlatformMvcConfig implements WebMvcConfigurer {
    private final UserService userService;

    public PlatformMvcConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        AccessInterceptor accessInterceptor = new AccessInterceptor(userService);
        registry.addInterceptor(accessInterceptor).addPathPatterns("/**");
    }

    static class AccessInterceptor implements HandlerInterceptor {
        private final UserService userService;

        AccessInterceptor(UserService userService) {
            this.userService = userService;
        }

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
            HttpSession session = request.getSession();
            String uri = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/"));
            String[] access = new String[]{"login", "logout", "register"};
            for (String action : access) {
                if (uri.toLowerCase().contains(action.toLowerCase())) {
                    return true;
                }
            }
            Object usernameAttr = session.getAttribute("username");
            if (usernameAttr == null || !StringUtils.hasText(usernameAttr.toString())) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Login required");
                return false;
            }
            String token = request.getHeader("Authorization");
            if (!StringUtils.hasText(token)) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Authorization header missing");
                return false;
            }
            try {
                String username = JWTUtil.parseToken(token);
                User user = userService.getById(username);
                if (user == null) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid token");
                    return false;
                }
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Token parsing failed");
                return false;
            }
            return true;
        }
    }
}