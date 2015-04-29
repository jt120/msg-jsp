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
    if (luser == null) {
        ValidateUtil.addError("user", "没有登陆", request);
        %>
<jsp:forward page="/loginInput.jsp"></jsp:forward>
<%
    }
%>