package com.jt.msg.dao;

/**
 * since 2015/4/28.
 */
public class DaoFactory {

    public static IUserDao userDao() {
        return new UserDao();
    }
}
