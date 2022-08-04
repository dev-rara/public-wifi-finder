<%@ page import="service.HistoryService" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.HistoryWifi" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Public WiFi Finder</title>
    <script type="text/javascript">
        function del(ID) {
            location.href="delete.jsp?id=" + ID;
        }
    </script>
    <style>
        th {
            padding-top: 9px;
            padding-bottom: 9px;
            background-color: #04AA6D;
            color: white;
            border: 1px solid #ddd;
            border-radius: 3px;
        }

        h1 {
            padding-top: 12px;
        }

        table {
            width: 100%;
            border-collapse : collapse;
        }


        tr:nth-child(odd) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        td {
            border: 1px solid #ddd;
            padding: 7px;
            text-align: left;
        }

        ul{
            list-style-type: none;
            margin: 0;
            padding: 0;
        }

        li {display: inline; }

        .nav_list {
            width: fit-content;
            padding: 5px;
            margin-bottom: 10px;
        }

        li a {
            padding: 5px;
            font-weight: bold;
        }

        .container {
            margin-bottom: 10px;
        }

    </style>
</head>
<body>
<div class="container">
    <h1>&nbsp;위치 히스토리 목록</h1>
    <nav class="nav_list">
        <ul>
            <li>
                <a href="index.jsp">
                    홈
                </a>
            </li>

            <li>
                <a href="history.jsp">
                    위치 히스토리 목록
                </a>
            </li>

            <li>
                <a href="load-wifi.jsp">
                    open API 와이파이 정보 가져오기
                </a>
            </li>
        </ul>
    </nav>
</div>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <%
        HistoryService historyService = new HistoryService();
        List<HistoryWifi> historyWifiList = historyService.historyList();

        for(HistoryWifi historyWifi: historyWifiList) {
    %>
    <tr>
        <td><%=historyWifi.getHistoryId()%></td>
        <td><%=historyWifi.getLat()%></td>
        <td><%=historyWifi.getLnt()%></td>
        <td><%=historyWifi.getSearchDate()%></td>
        <td>
            <button type=submit onclick="location.href='delete.jsp?id=<%=historyWifi.getHistoryId() %>'">
                삭제</button>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
