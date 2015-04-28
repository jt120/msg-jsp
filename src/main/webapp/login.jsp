<%@ page import="com.jt.msg.bean.User" %>
<%@ page import="com.jt.msg.utils.ValidateUtil" %>
<%--
  Date: 2015/4/28
  Time: 11:37
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute("loginUser");
    if (user != null) {
        response.sendRedirect("/jsp/admin/user/list.jsp");
        return;
    }
    if (!ValidateUtil.notNull(new String[]{"username", "password"}, request)) {
%>
<jsp:forward page="loginInput.jsp" />
<%
    }
    if (ValidateUtil.login(request)) {
        response.sendRedirect("/jsp/admin/user/list.jsp");
        return;
    } else {
%>
<jsp:forward page="loginInput.jsp" />

<%
    }
%>
