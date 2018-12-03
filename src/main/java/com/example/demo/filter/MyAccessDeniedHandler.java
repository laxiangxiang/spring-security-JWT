package com.example.demo.filter;

import com.example.demo.util.AuthErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LXX on 2018/10/23.
 * 登录后的无权访问在此处理
 */
@Slf4j
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        log.error("当前用户没有访问该资源的权限：{}",e.getMessage());
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.getWriter().write(AuthErrorEnum.ACCESS_DENIED.getMessage());
    }
}
