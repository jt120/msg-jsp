<%@ page import="com.jt.msg.bean.Pager" %>
<%@ page import="java.util.List" %>
<%--
  Date: 2015/4/30
  Time: 15:24
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Pager pager = (Pager) request.getAttribute("pagers");
    String url = (String) request.getAttribute("pageUrl");
    for (int i = 1; i <= pager.getTotalPages(); i++) {
        if (pager.getPageIndex() == i) {
            out.print(i);
        } else {
%>
<a href="<%=url + i%>">[<%=i%>]</a>

<%
    }

%>
<%
    }
%>
