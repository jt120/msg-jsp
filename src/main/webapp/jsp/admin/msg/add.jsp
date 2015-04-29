<%@ page import="com.jt.msg.dao.IUserDao" %>
<%@ page import="com.jt.msg.dao.UserDao" %>
<%@ page import="com.jt.msg.utils.ValidateUtil" %>
<%@ page import="com.jt.msg.dao.IMsgDao" %>
<%@ page import="com.jt.msg.dao.DaoFactory" %>
<%--
  Date: 2015/4/28
  Time: 11:37
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/admin/filter/checkLogin.jsp" %>
<%@include file="/jsp/util/buildMsg.jsp"%>
<%
    if (!ValidateUtil.notNull(new String[]{"title", "content"}, request)) {
%>
<jsp:forward page="addInput.jsp" />
<%
    }
    IMsgDao msgDao = DaoFactory.msgDao();

    try {
        int add = msgDao.add(m);
        response.sendRedirect("list.jsp");
        return;
    } catch (Exception e) {
        ValidateUtil.addError("add", e.getMessage(), request);
%>
<jsp:forward page="addInput.jsp" />

<%
    }
%>
