package service;

import dao.HistoryDao;
import dto.HistoryWifi;

import java.util.List;

public class HistoryService {

    public List<HistoryWifi> historyList () {
        HistoryDao historyDao = new HistoryDao();
        return historyDao.historyWifiList();
    }

    public void insert(HistoryWifi historyWifi) {
        HistoryDao historyDao = new HistoryDao();
        historyDao.insertHistory(historyWifi);
    }

    public int deleteHistory(int historyId) {
        HistoryDao historyDao = new HistoryDao();
        return historyDao.delete(historyId);
    }

}
