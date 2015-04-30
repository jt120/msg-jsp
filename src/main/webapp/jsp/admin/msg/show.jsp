<%@ page import="com.jt.msg.dao.IMsgDao" %>
<%@ page import="com.jt.msg.dao.DaoFactory" %>
<%@ page import="com.jt.msg.bean.Msg" %>
<%@ page import="com.jt.msg.dao.ICommentDao" %>
<%@ page import="com.jt.msg.bean.Comment" %>
<%@ page import="com.jt.msg.bean.Pager" %>
<%@ page import="java.util.List" %>
<%--
  Date: 2015/4/29
  Time: 10:53
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title></title>
</head>
<body>
<jsp:include page="/jsp/inc/header.jsp"/>
<%
    IMsgDao msgDao = DaoFactory.msgDao();
    ICommentDao commentDao = DaoFactory.commentDao();
    int id = Integer.parseInt(request.getParameter("id"));
    Msg msg = msgDao.fetchById(id);
    Pager<Comment> coms = null;
    List<Comment> list = null;
    if (msg != null) {
        coms = commentDao.listByMsgId(id);
        list = coms.getData();
    }

%>
<div>
    <p><%=msg.getTitle()%></p>
    <p><%=msg.getContent()%></p>
    <p>创建时间: <%=msg.getCreateTime()%>&nbsp;修改时间: <%=msg.getUpdateTime()%></p>

</div>
</body>
</html>
