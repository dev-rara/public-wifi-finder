<%@ page import="service.HistoryService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Public WiFi Finder</title>
</head>
<body>
<%
    HistoryService historyService = new HistoryService();
    int result = historyService.deleteHistory(Integer.parseInt(request.getParameter("id")));
    String msg = "삭제 실패";

    if (result > 0) {
        msg = "삭제 성공";
    }
%>
<script>
    alert("<%=msg %>")
    location.href="history.jsp";
</script>
</body>
</html>
