<%@ page import="com.jt.msg.dao.IUserDao" %>
<%@ page import="com.jt.msg.dao.UserDao" %>
<%@ page import="com.jt.msg.bean.User" %>
<%@ page import="com.jt.msg.bean.Pager" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jt.msg.utils.DataUtil" %>
<%@ page import="com.jt.msg.utils.ValidateUtil" %>
<%@ page import="com.jt.msg.dao.IMsgDao" %>
<%@ page import="com.jt.msg.dao.DaoFactory" %>
<%@ page import="com.jt.msg.bean.Msg" %>
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
    IMsgDao msgDao = DaoFactory.msgDao();
    Pager<Msg> list = msgDao.list();
    List<Msg> msgs = list.getData();
    User loginUser = (User) session.getAttribute("loginUser");
%>
<div>
    <p><span style="color: red"><%=ValidateUtil.showErrorMsg("admin", request)%></span></p>
    <table>
        <tr>
            <td>文章标题</td>
            <td>发表日期</td>
        </tr>
        <%
            for (Msg m : msgs) {

        %>
        <tr>
            <td><a href="show.jsp?id=<%=m.getId()%>"><%=m.getTitle()%></a>
            </td>
            <td><%=m.getCreateTime()%>
            </td>

        </tr>
        <%
            }
        %>
    </table>

    <p>
        <%
            for (int i = 1; i <= list.getTotalPages(); i++) {
                if (list.getPageIndex() == i) {
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
