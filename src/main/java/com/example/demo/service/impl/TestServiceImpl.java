package com.example.demo.service.impl;

import com.example.demo.dao.*;
import com.example.demo.domain.*;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by LXX on 2018/11/28.
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private ParentMenuDao parentMenuDao;

    @Autowired
    private ChildMenuDao childMenuDao;

    @Override
    public void addData() {
        ChildMenu childMenu1 = new ChildMenu();
        childMenu1.setChildMenuName("child_menu_1_1");
        childMenu1.setChildMenuNo("1-1");
        ChildMenu childMenu2 = new ChildMenu();
        childMenu2.setChildMenuName("child_menu_1_2");
        childMenu2.setChildMenuNo("1-2");
        ChildMenu childMenu3 = new ChildMenu();
        childMenu3.setChildMenuName("child_menu_2_1");
        childMenu3.setChildMenuNo("2-1");
        ChildMenu childMenu4 = new ChildMenu();
        childMenu4.setChildMenuName("child_menu_2_2");
        childMenu4.setChildMenuNo("2-2");
        ChildMenu childMenu5 = new ChildMenu();
        childMenu5.setChildMenuName("child_menu_3_1");
        childMenu5.setChildMenuNo("3-1");
        ParentMenu parentMenu1 = new ParentMenu();
        ParentMenu parentMenu2 = new ParentMenu();
        ParentMenu parentMenu3 = new ParentMenu();
        parentMenu1.setParentMenuName("parent_menu_1");
        parentMenu1.setParentMenuNo("1");
        parentMenu1.setChildMenus(Arrays.asList(childMenu1, childMenu2));
        childMenu1.setParentMenu(parentMenu1);
        childMenu2.setParentMenu(parentMenu1);
        parentMenu2.setParentMenuName("parent_menu_2");
        parentMenu2.setParentMenuNo("2");
        parentMenu2.setChildMenus(Arrays.asList(childMenu3, childMenu4));
        childMenu3.setParentMenu(parentMenu2);
        childMenu4.setParentMenu(parentMenu2);
        parentMenu3.setParentMenuName("parent_menu_3");
        parentMenu3.setParentMenuNo("3");
        parentMenu3.setChildMenus(Arrays.asList(childMenu1,childMenu2,childMenu3,childMenu4,childMenu5));
        childMenu5.setParentMenu(parentMenu3);
        Permission permission1 = new Permission();
        Permission permission2 = new Permission();
        Permission permission3 = new Permission();
        Role role1 = new Role();
        Role role2 = new Role();
        Role role3 = new Role();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        User user4 = new User();
        permission1.setPermissionName("p_1");
        permission1.setPermissionNo("1");
        permission1.setParentMenus(Arrays.asList(parentMenu1));
        permission2.setPermissionName("p_2");
        permission2.setPermissionNo("2");
        permission2.setParentMenus(Arrays.asList(parentMenu2));
        permission3.setPermissionName("p_3");
        permission3.setPermissionNo("3");
        permission3.setParentMenus(Arrays.asList(parentMenu3));
        role1.setRoleName("管理员");
        role1.setRoleNo("admin");
        role1.setPermissions(Arrays.asList(permission1,permission2,permission3));
        role2.setRoleName("普通用户角色1");
        role2.setRoleNo("role1");
        role2.setPermissions(Arrays.asList(permission1));
        role3.setRoleName("普通用户角色2");
        role3.setRoleNo("role2");
        role3.setPermissions(Arrays.asList(permission2));
        user1.setUserName("user1");
        user1.setUserNo("NO1");
        user1.setPassword("123456");
        user1.setRoles(Arrays.asList(role1,role2,role3));
        user2.setUserName("user2");
        user2.setUserNo("NO2");
        user2.setPassword("123456");
        user2.setRoles(Arrays.asList(role2));
        user3.setUserName("user3");
        user3.setUserNo("NO3");
        user3.setPassword("123456");
        user3.setRoles(Arrays.asList(role3));
        user4.setUserName("user4");
        user4.setUserNo("NO4");
        user4.setPassword("123456");
        user4.setRoles(Arrays.asList(role1));
        userDao.save(user1);
        userDao.save(user2);
        userDao.save(user3);
        userDao.save(user4);
    }

    @Override
    public User getData() {
        Optional<User> userOptional = userDao.findById(1);
        User user = null;
        if (userOptional.isPresent()){
            user = userOptional.get();
        }
        System.out.println(user);
        List<Role> roles = user.getRoles();
        for (Role role : roles){
            System.out.println(role);
            List<User> users = role.getUsers();
            for (User user1: users){
                System.out.println("--"+user1);
            }
            System.out.println("------------------------------------------------------------------");
            List<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions){
                System.out.println(permission);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                List<ParentMenu> parentMenus = permission.getParentMenus();
                for (ParentMenu parentMenu : parentMenus){
                    System.out.println(parentMenu);
                    System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                    List<ChildMenu> childMenus = parentMenu.getChildMenus();
                    for (ChildMenu childMenu : childMenus){
                        System.out.println(childMenu);
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    }
                }
            }
        }
        return user;
    }

    @Override
    public void deleteData() {
        userDao.deleteById(4);
        Optional<Role> roleOptional = roleDAO.findById(1);
        if (!roleOptional.isPresent()){
            System.out.println("删除出错，删除用户不能级联删除角色");
        }
        parentMenuDao.deleteById(1);
        Optional<Permission> permissionOptional = permissionDao.findById(1);
        if (permissionOptional.isPresent()){
            Permission permission = permissionOptional.get();
            List<Role> roles = permission.getRoles();
            if (roles == null || roles.size() == 0){
                System.out.println("该权限没有角色拥有");
            }else {
                for (Role role:roles){
                    System.out.println(role);
                }
            }
            List<ParentMenu> parentMenus = permission.getParentMenus();
            if (parentMenus == null || parentMenus.size() == 0){
                System.out.println("该权限没有可以查看的菜单");
            }else {
                for (ParentMenu parentMenu:parentMenus){
                    System.out.println(parentMenu);
                }
            }
        }
    }
}
