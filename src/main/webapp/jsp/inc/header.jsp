<%@ page import="com.jt.msg.bean.User" %>
<%--
  Date: 2015/4/28
  Time: 11:31
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <%
        User u = (User) session.getAttribute("loginUser");
        if (u != null) {
    %>
    欢迎 <%=u.getUsername()%> &nbsp;<a href="<%=request.getContextPath()%>/admin/msg/list.jsp">文章列表</a>
    <%
        if (u.getType() == 1) {
    %>
    <a href="<%=request.getContextPath()%>/jsp/admin/user/addInput.jsp">添加用户</a>
    <%
        }%>
    <a href="<%=request.getContextPath()%>/logout.jsp">退出</a>
    <%
    } else {

    %>
    欢迎, <a href="<%=request.getContextPath()%>/loginInput.jsp">登陆</a>&nbsp;
    <a href="<%=request.getContextPath()%>/registerInput.jsp">注册</a>
    <%
        }
    %>
</div>
