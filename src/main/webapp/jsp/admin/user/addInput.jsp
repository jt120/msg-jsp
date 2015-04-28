<%@ page import="com.jt.msg.utils.ValidateUtil" %>
<%--
  Date: 2015/4/28
  Time: 12:19
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
    <form action="add.jsp" method="post">
        <p>用户名: <input type="text" name="username" />
        <span style="color: red"><%=ValidateUtil.showErrorMsg("username", request)%></span>
        </p>

        <p>邮箱: <input type="text" name="email" />
            <span style="color: red"><%=ValidateUtil.showErrorMsg("email", request)%></span>
        </p>
        <p>密码: <input type="password" name="password" />
        <span style="color: red"><%=ValidateUtil.showErrorMsg("password", request)%></span>
        </p>
        <p><input type="submit" value="添加" /></p>
        <span style="color: red"><%=ValidateUtil.showErrorMsg("add", request)%></span>

    </form>
</div>

</body>
</html>
