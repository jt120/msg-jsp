<%@ page import="com.jt.msg.bean.User" %>
<%@ page import="com.jt.msg.dao.IUserDao" %>
<%@ page import="com.jt.msg.dao.DaoFactory" %>
<%@ page import="com.jt.msg.utils.ValidateUtil" %>
<%--
  Date: 2015/4/28
  Time: 15:10
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int aid = 0;
    try {
        aid = Integer.parseInt(request.getParameter("id"));
    } catch (NumberFormatException e) {

    }
    if (aid == 0) {
        return;
    }
    IUserDao userDao = DaoFactory.userDao();
    User user = userDao.fetchById(aid);
    if (user.getUsername().equals("admin")) {
        ValidateUtil.addError("admin", "admin不能被修改", request);
%>
<jsp:forward page="../user/list.jsp"></jsp:forward>
<%
    }
%>
