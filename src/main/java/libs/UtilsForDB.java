package libs;


import io.qameta.allure.Step;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UtilsForDB {
     Database dBMySQL;


// eld_orders

    @Step
    public String getLastOrderId(String fleetUserId, String id) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        String tempIdOrder = dBMySQL.selectValue("SELECT id FROM eld_orders WHERE " + fleetUserId + " = " + id + " ORDER BY orderDate desc LIMIT 1;");
        dBMySQL.quit();
        return tempIdOrder;
    }
    @Step
    public String getOrderStatus(String idOrder) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        String tempDeviceStatus = dBMySQL.selectValue("SELECT status FROM eld_orders WHERE id = " + idOrder + ";");
        dBMySQL.quit();
        return tempDeviceStatus;
    }
    @Step
    public List<String> getIdOrderWithStatusNew(String fleetUserId, String fleetId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempIdDevices = dBMySQL.selectResultSet("SELECT id FROM eld_orders WHERE " + fleetUserId + " = " + fleetId + " AND status = 0;");
        dBMySQL.quit();
        return tempIdDevices;
    }

    @Step
    public void setOrderDateByTariffId(String soloOrFleetString, String userId, String tariffStart, String tariffId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE eld_orders SET `orderDate` = '" + tariffStart + "' WHERE id IN (SELECT orderId FROM eld_orders_ids WHERE scannerId IN (SELECT id FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND tariffId = " + tariffId + "));");
        dBMySQL.quit();
    }
    @Step
    public void setOrderDateByDeviceId(String tariffStart, String deviceId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE eld_orders SET `orderDate` = '" + tariffStart + "' WHERE id IN (SELECT orderId FROM eld_orders_ids WHERE scannerId IN (" + deviceId + "));");
        dBMySQL.quit();
    }


    @Step
    public int getCountNewOrder(String fleetUserId, String fleetId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        int tempCountNewOrder = dBMySQL.getRowNumber("SELECT count(*) FROM eld_orders WHERE " + fleetUserId + " = " + fleetId + " AND status = 0;");
        dBMySQL.quit();
        return tempCountNewOrder;
    }

    @Step
    public void changeStatusOrderToCancel(String idOrder) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE eld_orders SET status = 2 WHERE id = " + idOrder + ";");
        dBMySQL.quit();
    }

