package com.example.demo.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by LXX on 2018/12/1.
 */
public class MyUserDetails extends User implements UserDetails{

    public MyUserDetails(User user) {
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Permission> permissions = new ArrayList<>();
        List<Role> roles = super.getRoles();
        for (Role role : roles){
            List<Permission> permissionList = role.getPermissions();
            if (permissionList != null || permissionList.size() != 0){
                for (Permission permission:permissionList){
                    if (!permissions.contains(permission)){
                        permissions.add(permission);
                    }
                }
            }
        }
        if (permissions == null || permissions.size() == 0){

        }else {
            for (Permission permission:permissions){
                //这里使用的是权限名称，也可以使用权限id或者编号。区别在于在使用@PreAuthorize("hasAuthority('权限名称')")
                authorities.add(new SimpleGrantedAuthority(permission.getPermissionName()));
            }
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }
}
