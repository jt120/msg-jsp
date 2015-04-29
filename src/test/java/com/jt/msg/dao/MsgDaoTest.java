package com.jt.msg.dao;

import com.jt.msg.bean.Msg;
import com.jt.msg.bean.Pager;
import com.jt.msg.bean.SystemContext;
import com.jt.msg.bean.User;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * since 2015/4/29.
 */
public class MsgDaoTest {

    private Msg msg() {
        Msg msg = new Msg();
        msg.setUserId(1);
        msg.setTitle("hello");
        msg.setContent("hello msg");
        msg.setCreateTime(new Date());
        msg.setUpdateTime(new Date());
        return msg;
    }

    IMsgDao msgDao = DaoFactory.msgDao();

    @Test
    public void testAdd() throws Exception {
        int count = msgDao.add(msg());
        assertTrue(count > 0);
    }

    @Test
    public void testDelete() throws Exception {
        Msg msg = msgDao.fetchByTitle("hello");
        assertNotNull(msg);
        msgDao.delete(msg.getId());
        Msg msg1 = msgDao.fetchByTitle("hello");
        assertNull(msg1);
    }

    @Test
    public void testUpdate() throws Exception {
        int count = msgDao.add(msg());
        assertTrue(count > 0);
        Msg msg = msgDao.fetchByTitle("hello");
        msg.setContent("good");
        msg.setUpdateTime(new Date());
        int update = msgDao.update(msg);
        assertTrue(update>0);
    }


    @Test
    public void testFetch() throws Exception {
        Msg msg = msgDao.fetchByTitle("hello");
        Msg msg1 = msgDao.fetchById(msg.getId());
        assertEquals(msg.getTitle(),msg1.getTitle());
    }

    @Test
    public void testPage() throws Exception {
        SystemContext.setPageIndex(1);
        SystemContext.setPageSize(2);
        Pager<Msg> list = msgDao.list();
        System.out.println(list.getData());
    }
}