// eld_scanners

    @Step
    public List<String> getStatusEldInOrder(String idOrder) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempStatusEld = dBMySQL.selectResultSet("SELECT status FROM eld_scanners WHERE id IN (SELECT scannerId FROM eld_orders_ids WHERE orderId = " + idOrder + ");");
        dBMySQL.quit();
        return tempStatusEld;
    }

    @Step
    public List<String> getLocalIdDevices(String idOrder) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempLocalIdDevices = dBMySQL.selectResultSet("SELECT localId FROM eld_scanners WHERE id IN (SELECT scannerId FROM eld_orders_ids WHERE orderId = " + idOrder + ");");
        dBMySQL.quit();
        return tempLocalIdDevices;
    }
    @Step
    public void setPaidTillAndTariffStartScannerForFleet(String fleetId, String paidTill, String tariffStart, String tariffId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE eld_scanners SET `paid_till`='" + paidTill + "', `tariffStart` = '" + tariffStart + "' WHERE fleet = " + fleetId + " AND tariffId IN(" + tariffId + ");");
        dBMySQL.quit();
    }
    @Step
    public void setPaidTillAndTariffStartScannerById(String paidTill, String tariffStart, String idDevice) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE eld_scanners SET `paid_till`='" + paidTill + "', `tariffStart` = '" + tariffStart + "' WHERE id IN (" + idDevice + ");");
        dBMySQL.quit();
    }
    @Step
    public void setStatusesForDevices(String listOfStatusDevices, String status) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE eld_scanners SET `status`='" + status + "' WHERE id IN(" + listOfStatusDevices + ");");
        dBMySQL.quit();
    }
    @Step
    public List<String> getIdEldFromOrder(String idOrder) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempIdDevices = dBMySQL.selectResultSet("SELECT id FROM eld_scanners WHERE id IN (SELECT scannerId FROM eld_orders_ids WHERE orderId = " + idOrder + ");");
        dBMySQL.quit();
        return tempIdDevices;
    }
    @Step
    public List<String> getIdChargeScannersByTariff(String soloOrFleetString, String userId, String tariffId, int countDevices) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempDeviceStatus = dBMySQL.selectResultSet("SELECT id FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND tariffId = " + tariffId + " AND status = 4 ORDER BY id DESC LIMIT " + countDevices + ";");
        dBMySQL.quit();
        return tempDeviceStatus;
    }

    @Step
    public void setPaidTillAndTariffStartScannerForSolo(String soloId, String paidTill, String tariffStart, String tariffId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE eld_scanners SET `paid_till`='" + paidTill + "', `tariffStart` = '" + tariffStart + "' WHERE userId = " + soloId + " AND tariffId = " + tariffId + ";");
        dBMySQL.quit();
    }
    @Step
    public int countChargeScannersByTariff(String soloOrFleetString, String userId, String tariffId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        int tempCountScanner =dBMySQL.getRowNumber("SELECT count(*) FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status IN (4, 8, 103) AND tariffId = " + tariffId + ";");
        dBMySQL.quit();
        return tempCountScanner;
    }
    @Step
    public int countChargeScanners(String soloOrFleetString, String userId, String firstDayNextMonth) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        int tempCountScanner =dBMySQL.getRowNumber("SELECT count(*) FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status IN (4, 5, 8, 103) AND paid_till < " + firstDayNextMonth + ";");
        dBMySQL.quit();
        return tempCountScanner;
    }
    public int countDeactivatedChargeScannersMonthToMonth(String soloOrFleetString, String userId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        int tempCountScanner =dBMySQL.getRowNumber("SELECT count(*) FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status = 5 AND tariffId IN (0, 9);");
        dBMySQL.quit();
        return tempCountScanner;
    }
    @Step
    public List<String> getScannersTariffStart(String scannerId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempCountScanner =dBMySQL.selectResultSet("SELECT tariffStart FROM eld_scanners WHERE id IN(" + scannerId + ");");
        dBMySQL.quit();
        return tempCountScanner;
    }

    @Step
    public List<String> getPaidTillFromEldScanners(String soloOrFleetString, String userId, String tariffId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempPaidTillAndTariffStartList = dBMySQL.selectResultSet("SELECT paid_till FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status = 4 AND tariffId IN (" + tariffId + ")");
        dBMySQL.quit();
        return tempPaidTillAndTariffStartList;
    }
    @Step
    public List<String> getParamsDeactivatedScanners(String soloOrFleetString, String userId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempAmountList = dBMySQL.selectResultSet("SELECT params FROM eld_scanners WHERE id IN (SELECT id FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status = 5 AND tariffId = 0);");
        dBMySQL.quit();
        return tempAmountList;
    }
    @Step
    public List<String> getIdScannersByStatus(String soloOrFleetString, String userId, String numberStatus) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempIdDevices = dBMySQL.selectResultSet("SELECT id FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status = " + numberStatus + ";");
        dBMySQL.quit();
        return tempIdDevices;
    }
    @Step
    public List<String> getScannersStatus(String scannerId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempScannersStatuses = dBMySQL.selectResultSet("SELECT status FROM eld_scanners WHERE id IN(" + scannerId + ");");
        dBMySQL.quit();
        return tempScannersStatuses;
    }

//    eld_orders_ids

    @Step
    public boolean isEldBlinded(String idOrder) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        boolean tempResult = dBMySQL.isRowPresent("SELECT * FROM eld_orders_ids WHERE orderId = " + idOrder + ";");
        dBMySQL.quit();
        return tempResult;
    }

//   eld_returns

    public int countChargeReturnedScannerByTariff(String soloOrFleetString, String userId, String tariffId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        int tempCountScanner =dBMySQL.getRowNumber("SELECT count(*) FROM eld_returns WHERE scannerId IN (SELECT id FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status = 11 AND tariffId = " + tariffId + ") AND returnReason IN (2, 1) AND status IN (0, 1, 3);");
        dBMySQL.quit();
        return tempCountScanner;
    }
    public int countChargeReturnedScanners(String soloOrFleetString, String userId, String firstDayNextMonth) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        int tempCountScanner =dBMySQL.getRowNumber("SELECT count(*) FROM eld_returns WHERE scannerId IN (SELECT id FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status = 11 AND paid_till < " + firstDayNextMonth + ") AND returnReason IN (2, 1) AND status IN (0, 1, 3);");
        dBMySQL.quit();
        return tempCountScanner;
    }

