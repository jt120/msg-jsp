package com.jt.msg.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * since 2015/4/27.
 */
public class DbUtils {

    private static final String url = "jdbc:mysql://localhost:3306/msg";
    public static final String username = "hello";
    public static final String password = "hello";

    private static final Logger log = LoggerFactory.getLogger(DbUtils.class);


    public static Connection getConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            log.warn("get connection error", e);
        }
        return conn;
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement pst) {
        try {
            if (pst != null) {
                pst.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
