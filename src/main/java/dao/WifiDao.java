package dao;

import dto.WifiInfo;
import util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class WifiDao {

    public void insertWifiInfo(WifiInfo wifiInfo) {
        try {
            Class.forName(DbUtil.JDBC);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(DbUtil.URL);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO WIFI_INFO ").append("VALUES ")
                    .append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

            statement = connection.prepareStatement(sql.toString());

            statement.setString(1, wifiInfo.getAdminNumber());
            statement.setString(2, wifiInfo.getBorough());
            statement.setString(3, wifiInfo.getWifiName());
            statement.setString(4, wifiInfo.getRoadAddress());
            statement.setString(5, wifiInfo.getDetailAddress());
            statement.setString(6, wifiInfo.getInstallPosition());
            statement.setString(7, wifiInfo.getInstallType());
            statement.setString(8, wifiInfo.getInstallAgency());
            statement.setString(9, wifiInfo.getServiceType());
            statement.setString(10, wifiInfo.getNetType());
            statement.setString(11, wifiInfo.getInstallYear());
            statement.setString(12,wifiInfo.getInOutDoorType());
            statement.setString(13, wifiInfo.getConnectEnv());
            statement.setString(14, wifiInfo.getLnt());
            statement.setString(15, wifiInfo.getLat());
            statement.setString(16, wifiInfo.getWorkDateTime());

            int affected = statement.executeUpdate();

            if (affected < 0) {
                System.out.println("Fail");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<WifiInfo> selectNearbyWifi(String lnt_val, String lat_val) {
        List<WifiInfo> wifiList = new ArrayList<>();

        try {
            Class.forName(DbUtil.JDBC);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(DbUtil.URL);

            String sql = "SELECT *," +
                    " round(6371*acos(cos(radians( ? ))*cos(radians(LAT))*cos(radians(LNT)-radians( ? ))+sin(radians( ? ))*sin(radians(LAT))), 4)" +
                    " AS DISTANCE" +
                    " FROM WIFI_INFO" +
                    " ORDER BY DISTANCE" +
                    " LIMIT 20;";

            statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(lnt_val));
            statement.setString(2, String.valueOf(lat_val));
            statement.setString(3, String.valueOf(lnt_val));

            rs = statement.executeQuery();
            while(rs.next()) {
                String distance = rs.getString("DISTANCE");
                String adminNumber = rs.getString("MGR_NO");
                String borough = rs.getString("WRDOFC");
                String wifiName = rs.getString("MAIN_NM");
                String roadAddress = rs.getString("ADRES1");
                String detailAddress = rs.getString("ADRES2");
                String installPosition = rs.getString("INSTL_FLOOR");
                String installType = rs.getString("INSTL_TY");
                String installAgency = rs.getString("INSTL_MBY");
                String serviceType = rs.getString("SVC_SE");
                String netType = rs.getString("CMCWR");
                String installYear = rs.getString("CNSTC_YEAR");
                String inOutDoorType = rs.getString("INOUT_DOOR");
                String connectEnv = rs.getString("REMARS3");
                String lnt = rs.getString("LNT");
                String lat = rs.getString("LAT");
                String workDateTime = rs.getString("WORK_DTTM");


                WifiInfo wifiInfo = new WifiInfo();
                wifiInfo.setDistance(distance);
                wifiInfo.setAdminNumber(adminNumber);
                wifiInfo.setBorough(borough);
                wifiInfo.setWifiName(wifiName);
                wifiInfo.setRoadAddress(roadAddress);
                wifiInfo.setDetailAddress(detailAddress);
                wifiInfo.setInstallPosition(installPosition);
                wifiInfo.setInstallType(installType);
                wifiInfo.setInstallAgency(installAgency);
                wifiInfo.setServiceType(serviceType);
                wifiInfo.setNetType(netType);
                wifiInfo.setInstallYear(installYear);
                wifiInfo.setInOutDoorType(inOutDoorType);
                wifiInfo.setConnectEnv(connectEnv);
                wifiInfo.setLnt(lnt);
                wifiInfo.setLat(lat);
                wifiInfo.setWorkDateTime(workDateTime);

                wifiList.add(wifiInfo);
            }
        } catch (SQLException e) {
             e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return wifiList;
    }
}