//    ez_finances

    @Step
    public void setCurrentDueForFleet(String valueCurrentDue, String idCarrier) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE ez_finances SET `currentDue`=-" + valueCurrentDue + " WHERE `carrierId`= " + idCarrier + ";");
        dBMySQL.quit();
    }

    @Step
    public void setPaidTillEstimatedTillEzFinancesFleet(String fleetId, String paidTill, String estimatedTill) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE ez_finances SET `paidTill` = '" + paidTill + "', `estimatedTill` = '" + estimatedTill + "' WHERE carrierId = " + fleetId + ";");
        dBMySQL.quit();
    }
    @Step
    public String getPaidTillEzFinancesFleet(String fleetId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        String tempPaidTill = dBMySQL.selectValue("SELECT paidTill FROM ez_finances WHERE carrierId = " + fleetId + ";");
        dBMySQL.quit();
        return tempPaidTill;
    }
    @Step
    public String getEstimatedTillEzFinancesFleet(String fleetId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        String tempEstimatedTill = dBMySQL.selectValue("SELECT estimatedTill FROM ez_finances WHERE carrierId = " + fleetId + ";");
        dBMySQL.quit();
        return tempEstimatedTill;
    }
    @Step
    public String getCurrentDueEzFinancesFleet(String fleetId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        String tempCurrentDue = dBMySQL.selectValue("SELECT currentDue FROM ez_finances WHERE carrierId = " + fleetId + ";");
        dBMySQL.quit();
        return tempCurrentDue;
    }

//    eld_personal_finances

    @Step
    public void setCurrentDueForSolo(String valueCurrentDue, String idSolo) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE eld_personal_finances SET `currentDue`=-" + valueCurrentDue + " WHERE `userId`= " + idSolo + ";");
        dBMySQL.quit();
    }

// ez_due

    @Step
    public String getLastDueForFleet(String idCarrier) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        String tempLastDue = dBMySQL.selectValue("SELECT amount FROM ez_due  WHERE carrierId = " + idCarrier + " ORDER BY dateTime desc LIMIT 1;");
        dBMySQL.quit();
        return tempLastDue;
    }
    @Step
    public String getLastDueForSolo(String idSolo) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        String tempLastDue = dBMySQL.selectValue("SELECT amount FROM ez_due  WHERE userId = " + idSolo + " ORDER BY dateTime desc LIMIT 1;");
        dBMySQL.quit();
        return tempLastDue;
    }
    @Step
    public List<String> getAmountEzDue(String soloOrFleetString, String userId, String timeRunCron) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempAmountList = dBMySQL.selectResultSet("SELECT amount FROM ez_due WHERE " + soloOrFleetString + " = " + userId + "  AND dateTime >= " + "'" +  timeRunCron + "'" + ";");
        dBMySQL.quit();
        return tempAmountList;
    }

    @Step
    public List<String> getDateTimeEzDue(String soloOrFleetString, String userId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempDateTimeEzDue = dBMySQL.selectResultSet("SELECT dateTime FROM ez_due  WHERE " + soloOrFleetString + " = " + userId + " ORDER BY dateTime desc LIMIT 1;");
        dBMySQL.quit();
        return tempDateTimeEzDue;
    }
    @Step
    public List<String> getDateTimeEzDueMonthToMonth(String soloOrFleetString, String userId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempDateTimeEzDue = dBMySQL.selectResultSet("SELECT dateTime FROM ez_due  WHERE " + soloOrFleetString + " = " + userId + " ORDER BY dateTime desc LIMIT 1;");
        dBMySQL.quit();
        return tempDateTimeEzDue;
    }
    @Step
    public List<String> getAmountEzDueMonthToMonth(String soloOrFleetString, String userId, String timeRunCron) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempAmountList = dBMySQL.selectResultSet("SELECT amount FROM ez_due WHERE " + soloOrFleetString + " = " + userId + " AND dateTime >= " + "'" +  timeRunCron + "'" + ";");
        dBMySQL.quit();
        return tempAmountList;
    }


