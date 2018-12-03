package com.example.demo.filter;

import com.example.demo.exception.IllegalTokenAuthenticationException;
import com.example.demo.exception.NoneTokenException;
import com.example.demo.util.AuthErrorEnum;
import com.example.demo.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LXX on 2018/12/3.
 */
@Slf4j
public class MyLogoutHandler implements LogoutHandler{

    private String tokenHeader;

    private String head;

    public MyLogoutHandler(String tokenHeader, String head) {
        this.tokenHeader = tokenHeader;
        this.head = head;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.info("执行登出操作...");
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            log.warn("请先登录");
            throw new NoneTokenException(AuthErrorEnum.TOKEN_NEEDED.getMessage());
        }
        if (!token.startsWith(head)){
            log.warn("token信息不合法");
            throw new IllegalTokenAuthenticationException(AuthErrorEnum.AUTH_HEADER_ERROR.getMessage());
        }
        Claims claims = JwtUtil.getClaim(token.substring(head.length()));
        if (claims == null){
            request.setAttribute("userName",null);
        }else {
            String userName = claims.getSubject();
            request.setAttribute("userName",userName);
        }
    }
}
