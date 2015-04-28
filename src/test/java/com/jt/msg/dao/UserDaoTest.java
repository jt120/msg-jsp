package com.jt.msg.dao;

import com.jt.msg.bean.Pager;
import com.jt.msg.bean.SystemContext;
import com.jt.msg.bean.User;
import com.jt.msg.utils.EncryptUtil;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * since 2015/4/27.
 */
public class UserDaoTest {

    IUserDao userDao = new UserDao();

    private User user() {
        User u = new User();
        u.setUsername("b");
        u.setPassword(EncryptUtil.md5("b"));
        u.setNickname("b");
        u.setEmail("b@163.com");
        u.setType(1);
        u.setStatus(1);
        u.setCreateTime(new Date());
        return u;
    }

    @Test
    public void testAdmin() throws Exception {
        userDao.add(user());
    }


    @Test
    public void testAdd() throws Exception {
        int add = userDao.add(user());
        assertTrue(add > 0);
    }

    @Test
    public void testDelete() throws Exception {
        User user = userDao.fetchByUsername("admin");
        userDao.delete(user.getId());
        User user1 = userDao.fetchByUsername("admin");
        assertTrue(user1==null);
    }

    @Test
    public void testUpdate() throws Exception {
        User user = userDao.fetchByUsername("admin");
        user.setEmail("good@163.com");
        userDao.update(user);
        User user1 = userDao.fetchById(user.getId());
        assertEquals(user1.getEmail(),"good@163.com");
    }

    @Test
    public void testFetch() throws Exception {

        User user = userDao.fetchByUsername("admin");
        if (user != null) {
            userDao.delete(user.getId());
        }
        userDao.add(user());
        User user1 = userDao.fetchByUsername("admin");
        User u2 = user();
        assertEquals(user1, u2);

    }

    @Test
    public void testPage() throws Exception {
        SystemContext.setPageIndex(1);
        SystemContext.setPageSize(5);
        Pager<User> list = userDao.list();
        System.out.println(list.getData());
    }

}
