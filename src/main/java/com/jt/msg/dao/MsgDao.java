package com.jt.msg.dao;

import com.jt.msg.bean.Msg;
import com.jt.msg.bean.Pager;
import com.jt.msg.bean.SystemContext;
import com.jt.msg.bean.User;
import com.jt.msg.utils.DbUtils;
import org.apache.commons.lang.StringUtils;
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
 * since 2015/4/28.
 */
public class MsgDao implements IMsgDao {

    private static final Logger log = LoggerFactory.getLogger(MsgDao.class);


    @Override
    public int add(Msg msg) {
        log.info("add msg {}", msg);
        Connection conn = null;
        PreparedStatement pst = null;
        String sql = "insert into t_msg (user_id,title,content,create_time,update_time) values " +
                "(?,?,?,?,?)";
        try {
            conn = DbUtils.getConn();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, msg.getUserId());
            pst.setString(2, msg.getTitle());
            pst.setString(3, msg.getContent());
            pst.setTimestamp(4, new Timestamp(msg.getCreateTime().getTime()));
            pst.setTimestamp(5, new Timestamp(msg.getUpdateTime().getTime()));
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
        log.info("delete msg {}", id);
        Connection conn = null;
        PreparedStatement pst = null;
        String sql = "delete from t_msg where id=?";
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
    public int update(Msg msg) {
        log.info("update msg {}", msg);
        Connection conn = null;
        PreparedStatement pst = null;
        String sql = "update t_msg set title=?, content=?, update_time=? where id=?";
        try {
            conn = DbUtils.getConn();
            pst = conn.prepareStatement(sql);
            pst.setString(1, msg.getTitle());
            pst.setString(2, msg.getContent());
            pst.setTimestamp(3, new Timestamp(msg.getUpdateTime().getTime()));
            pst.setInt(4, msg.getId());
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
    public Msg fetchById(int id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select id, user_id userId, title, content, create_time createTime, update_time updateTime " +
                "from t_msg where id=?";
        Msg msg = null;
        try {
            conn = DbUtils.getConn();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                msg = new Msg();
                msg.setId(rs.getInt(1));
                msg.setUserId(rs.getInt(2));
                msg.setTitle(rs.getString(3));
                msg.setContent(rs.getString(4));
                msg.setCreateTime(rs.getTimestamp(5));
                msg.setUpdateTime(rs.getTimestamp(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(rs);
            DbUtils.close(pst);
            DbUtils.close(conn);
        }
        return msg;
    }

    @Override
    public Msg fetchByTitle(String title) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select id, user_id userId, title, content, create_time createTime, update_time updateTime " +
                "from t_msg where title like ?";
        Msg msg = null;
        try {
            conn = DbUtils.getConn();
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + title + "%");
            rs = pst.executeQuery();
            if (rs.next()) {
                msg = new Msg();
                msg.setId(rs.getInt(1));
                msg.setUserId(rs.getInt(2));
                msg.setTitle(rs.getString(3));
                msg.setContent(rs.getString(4));
                msg.setCreateTime(rs.getTimestamp(5));
                msg.setUpdateTime(rs.getTimestamp(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(rs);
            DbUtils.close(pst);
            DbUtils.close(conn);
        }
        return msg;
    }

    @Override
    public Pager<Msg> list(String title) {
        Connection conn = null;
        PreparedStatement pst = null;
        PreparedStatement cpst = null;
        ResultSet rs = null;
        int pageIndex = SystemContext.getPageIndex();
        int pageSize = SystemContext.getPageSize();
        int pageOffset = 0;
        int pageCount = 0;
        int pageRecords = 0;
        String sql = "select id, user_id userId, title, content, create_time, update_time " +
                "from t_msg ";
        String sqlCount = "select count(*) from t_msg";

        if (StringUtils.isNotEmpty(title)) {
            sql += "where title like ?";
            sqlCount += " where title like ?";
        }
        sql += " limit ?,?";
        Pager<Msg> pager = new Pager<Msg>();
        try {
            conn = DbUtils.getConn();
            pst = conn.prepareStatement(sql);
            List<Msg> msgList = new ArrayList<Msg>();
            pager.setData(msgList);
            pageOffset = (pageIndex - 1) * pageSize;
            if (StringUtils.isNotEmpty(title)) {
                pst.setString(1, "%" + title + "%");
                pst.setInt(2, pageOffset);
                pst.setInt(3, pageSize);
            } else {
                pst.setInt(1, pageOffset);
                pst.setInt(2, pageSize);
            }

            rs = pst.executeQuery();
            while (rs.next()) {
                Msg msg = new Msg();
                msg.setId(rs.getInt(1));
                msg.setUserId(rs.getInt(2));
                msg.setTitle(rs.getString(3));
                msg.setContent(rs.getString(4));
                msg.setCreateTime(rs.getTimestamp(5));
                msg.setUpdateTime(rs.getTimestamp(6));
                msgList.add(msg);
            }
            cpst = conn.prepareStatement(sqlCount);
            if (StringUtils.isNotEmpty(title)) {
                cpst.setString(1, "%" + title + "%");
            }
            rs = cpst.executeQuery();
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
            DbUtils.close(cpst);
            DbUtils.close(conn);
        }
        return pager;
    }

    @Override
    public Pager<Msg> list() {
        return list(null);
    }
}
