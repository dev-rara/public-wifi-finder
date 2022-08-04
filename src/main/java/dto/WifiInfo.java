package dto;

public class WifiInfo {
    private String distance;
    private String adminNumber;
    private String borough;
    private String wifiName;
    private String roadAddress;
    private String detailAddress;
    private String installPosition;
    private String installType;
    private String installAgency;
    private String serviceType;
    private String netType;
    private String installYear;
    private String inOutDoorType;
    private String connectEnv;
    private String lat;
    private String lnt;
    private String workDateTime;

    public WifiInfo() {}

    public WifiInfo(String distance, String adminNumber, String borough,
                    String wifiName, String roadAddress, String detailAddress,
                    String installPosition, String installType, String installAgency,
                    String serviceType, String netType, String installYear,
                    String inOutDoorType, String connectEnv, String lat, String lnt,
                    String workDateTime) {
        this.distance = distance;
        this.adminNumber = adminNumber;
        this.borough = borough;
        this.wifiName = wifiName;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
        this.installPosition = installPosition;
        this.installType = installType;
        this.installAgency = installAgency;
        this.serviceType = serviceType;
        this.netType = netType;
        this.installYear = installYear;
        this.inOutDoorType = inOutDoorType;
        this.connectEnv = connectEnv;
        this.lat = lat;
        this.lnt = lnt;
        this.workDateTime = workDateTime;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getAdminNumber() {
        return adminNumber;
    }

    public void setAdminNumber(String adminNumber) {
        this.adminNumber = adminNumber;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public String getRoadAddress() {
        return roadAddress;
    }

    public void setRoadAddress(String roadAddress) {
        this.roadAddress = roadAddress;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getInstallPosition() {
        return installPosition;
    }

    public void setInstallPosition(String installPosition) {
        this.installPosition = installPosition;
    }

    public String getInstallType() {
        return installType;
    }

    public void setInstallType(String installType) {
        this.installType = installType;
    }

    public String getInstallAgency() {
        return installAgency;
    }

    public void setInstallAgency(String installAgency) {
        this.installAgency = installAgency;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getInstallYear() {
        return installYear;
    }

    public void setInstallYear(String installYear) {
        this.installYear = installYear;
    }

    public String getInOutDoorType() {
        return inOutDoorType;
    }

    public void setInOutDoorType(String inOutDoorType) {
        this.inOutDoorType = inOutDoorType;
    }

    public String getConnectEnv() {
        return connectEnv;
    }

    public void setConnectEnv(String connectEnv) {
        this.connectEnv = connectEnv;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLnt() {
        return lnt;
    }

    public void setLnt(String lnt) {
        this.lnt = lnt;
    }

    public String getWorkDateTime() {
        return workDateTime;
    }

    public void setWorkDateTime(String workDateTime) {
        this.workDateTime = workDateTime;
    }
}
