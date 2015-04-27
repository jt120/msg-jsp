package com.jt.msg.dao;

import com.jt.msg.bean.Pager;
import com.jt.msg.bean.User;

/**
 * since 2015/4/27.
 */
public interface IUserDao {
    int add(User user);

    int delete(int id);

    int update(User user);

    User fetchById(int id);

    User fetchByUsername(String username);

    Pager<User> list();

}
