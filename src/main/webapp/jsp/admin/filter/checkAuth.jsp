<%@ page import="com.jt.msg.dao.IUserDao" %>
<%@ page import="com.jt.msg.bean.User" %>
<%@ page import="com.jt.msg.utils.ValidateUtil" %>
<%--
  Date: 2015/4/28
  Time: 14:12
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User luser = (User) session.getAttribute("loginUser");
    if (luser.getType() != 1) {
        ValidateUtil.addError("admin", "没有权限", request);
        %>
<jsp:forward page="../user/list.jsp"></jsp:forward>
<%
    }
%>