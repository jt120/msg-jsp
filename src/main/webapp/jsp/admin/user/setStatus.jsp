<%@ page import="com.jt.msg.dao.IUserDao" %>
<%@ page import="com.jt.msg.dao.DaoFactory" %>
<%@ page import="com.jt.msg.bean.User" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%--
  Date: 2015/4/28
  Time: 14:06
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../filter/checkAuth.jsp"%>
<%@include file="../filter/checkAdmin.jsp" %>

<%!
    private static final Logger log = LoggerFactory.getLogger("setStatus.jsp");

%>
<%
    if (user.getStatus() == 0) {
        user.setStatus(1);
    } else {
        user.setStatus(0);
    }
    log.info("update user {}", user);
    userDao.update(user);
    response.sendRedirect("list.jsp");
%>
