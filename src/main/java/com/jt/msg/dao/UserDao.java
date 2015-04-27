package com.jt.msg.dao;

import com.jt.msg.bean.MsgException;
import com.jt.msg.bean.Pager;
import com.jt.msg.bean.SystemContext;
import com.jt.msg.bean.User;
import com.jt.msg.utils.DbUtils;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * since 2015/4/27.
 */
public class UserDao implements IUserDao {
    @Override
    public int add(User user) {
        User u = fetchByUsername(user.getUsername());
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
        User u = fetchByUsername(user.getUsername());
        if (u != null) {
            throw new MsgException("用户名已存在");
        }
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
        String sql = "select id, username, password, nickname, email, status, type, create_time createTime" +
                "from t_user where id=?";
        User user = new User();
        try {
            conn = DbUtils.getConn();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
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
            DbUtils.close(conn);
            DbUtils.close(pst);
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
        int pageCount = 0;
        int pageRecords = 0;
        String sql = "select id, username, password, nickname, email, status, type, create_time createTime" +
                "from t_user limit ?,?";
        String sqlCount = "select count(*) from t_user";
        Pager<User> pager = new Pager<User>();
        try {
            conn = DbUtils.getConn();
            pst = conn.prepareStatement(sql);
            List<User> userList = new ArrayList<User>();
            if (pageIndex > 0) {
                pageIndex = pageSize - 1;
            }
            pst.setInt(1, pageIndex);
            pst.setInt(2, pageSize);
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
            pager.setData(userList);

            rs = pst.executeQuery(sqlCount);
            if (rs.next()) {
                pageRecords = rs.getInt(1);
            }
            pageCount = (pageRecords-1)/pageSize + 1;
            pager.setPageIndex(pageIndex);
            pager.setPageSize(pageSize);
            pager.setTotalPages(pageCount);
            pager.setTotalRecords(pageRecords);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(conn);
            DbUtils.close(pst);
        }
        return pager;
    }
}
