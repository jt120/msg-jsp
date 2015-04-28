<%@ page import="com.jt.msg.utils.EncryptUtil" %>
<%@ page import="com.jt.msg.bean.User" %>
<%@ page import="java.util.Date" %>
<%--
  Date: 2015/4/28
  Time: 15:22
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String email = request.getParameter("email");

    password = EncryptUtil.md5(password);

    User u = new User();
    u.setUsername(username);
    u.setPassword(password);
    u.setNickname("");
    u.setEmail(email);
    u.setType(0);
    u.setStatus(1);
    u.setCreateTime(new Date());
%>
