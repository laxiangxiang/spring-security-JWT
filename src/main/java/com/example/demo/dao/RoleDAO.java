package com.example.demo.dao;

import com.example.demo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by LXX on 2018/11/28.
 */
@Repository
public interface RoleDAO extends JpaRepository<Role,Integer>{
}
