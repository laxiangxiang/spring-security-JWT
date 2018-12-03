package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.MyUserDetails;
import com.example.demo.domain.Permission;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.exception.WrongUsernameException;
import com.example.demo.util.AuthErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by LXX on 2018/12/1.
 */
@Slf4j
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserDao userDao;

    /**
     * 根据用户名登录
     * @param s 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userOptional = userDao.findUserByUserName(s);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            //级联查询(即时加载)
            List<Role> roles = user.getRoles();
            List<Permission> permissions = new ArrayList<>();
            for (Role role:roles){
                //级联查询
                List<Permission> permissionList = role.getPermissions();
//                role.setPermissions(permissionList);
            }
//            user.setRoles(roles);
            UserDetails userDetails = new MyUserDetails(user);
//            List<GrantedAuthority> authorities = (List<GrantedAuthority>) userDetails.getAuthorities();
            return userDetails;
        }else {
            log.error("用户不存在");
            throw new WrongUsernameException(AuthErrorEnum.LOGIN_NAME_ERROR.getMessage());
        }
    }
}
