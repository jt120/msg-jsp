<%--
  Date: 2015/4/28
  Time: 15:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../filter/checkAuth.jsp"%>
<%@include file="../filter/checkAdmin.jsp" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    userDao.delete(id);
    response.sendRedirect("list.jsp");
%>
