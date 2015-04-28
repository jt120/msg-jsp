<%@ page import="com.jt.msg.dao.IUserDao" %>
<%@ page import="com.jt.msg.dao.UserDao" %>
<%@ page import="com.jt.msg.bean.User" %>
<%@ page import="com.jt.msg.bean.Pager" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jt.msg.utils.DataUtil" %>
<%@ page import="com.jt.msg.utils.ValidateUtil" %>
<%--
  Date: 2015/4/28
  Time: 13:57
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title></title>
</head>
<body>
<jsp:include page="/jsp/inc/header.jsp" />

<%
    IUserDao userDao = new UserDao();
    Pager<User> list = userDao.list();
    List<User> users = list.getData();
    User loginUser = (User) session.getAttribute("loginUser");
%>
<div>
    <p><span style="color: red"><%=ValidateUtil.showErrorMsg("admin", request)%></span></p>
    <table>
        <tr>
            <td>用户名</td>
            <td>邮箱</td>
            <td>别名</td>
            <td>状态</td>
            <td>类型</td>
            <td>注册时间</td>
            <td>其他操作</td>
        </tr>
        <%
            for (User u : users) {

        %>
        <tr>
            <td><%=u.getUsername()%>
            </td>
            <td><%=u.getEmail()%>
            </td>
            <td><%=u.getNickname()%>
            </td>
            <td><%
                if (u.getStatus() == 0) {
            %>
                已停用&nbsp;<a href="setStatus.jsp?id=<%=u.getId()%>">启用</a>
                <%
                } else if (u.getStatus() == 1) {
                %>
                正常&nbsp;<a href="setStatus.jsp?id=<%=u.getId()%>">停用</a>
                <%
                    }
                %>
            </td>
            <td>
                <%
                    if (u.getType() == 1) {
                %>
                管理员
                <%
                } else if (u.getType() == 0) {
                %>
                普通用户
                <%
                    if (loginUser.getType() == 1) {
                %>
                &nbsp;<a href="setType.jsp?id=<%=u.getId()%>">设为管理员</a>
                <%
                    }
                %>
                <%
                    }
                %>
            </td>
            <td><%=DataUtil.parse(u.getCreateTime())%>
            </td>
            <td>
                <%
                    if (loginUser.getType() == 1) {
                %>
                <a href="delete.jsp?id=<%=u.getId()%>">删除</a>
                <%
                    }
                %>
            </td>
        </tr>
        <%
            }
        %>
    </table>

    <p>
        <%
            for (int i = 1; i <= list.getTotalPages(); i++) {
                if (list.getPageIndex() == i-1) {
                    out.print(i);
                } else {
        %>
         <a href="list.jsp?pageIndex=<%=i%>">[<%=i%>]</a>

        <%
            }

        %>
        <%
            }
        %>
    </p>

</div>

</body>
</html>
