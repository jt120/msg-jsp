package com.jt.msg.dao;

import com.jt.msg.bean.Msg;

/**
 * since 2015/4/28.
 */
public interface IMsgDao {

    int add(Msg msg);

    int delete(int id);

    int update(Msg msg);

    Msg fetchById(int id);
}
