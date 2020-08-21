package com.lrq.dao;

import com.lrq.model.User;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface UserDao {
    //列出所有的已经激活的用户，可以实现一下分页功能
    List<User> selectList() ;

    //通过用户名查找用户
    User selectByName(String name);

    //通过激活码查找用户
    User selectByCode(String code);


    int update(User user);


    int insert(User user);


    int deleteByName(String name);
}