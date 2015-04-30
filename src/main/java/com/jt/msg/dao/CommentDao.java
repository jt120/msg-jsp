package com.jt.msg.dao;

import com.jt.msg.bean.Comment;
import com.jt.msg.bean.Pager;
import com.jt.msg.bean.SystemContext;
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
 * since 2015/4/30.
 */
public class CommentDao implements ICommentDao {

    private static final Logger log = LoggerFactory.getLogger(CommentDao.class);


    @Override
    public int add(Comment comment) {
        log.info("add comment {}", comment);
        Connection conn = null;
        PreparedStatement pst = null;
        String sql = "insert into t_comment (user_id,msg_id,content,status,create_time) values " +
                "(?,?,?,?,?)";
        try {
            conn = DbUtils.getConn();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, comment.getUserId());
            pst.setInt(2, comment.getMsgId());
            pst.setString(3, comment.getContent());
            pst.setInt(4, comment.getStatus());
            pst.setTimestamp(5, new Timestamp(comment.getCreateTime().getTime()));
            return pst.executeUpdate();
        } catch (SQLException e) {
            log.warn("add comment error", e);
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
        String sql = "delete from t_comment where id=?";
        try {
            conn = DbUtils.getConn();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            log.warn("delete comment error", e);
        } finally {
            DbUtils.close(conn);
            DbUtils.close(pst);
        }
        return 0;
    }

    private Pager<Comment> list(int id, String sql, String sqlCount) {
        log.info("list comment sql {}, param {}", sql, id);
        Connection conn = null;
        PreparedStatement pst = null;
        PreparedStatement cpst = null;
        ResultSet rs = null;
        int pageIndex = SystemContext.getPageIndex();
        int pageSize = SystemContext.getPageSize();
        int pageOffset = 0;
        int pageCount = 0;
        int pageRecords = 0;
        sql += " limit ?,?";
        Pager<Comment> pager = new Pager<Comment>();
        try {
            conn = DbUtils.getConn();
            pst = conn.prepareStatement(sql);
            List<Comment> comList = new ArrayList<Comment>();
            pager.setData(comList);
            pageOffset = (pageIndex - 1) * pageSize;
            pst.setInt(1, id);
            pst.setInt(2, pageOffset);
            pst.setInt(3, pageSize);
            rs = pst.executeQuery();
            while (rs.next()) {
                Comment com = new Comment();
                com.setId(rs.getInt(1));
                com.setUserId(rs.getInt(2));
                com.setMsgId(rs.getInt(3));
                com.setContent(rs.getString(4));
                com.setStatus(rs.getInt(5));
                com.setCreateTime(rs.getTimestamp(6));
                comList.add(com);
            }
            cpst = conn.prepareStatement(sqlCount);
            cpst.setInt(1, id);
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
            log.info("list comment result count {}", comList.size());

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
    public Pager<Comment> listByMsgId(int msgId) {
        String sql = "select id, user_id userId, msg_id msgId, content, status, create_time " +
                "from t_comment where msg_id=? ";
        String sqlCount = "select count(*) from t_comment where msg_id=? ";
        return list(msgId, sql, sqlCount);
    }

    @Override
    public Pager<Comment> listByUserId(int userId) {
        String sql = "select id, user_id userId, msg_id msgId, content, status, create_time " +
                "from t_comment where user_id=? ";
        String sqlCount = "select count(*) from t_comment where user_id=? ";
        return list(userId, sql, sqlCount);
    }
}
