package com.example.demo.filter;

import org.springframework.security.web.authentication.logout.LogoutFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by LXX on 2018/12/3.
 * 默认处理登出URL为/logout,也可以自定义登出URL
 */
public class MyLogoutFilter extends LogoutFilter{

    public MyLogoutFilter(MyLogoutSuccessHandler logoutSuccessHandler, MyLogoutHandler logoutHandler,String filterProcessesUrl) {
        super(logoutSuccessHandler, logoutHandler);
        //更改默认的登出URL
//        super.setFilterProcessesUrl(filterProcessesUrl);
    }

    /**
     * 使用此构造方法，会使用默认的SimpleUrlLogoutSuccessHandler
     * 在登出成功后重定向到指定的logoutSuccessUrl
     * @param logoutSuccessUrl
     * @param handler
     */
    public MyLogoutFilter(String logoutSuccessUrl, MyLogoutHandler handler) {
        super(logoutSuccessUrl, handler);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
    }
}
