<%--
  Date: 2015/4/28
  Time: 14:06
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../filter/checkAuth.jsp" %>
<%@include file="../filter/checkAdmin.jsp" %>

<%
    if (user.getType() == 0) {
        user.setType(1);
        userDao.update(user);
    }
    response.sendRedirect("list.jsp");
%>
