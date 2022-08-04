<%@ page import="service.WifiService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Public WiFi Finder</title>
    <style>
        .wifi-result {
            padding-top: 50px;
            text-align: center;
        }

        .home-button
        {
            text-align: center;
        }

        .home-button a
        {
            color: Black;
        }
        .wifi-result
        {
            font-weight: 800;
            font-size: 140%;
        }
    </style>
</head>
<body>
<p class='wifi-result'>
    <%
        WifiService wifiService = new WifiService();
        int totalCnt = wifiService.getWifiData();
        out.write(String.valueOf(totalCnt));
        wifiService.insertWifiData();
    %>
    개의 WIFI 정보를 정상적으로 저장하였습니다.</p>
<div class='home-button'>
    <a href="index.jsp">홈으로 가기</a>
</div>
</body>
</html>
