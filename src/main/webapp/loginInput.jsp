<%@ page import="com.jt.msg.utils.ValidateUtil" %>
<%@ page import="com.jt.msg.utils.ShowUtil" %>
<%--
  Date: 2015/4/27
  Time: 20:28
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title></title>
</head>
<body>
<jsp:include page="/jsp/inc/header.jsp" />

<div>
    <form action="login.jsp" method="post">
        <p>用户名: <input type="text" name="username" value="<%=ShowUtil.show("username", request)%>"/>
        <span style="color: red"><%=ValidateUtil.showErrorMsg("username", request)%></span>
        </p>
        <p>密码: <input type="password" name="password" />
        <span style="color: red"><%=ValidateUtil.showErrorMsg("password", request)%></span>
        </p>
        <p><input type="submit" value="登陆" /></p>
        <span style="color: red"><%=ValidateUtil.showErrorMsg("login", request)%></span>

    </form>
</div>
</body>
</html>