//    user_event_manager

    @Step
    public void deleteEventNewOrder(String idOrder) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("DELETE FROM user_event_manager WHERE action = 'newOrder' AND data LIKE '%orderId%:" + idOrder + "%';");
        dBMySQL.quit();
    }

//    eld_history

    @Step
    public void setDateTimeEldHistoryForMonthToMonth(String soloOrFleetString, String userId, String tariffStart) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE eld_history SET `dateTime` = '" + tariffStart + "' WHERE status = 4 AND scannerId IN (SELECT id FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND tariffId IN (0, 9, 12));");
        dBMySQL.quit();
    }
    @Step
    public void setDateTimeEldHistoryByIdDevice(String tariffStart, String deviceId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE eld_history SET `dateTime` = '" + tariffStart + "' WHERE status = 4 AND scannerId IN (" + deviceId + ");");
        dBMySQL.quit();
    }
    @Step
    public void delete_102_Status(String userIdOrFleetString, String userId, String tariffId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("DELETE FROM eld_history WHERE status = 102 AND scannerId IN (SELECT id FROM eld_scanners WHERE " + userIdOrFleetString + " = " + userId + " AND tariffId = " + tariffId + ");");
        dBMySQL.quit();
    }
    @Step
    public void delete_102_StatusByIdDevice(String deviceId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("DELETE FROM eld_history WHERE status = 102 AND scannerId IN (" + deviceId + ");");
        dBMySQL.quit();
    }


// eld_personal_finances

    @Step
    public void setPaidTillEstimatedTillEzFinancesSolo(String soloId, String paidTill, String estimatedTill) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE eld_personal_finances SET `paidTill` = '" + paidTill + "', `estimatedTill` = '" + estimatedTill + "' WHERE userId = " + soloId + ";");
        dBMySQL.quit();
    }
    @Step
    public String getPaidTillEzFinancesSolo(String soloId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        String tempPaidTill = dBMySQL.selectValue("SELECT paidTill FROM eld_personal_finances WHERE userId = " + soloId + ";");
        dBMySQL.quit();
        return tempPaidTill;
    }
    @Step
    public String getEstimatedTillEzFinancesSolo(String soloId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        String tempEstimatedTill = dBMySQL.selectValue("SELECT estimatedTill FROM eld_personal_finances WHERE userId = " + soloId + ";");
        dBMySQL.quit();
        return tempEstimatedTill;
    }
    @Step
    public String getCurrentDueEzFinancesSolo(String soloId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        String tempCurrentDue = dBMySQL.selectValue("SELECT currentDue FROM eld_personal_finances WHERE userId = " + soloId + ";");
        dBMySQL.quit();
        return tempCurrentDue;
    }


//    authorize_clients

    @Step
    public void setCurrentCard_0_Fleet(String fleetId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE authorize_clients SET currentCard = 0 WHERE carrierId = " + fleetId + " AND currentCard = 1;");
        dBMySQL.quit();
    }
    @Step
    public void setCurrentCard_0_Solo(String soloId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE authorize_clients SET currentCard = 0 WHERE userId = " + soloId + " AND currentCard = 1;");
        dBMySQL.quit();
    }
    @Step
    public void setCurrentCard(String soloOrFleetString,  String userId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE authorize_clients SET currentCard = 1 WHERE " + soloOrFleetString + " = " + userId + " AND validCard = 1 ORDER BY id DESC LIMIT 1;");
        dBMySQL.quit();
    }

//  fleet_defaulters

    @Step
    public void setDateAndEmailFleetDefaulters(String dateTime, String lastEmailTime, String fleetId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE fleet_defaulters SET `dateTime` = '" + dateTime + "', `lastEmailTime` = '" + lastEmailTime + "' WHERE fleetId = " + fleetId + ";");
        dBMySQL.quit();
    }

    @Step
    public boolean checkFleetInDefaulters(String fleetId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        boolean tempResult = dBMySQL.isRowPresent("SELECT * FROM fleet_defaulters WHERE fleetId = " + fleetId + ";");
        dBMySQL.quit();
        return tempResult;
    }

    @Step
    public boolean checkFleetIsDeactivated(String fleetId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        boolean tempResult = dBMySQL.isRowPresent("SELECT * FROM fleet_defaulters WHERE fleetId = " + fleetId + " AND deactivated = 1;");
        dBMySQL.quit();
        return tempResult;
    }
    @Step
    public void set_0_DeactivatedFleet(String fleetId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE fleet_defaulters SET `deactivated` = 0 WHERE fleetId = " + fleetId + ";");
        dBMySQL.quit();
    }


