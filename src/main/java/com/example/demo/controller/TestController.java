package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.MyUserDetails;
import com.example.demo.domain.User;
import com.example.demo.service.TestService;
import com.sun.media.jfxmedia.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;

/**
 * Created by LXX on 2018/11/28.
 */
@RestController
@RequestMapping("/testApi")
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping(value = "/addData")
    public void addData(){
        testService.addData();
    }

    @RequestMapping(value = "/getData")
    public User getData(){
        return testService.getData();
    }

    @RequestMapping(value = "/deleteData")
    public void deleteData(){
        testService.deleteData();
    }

    /////////////////////////基于内存的用户认证///////////////////////////////////
    @RolesAllowed({"USER"})
    @RequestMapping(value = "/authorize1",produces = MediaType.APPLICATION_JSON_VALUE)
    public String authorize1(){
        return "authorized success";
    }

    @DenyAll
    @RequestMapping(value = "/authorize2",produces = MediaType.APPLICATION_JSON_VALUE)
    public String authorize2(){
        return "authorized success";
    }

    @RolesAllowed({"ADMIN"})
    @RequestMapping(value = "/authorize3",produces = MediaType.APPLICATION_JSON_VALUE)
    public String authorize3(){
        return "authorized success";
    }

    /////////////////////需要定义自己的userDetailService////////////////
    @PreAuthorize("hasAuthority('p_1')")
    @RequestMapping(value = "/authorize4",produces = MediaType.APPLICATION_JSON_VALUE)
    public String authorize4(){
        return "authorized success";
    }

}
