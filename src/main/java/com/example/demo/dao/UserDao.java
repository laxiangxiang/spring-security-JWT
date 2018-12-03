package com.example.demo.dao;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by LXX on 2018/11/28.
 */
@Repository
public interface UserDao extends JpaRepository<User,Integer>{

    Optional<User> findUserByUserName(String username);

}
