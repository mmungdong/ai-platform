package com.platform.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MungDong
 * @create 2024-03-23-17:01
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        // 设置响应状态码为403 Forbidden
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        // 跳转到自定义的被拒绝页面
        response.sendRedirect("/error/403.html");
    }
}