//    carriers

    @Step
    public boolean checkFleetIsBanned(String fleetId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        boolean tempResult = dBMySQL.isRowPresent("SELECT * FROM carriers WHERE id = " + fleetId + " AND banned = 1;");
        dBMySQL.quit();
        return tempResult;
    }
    @Step
    public void setUnbanFleet(String fleetId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE carriers SET `banned` = 0 WHERE id = " + fleetId + ";");
        dBMySQL.quit();
    }

//    users

    @Step
    public boolean checkSoloIsBanned(String soloId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        boolean tempResult = dBMySQL.isRowPresent("SELECT * FROM users WHERE id = " + soloId + " AND banned = 1;");
        dBMySQL.quit();
        return tempResult;
    }
    @Step
    public void setUnbanSolo(String soloId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE users SET `banned` = 0 WHERE id = " + soloId + ";");
        dBMySQL.quit();
    }

//    driver_defaulters

    @Step
    public void setDateAndEmailSoloDefaulters(String dateTime, String lastEmailTime, String soloId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE driver_defaulters SET `dateTime` = '" + dateTime + "', `lastEmailTime` = '" + lastEmailTime + "' WHERE userId = " + soloId + ";");
        dBMySQL.quit();
    }
    @Step
    public void set_0_DeactivatedSolo(String soloId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE driver_defaulters SET `deactivated` = 0 WHERE userId = " + soloId + ";");
        dBMySQL.quit();
    }
    @Step
    public boolean checkSoloInDefaulters(String soloId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        boolean tempResult = dBMySQL.isRowPresent("SELECT * FROM driver_defaulters WHERE userId = " + soloId + ";");
        dBMySQL.quit();
        return tempResult;
    }
    @Step
    public boolean checkSoloIsDeactivated(String soloId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        boolean tempResult = dBMySQL.isRowPresent("SELECT * FROM driver_defaulters WHERE userId = " + soloId + " AND deactivated = 1;");
        dBMySQL.quit();
        return tempResult;
    }
//    SETTINGS
    @Step
    public void setOffCheckBoxDriverSetting(String userId, String checkBoxValue) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE driversData dd SET dd.HazMat = " + checkBoxValue + ", dd.Insurance = " + checkBoxValue + ", dd.TankerEndorsment = " + checkBoxValue + ", dd.yard = " + checkBoxValue + ", dd.conv = " + checkBoxValue + ", dd.hideEngineStatuses = " + checkBoxValue + ", dd.Sms = " + checkBoxValue + " WHERE dd.userId = " + userId + " AND dd.companyId = 0;");
        dBMySQL.quit();
    }

    @Step
    public String getValueFromSettings(String userId, String columnName) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        String tempValue = dBMySQL.selectValue("SELECT " + columnName + " FROM driversData WHERE userId = " + userId + " AND companyId = 0 ;");
        dBMySQL.quit();
        return tempValue;
    }
    @Step
    public void set_0_AobrdMPHDriverSettings(String userId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE driversRules dr SET dr.aobrdMPH = 0 WHERE userId = " + userId + ";");
        dBMySQL.quit();
    }
    @Step
    public boolean checkAobrdMPHDriverSettings(String userId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        boolean tempResult = dBMySQL.isRowPresent("SELECT * FROM driversRules WHERE userId = " + userId + " AND aobrdMPH > 0;");
        dBMySQL.quit();
        return tempResult;
    }
    @Step
    public List<String> getDataDriverSettings(String userId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempData = dBMySQL.selectTable("SELECT HazMat, Insurance, TankerEndorsment, yard, conv, hideEngineStatuses, Sms, City, Address, notes, Phone, SSN, EIN, MedCard, DateOfBirth, HireDate, TermitaneDate, PullNotice, DLExpiration  FROM driversData WHERE userId = " + userId + " AND companyId = 0 ;");
        dBMySQL.quit();
        return tempData;
    }




}
