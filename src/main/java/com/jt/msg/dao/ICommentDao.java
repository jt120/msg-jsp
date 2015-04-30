package com.jt.msg.dao;

import com.jt.msg.bean.Comment;
import com.jt.msg.bean.Pager;

/**
 * since 2015/4/30.
 */
public interface ICommentDao {

    int add(Comment comment);

    int delete(int id);

    Pager<Comment> listByMsgId(int msgId);

    Pager<Comment> listByUserId(int userId);
}
