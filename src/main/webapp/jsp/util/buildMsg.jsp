<%@ page import="java.util.Date" %>
<%@ page import="com.jt.msg.bean.Msg" %>
<%--
  Date: 2015/4/28
  Time: 15:22
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int userId = Integer.parseInt(request.getParameter("userId"));
    String title = request.getParameter("title");
    String content = request.getParameter("content");

    Msg m = new Msg();
    m.setUserId(userId);
    m.setTitle(title);
    m.setContent(content);
    m.setCreateTime(new Date());
    m.setUpdateTime(new Date());
%>
