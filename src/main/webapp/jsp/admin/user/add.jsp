<%@ page import="com.jt.msg.dao.IUserDao" %>
<%@ page import="com.jt.msg.dao.UserDao" %>
<%@ page import="com.jt.msg.utils.ValidateUtil" %>
<%--
  Date: 2015/4/28
  Time: 11:37
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/util/buildUser.jsp"%>
<%
    if (!ValidateUtil.notNull(new String[]{"username", "password"}, request)) {
%>
<jsp:forward page="addInput.jsp" />
<%
    }
    IUserDao userDao = new UserDao();

    try {
        int add = userDao.add(u);
        response.sendRedirect("list.jsp");
        return;
    } catch (Exception e) {
        ValidateUtil.addError("add", e.getMessage(), request);
%>
<jsp:forward page="addInput.jsp" />

<%
    }
%>
