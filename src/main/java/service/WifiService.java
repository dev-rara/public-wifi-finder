package service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.WifiDao;
import dto.WifiInfo;
import util.JsonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class WifiService {

        public int getWifiData() throws IOException {
                StringBuilder urlBuilder = new StringBuilder(JsonUtil.baseUrl);
                urlBuilder.append("/" + URLEncoder.encode(JsonUtil.KEY, "UTF-8"));
                urlBuilder.append("/" + URLEncoder.encode(JsonUtil.TYPE, "UTF-8"));
                urlBuilder.append("/" + URLEncoder.encode(JsonUtil.SERVICE, "UTF-8"));
                urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8"));
                urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8"));

                URL url = new URL(urlBuilder.toString());
                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
                httpConn.setRequestMethod("GET");
                httpConn.setRequestProperty("Content-type", "application/json");
                System.out.println("Response code: " + httpConn.getResponseCode());

                BufferedReader rd;

                if (httpConn.getResponseCode() >= 200 && httpConn.getResponseCode() <= 300) {
                        rd = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                } else {
                        rd = new BufferedReader(new InputStreamReader(httpConn.getErrorStream()));
                }

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                        sb.append(line);
                }
                rd.close();
                httpConn.disconnect();

                String total = sb.substring(40, 45);

                return Integer.parseInt(total);
        }


        public void insertWifiData() {
                try {
                        int wifiTotal = getWifiData();
                        BufferedReader rd;
                        StringBuilder sb;
                        String line;

                        int index = 1;
                        for (int i = 0; i <= wifiTotal / 1000; i++) {
                                StringBuilder urlBuilder = new StringBuilder(JsonUtil.baseUrl);
                                urlBuilder.append("/" + URLEncoder.encode(JsonUtil.KEY,"UTF-8") );
                                urlBuilder.append("/" + URLEncoder.encode(JsonUtil.TYPE,"UTF-8") );
                                urlBuilder.append("/" + URLEncoder.encode(JsonUtil.SERVICE,"UTF-8"));
                                urlBuilder.append("/" + URLEncoder.encode(String.valueOf(index),"UTF-8"));
                                urlBuilder.append("/" + URLEncoder.encode(String.valueOf((index+999)),"UTF-8"));

                                URL url = new URL(urlBuilder.toString());
                                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
                                httpConn.setRequestMethod("GET");
                                httpConn.setRequestProperty("Content-type", "application/json");

                                if(httpConn.getResponseCode() >= 200 && httpConn.getResponseCode() <= 300) {
                                        rd = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                                } else {
                                        rd = new BufferedReader(new InputStreamReader(httpConn.getErrorStream()));
                                }

                                sb = new StringBuilder();
                                while ((line = rd.readLine()) != null) {
                                        sb.append(line);
                                }

                                JsonParser jsonParser = new JsonParser();
                                JsonObject result = (JsonObject) jsonParser.parse(sb.toString());
                                JsonObject wifiData = (JsonObject)result.get("TbPublicWifiInfo");
                                JsonArray row = (JsonArray)wifiData.get("row");

                                JsonObject jsonObject;
                                for(int j = 0; j < row.size(); j++) {
                                        WifiInfo wifiInfo = new WifiInfo();
                                        jsonObject = (JsonObject) row.get(j);

                                        wifiInfo.setAdminNumber(String.valueOf(jsonObject.get("X_SWIFI_MGR_NO")));
                                        wifiInfo.setBorough(String.valueOf(jsonObject.get("X_SWIFI_WRDOFC")));
                                        wifiInfo.setWifiName(String.valueOf(jsonObject.get("X_SWIFI_MAIN_NM")));
                                        wifiInfo.setRoadAddress(String.valueOf(jsonObject.get("X_SWIFI_ADRES1")));
                                        wifiInfo.setDetailAddress(String.valueOf(jsonObject.get("X_SWIFI_ADRES2")));
                                        wifiInfo.setInstallPosition(String.valueOf(jsonObject.get("X_SWIFI_INSTL_FLOOR")));
                                        wifiInfo.setInstallType(String.valueOf(jsonObject.get("X_SWIFI_INSTL_TY")));
                                        wifiInfo.setInstallAgency(String.valueOf(jsonObject.get("X_SWIFI_INSTL_MBY")));
                                        wifiInfo.setServiceType(String.valueOf(jsonObject.get("X_SWIFI_SVC_SE")));
                                        wifiInfo.setNetType(String.valueOf(jsonObject.get("X_SWIFI_CMCWR")));
                                        wifiInfo.setInstallYear(String.valueOf(jsonObject.get("X_SWIFI_CNSTC_YEAR")));
                                        wifiInfo.setInOutDoorType(String.valueOf(jsonObject.get("X_SWIFI_INOUT_DOOR")));
                                        wifiInfo.setConnectEnv(String.valueOf(jsonObject.get("X_SWIFI_REMARS3")));
                                        wifiInfo.setLnt(String.valueOf(jsonObject.get("LAT")));
                                        wifiInfo.setLat(String.valueOf(jsonObject.get("LNT")));
                                        wifiInfo.setWorkDateTime(String.valueOf(jsonObject.get("WORK_DTTM")));

                                        WifiDao wifiDao = new WifiDao();
                                        wifiDao.insertWifiInfo(wifiInfo);
                                }
                                rd.close();
                                httpConn.disconnect();

                                index += 1000;
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public List<WifiInfo> nearbyWifiList(String lnt, String lat) {
                WifiDao wifiDao = new WifiDao();
                return wifiDao.selectNearbyWifi(lnt, lat);
        }
}
