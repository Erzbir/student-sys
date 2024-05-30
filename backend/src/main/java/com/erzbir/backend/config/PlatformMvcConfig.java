package com.erzbir.backend.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Erzbir
 * @Data: 2024/5/29 10:46
 */
@Configuration
public class PlatformMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        AccessInterceptor accessInterceptor = new AccessInterceptor();
        registry.addInterceptor(accessInterceptor).addPathPatterns("/**");
    }
}

class AccessInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession();
        session.removeAttribute("msg");
        String uri = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/"));
        String[] access = new String[]{"login", "logout", "register"};
        for (String action : access) {
            if (uri.toLowerCase().contains(action.toLowerCase())) {
                return true;
            }
        }
        if (session.getAttribute("token") == null) {
            session.setAttribute("msg", "请先登录");
            return false;
        }
        return true;
    }
}