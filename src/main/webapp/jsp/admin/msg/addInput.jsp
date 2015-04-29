<%@ page import="com.jt.msg.utils.ValidateUtil" %>
<%@ page import="com.jt.msg.utils.ShowUtil" %>
<%--
  Date: 2015/4/28
  Time: 12:19
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title></title>
    <script src="<%=request.getContextPath()%>/resources/editor/ckeditor.js"></script>
</head>
<body>
<jsp:include page="/jsp/inc/header.jsp" />
<%@include file="/jsp/admin/filter/checkLogin.jsp" %>
<div>
    <form action="add.jsp" method="post">
        <input type="hidden" name="userId" value="<%=luser.getId()%>"/>
        <p><input type="text" name="title" value="<%=ShowUtil.show("title", request)%>"/>
            <span style="color: red"><%=ValidateUtil.showErrorMsg("title", request)%></span>
        </p>

        <p><textarea class="ckeditor" cols="80" id="editor1" rows="10" name="content"
                     value="<%=ShowUtil.show("content", request)%>">

        </textarea>
            <span style="color: red"><%=ValidateUtil.showErrorMsg("content", request)%></span>
        </p>

        <p><input type="submit" value="发表" /></p>
        <span style="color: red"><%=ValidateUtil.showErrorMsg("add", request)%></span>

    </form>
</div>

</body>
</html>