package com.lrq.service;


import com.lrq.dao.UserDao;
import com.lrq.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;

    public void insert(User user) {
        userDao.insert(user);
    }


    public boolean update(User user) {
        userDao.update(user);
        return true;
    }


    public boolean deleteByName(String name) {
        userDao.deleteByName(name);
        return true;
    }


    public User selectByName(String name) {
        User res = userDao.selectByName(name);
        return res;

    }

    public User selectByCode(String code) {
        User res = userDao.selectByCode(code);
        return res;

    }

    public List<User> selectList() {
        List<User> user_list = userDao.selectList();
        return user_list;
    }
}
