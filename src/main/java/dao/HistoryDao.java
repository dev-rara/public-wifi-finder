package dao;

import dto.HistoryWifi;
import util.DbUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.sql.DriverManager.getConnection;

public class HistoryDao {

    public void insertHistory(HistoryWifi historyWifi) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.KOREA);

        try {
            Class.forName(DbUtil.JDBC);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection(DbUtil.URL);

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO WIFI_HISTORY(LNT, LAT, SEARCH_DATE) ")
                    .append("VALUES ").append("( ? , ? , ?);");

            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, historyWifi.getLnt());
            statement.setString(2, historyWifi.getLat());
            statement.setString(3, dateFormat.format(new Date()));

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

    public List<HistoryWifi> historyWifiList() {
        List<HistoryWifi> historyWifiList = new ArrayList<>();

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

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ").append("* ").append("FROM WIFI_HISTORY ");

            statement = connection.prepareStatement(sql.toString());
            rs = statement.executeQuery();

            while (rs.next()) {
                int historyId = rs.getInt("HISTORY_ID");
                String lat = rs.getString("LNT");
                String lnt = rs.getString("LAT");
                String searchDate = rs.getString("SEARCH_DATE");

                HistoryWifi historyWifi = new HistoryWifi();
                historyWifi.setHistoryId(historyId);
                historyWifi.setLat(lnt);
                historyWifi.setLnt(lat);
                historyWifi.setSearchDate(searchDate);

                historyWifiList.add(historyWifi);
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
        return historyWifiList;
    }

    public int delete(int historyId) {
        int result = 0;

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
            sql.append("DELETE FROM WIFI_HISTORY ").append("WHERE HISTORY_ID = ? ");

            statement = connection.prepareStatement(sql.toString());
            statement.setInt(1, historyId);

            result = statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        return result;
    }
}
