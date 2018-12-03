package com.example.demo.dao;

import com.example.demo.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by LXX on 2018/11/28.
 */
@Repository
public interface PermissionDao extends JpaRepository<Permission,Integer> {

    Permission findPermissionByPermissionName(String permissionName);
}
