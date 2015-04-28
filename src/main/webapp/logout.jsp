<%--
  Date: 2015/4/28
  Time: 14:54
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.invalidate();
    response.sendRedirect("loginInput.jsp");
%>
