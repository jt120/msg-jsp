package com.jt.msg.dao;

import com.jt.msg.bean.MsgException;
import com.jt.msg.bean.Pager;
import com.jt.msg.bean.SystemContext;
import com.jt.msg.bean.User;
import com.jt.msg.utils.DbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * since 2015/4/27.
 */
public class UserDao implements IUserDao {

    private static final Logger log = LoggerFactory.getLogger(UserDao.class);


    @Override
    public int add(User user) {
        //    int count = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        //rs = stmt.getGeneratedKeys();
        User u = fetchByUsername(user.getUsername());
        log.info("add user {}", user);
        if (u != null) {
            throw new MsgException("用户名已存在");
        }
        Connection conn = null;
        PreparedStatement pst = null;
        String sql = "insert into t_user " +
                "(username, password, nickname, email, status, type, create_time) values " +
                "(?,?,?,?,?,?,?)";
        try {
            conn = DbUtils.getConn();
            pst = conn.prepareStatement(sql);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getNickname());
            pst.setString(4, user.getEmail());
            pst.setInt(5, user.getStatus());
            pst.setInt(6, user.getType());
            pst.setTimestamp(7, new Timestamp(user.getCreateTime().getTime()));
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(conn);
            DbUtils.close(pst);
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        Connection conn = null;
        PreparedStatement pst = null;
        String sql = "delete from t_user where id=?";
        try {
            conn = DbUtils.getConn();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(conn);
            DbUtils.close(pst);
        }
        return 0;
    }

    @Override
    public int update(User user) {
        Connection conn = null;
        PreparedStatement pst = null;
        String sql = "update t_user set password=?, nickname=?, status=?, type=? where id=?";
        try {
            conn = DbUtils.getConn();
            pst = conn.prepareStatement(sql);
            pst.setString(1, user.getPassword());
            pst.setString(2, user.getNickname());
            pst.setInt(3, user.getStatus());
            pst.setInt(4, user.getType());
            pst.setInt(5, user.getId());
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(conn);
            DbUtils.close(pst);
        }
        return 0;
    }

    @Override
    public User fetchById(int id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select id, username, password, nickname, email, status, type, create_time createTime " +
                "from t_user where id=?";
        User user = null;
        try {
            conn = DbUtils.getConn();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setNickname(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setStatus(rs.getInt(6));
                user.setType(rs.getInt(7));
                user.setCreateTime(rs.getTimestamp(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(conn);
            DbUtils.close(pst);
        }
        return user;
    }

    @Override
    public User fetchByUsername(String username) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select id, username, password, nickname, email, status, type, create_time createTime " +
                "from t_user where username=?";
        User user = null;
        try {
            conn = DbUtils.getConn();
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            rs = pst.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setNickname(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setStatus(rs.getInt(6));
                user.setType(rs.getInt(7));
                user.setCreateTime(rs.getTimestamp(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(rs);
            DbUtils.close(pst);
            DbUtils.close(conn);
        }
        return user;
    }

    @Override
    public Pager<User> list() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int pageIndex = SystemContext.getPageIndex();
        int pageSize = SystemContext.getPageSize();
        int pageOffset = 0;
        int pageCount = 0;
        int pageRecords = 0;
        String sql = "select id, username, password, nickname, email, status, type, create_time createTime " +
                "from t_user limit ?,?";
        String sqlCount = "select count(*) from t_user";
        Pager<User> pager = new Pager<User>();
        try {
            conn = DbUtils.getConn();
            pst = conn.prepareStatement(sql);
            List<User> userList = new ArrayList<User>();
            pager.setData(userList);
            pageOffset = (pageIndex-1) * pageSize;
            pst.setInt(1, pageOffset);
            pst.setInt(2, pageSize);
            rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setNickname(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setStatus(rs.getInt(6));
                user.setType(rs.getInt(7));
                user.setCreateTime(rs.getTimestamp(8));
                userList.add(user);
            }

            rs = pst.executeQuery(sqlCount);
            if (rs.next()) {
                pageRecords = rs.getInt(1);
            }
            pageCount = (pageRecords - 1) / pageSize + 1;
            pager.setPageOffset(pageOffset);
            pager.setPageIndex(pageIndex);
            pager.setPageSize(pageSize);
            pager.setTotalPages(pageCount);
            pager.setTotalRecords(pageRecords);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(rs);
            DbUtils.close(pst);
            DbUtils.close(conn);
        }
        return pager;
    }

    @Override
    public User login(String username, String password) {
        User user = fetchByUsername(username);
        if (user == null) {
            throw new MsgException("用户名不存在");
        }
        if (!user.getPassword().equals(password)) {
            throw new MsgException("用户名或密码错误");
        }
        return user;
    }
}
