package com.jt.msg.dao;

/**
 * since 2015/4/28.
 */
public final class DaoFactory {

    public static IUserDao userDao() {
        return new UserDao();
    }

    public static IMsgDao msgDao() {
        return new MsgDao();
    }
}
