package com.jt.msg.dao;

import com.jt.msg.bean.Msg;
import com.jt.msg.bean.Pager;

/**
 * since 2015/4/28.
 */
public interface IMsgDao {

    int add(Msg msg);

    int delete(int id);

    int update(Msg msg);

    Msg fetchById(int id);

    Msg fetchByTitle(String title);

    Pager<Msg> list(String title);

    Pager<Msg> list();
}
