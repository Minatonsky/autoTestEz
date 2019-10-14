package libs;


import io.qameta.allure.Step;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UtilsForDB {
     Database dBMySQL;


    @Step
    public String getLastOrderIdForFleet(String id) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        String tempIdOrder = dBMySQL.selectValue("SELECT id FROM eld_orders WHERE fleetId = " + id + " ORDER BY orderDate desc LIMIT 1;");
        dBMySQL.quit();
        return tempIdOrder;

    }
    @Step
    public String getLastOrderIdForSolo(String id) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        String tempIdOrder = dBMySQL.selectValue("SELECT id FROM eld_orders WHERE userId = " + id + " ORDER BY orderDate desc LIMIT 1;");
        dBMySQL.quit();
        return tempIdOrder;
    }
    @Step
    public void setCurrentDueForFleet(String valueCurrentDue, String idCarrier) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE ez_finances SET `currentDue`=-" + valueCurrentDue + " WHERE `carrierId`= " + idCarrier + ";");
        dBMySQL.quit();
    }
    @Step
    public void setCurrentDueForSolo(String valueCurrentDue, String idSolo) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE eld_personal_finances SET `currentDue`=-" + valueCurrentDue + " WHERE `userId`= " + idSolo + ";");
        dBMySQL.quit();
    }


    @Step
    public String getOrderStatus(String idOrder) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        String tempDeviceStatus = dBMySQL.selectValue("SELECT status FROM eld_orders WHERE id = " + idOrder + ";");
        dBMySQL.quit();
        return tempDeviceStatus;
    }

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
    public boolean isEldBlinded(String idOrder) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        boolean tempResult = dBMySQL.isRowPresent("SELECT * FROM eld_orders_ids WHERE orderId = " + idOrder + ";");
        dBMySQL.quit();
        return tempResult;
    }

    @Step
    public List<String> getIdEldFromOrder(String idOrder) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempIdDevices = dBMySQL.selectResultSet("SELECT id FROM eld_scanners WHERE id IN (SELECT scannerId FROM eld_orders_ids WHERE orderId = " + idOrder + ");");
        dBMySQL.quit();
        return tempIdDevices;
    }

    @Step
    public int getCountNewOrderForFleet(String fleetId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        int tempCountNewOrder = dBMySQL.getRowNumber("SELECT count(*) FROM eld_orders WHERE fleetId = " + fleetId + " AND status = 0;");
        dBMySQL.quit();
        return tempCountNewOrder;
    }
    @Step
    public int getCountNewOrderForSolo(String soloId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        int tempCountNewOrder = dBMySQL.getRowNumber("SELECT count(*) FROM eld_orders WHERE userId = " + soloId + " AND status = 0;");
        dBMySQL.quit();
        return tempCountNewOrder;
    }
    @Step
    public List<String> getIdOrderWithStatusNewForFleet(String fleetId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempIdDevices = dBMySQL.selectResultSet("SELECT id FROM eld_orders WHERE fleetId = " + fleetId + " AND status = 0;");
        dBMySQL.quit();
        return tempIdDevices;
    }

    @Step
    public List<String> getIdOrderWithStatusNewForSolo(String soloId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempIdDevices = dBMySQL.selectResultSet("SELECT id FROM eld_orders WHERE userId = " + soloId + " AND status = 0;");
        dBMySQL.quit();
        return tempIdDevices;
    }

    @Step
    public void deleteEventNewOrder(String idOrder) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("DELETE FROM user_event_manager WHERE action = 'newOrder' AND data LIKE '%orderId%:" + idOrder + "%';");
        dBMySQL.quit();
    }

    @Step
    public void changeStatusOrderToCancel(String idOrder) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE eld_orders SET status = 2 WHERE id = " + idOrder + ";");
        dBMySQL.quit();
    }
//////////////// CHARGE
    @Step
    public void setPaidTillAndTariffStartScannerForFleet(String fleetId, String paidTill, String tariffStart, String tariffId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE eld_scanners SET `paid_till`='" + paidTill + "', `tariffStart` = '" + tariffStart + "' WHERE fleet = " + fleetId + " AND tariffId = " + tariffId + ";");
        dBMySQL.quit();
    }
    @Step
    public void setPaidTillAndTariffStartScannerForSolo(String soloId, String paidTill, String tariffStart, String tariffId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE eld_scanners SET `paid_till`='" + paidTill + "', `tariffStart` = '" + tariffStart + "' WHERE userId = " + soloId + " AND tariffId = " + tariffId + ";");
        dBMySQL.quit();
    }
    @Step
    public int countChargeScannersWithTariff(String soloOrFleetString, String userId, String tariffId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        int tempCountScanner =dBMySQL.getRowNumber("SELECT count(*) FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status IN (4, 8, 103) AND tariffId = " + tariffId + ";");
        dBMySQL.quit();
        return tempCountScanner;
    }
    public int countDeactivatedChargeScannersMonthToMonth(String soloOrFleetString, String userId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        int tempCountScanner =dBMySQL.getRowNumber("SELECT count(*) FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status = 5 AND tariffId = 0;");
        dBMySQL.quit();
        return tempCountScanner;
    }

    @Step
    public void setPaidTillEstimatedTillEzFinancesFleet(String fleetId, String paidTill, String estimatedTill) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE ez_finances SET `paidTill` = '" + paidTill + "', `estimatedTill` = '" + estimatedTill + "' WHERE carrierId = " + fleetId + ";");
        dBMySQL.quit();
    }
    @Step
    public void setPaidTillEstimatedTillEzFinancesSolo(String soloId, String paidTill, String estimatedTill) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE eld_personal_finances SET `paidTill` = '" + paidTill + "', `estimatedTill` = '" + estimatedTill + "' WHERE userId = " + soloId + ";");
        dBMySQL.quit();
    }
    @Step
    public List<String> getAmountEzDue(String soloOrFleetString, String userId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempAmountList = dBMySQL.selectResultSet("SELECT amount FROM ez_due WHERE " + soloOrFleetString + " = " + userId + " ORDER BY dateTime DESC LIMIT 3;");
        dBMySQL.quit();
        return tempAmountList;
    }
    @Step
    public List<String> getAmountEzDueSolo(String soloId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempAmountList = dBMySQL.selectResultSet("SELECT amount FROM ez_due WHERE userId = " + soloId + " ORDER BY dateTime DESC LIMIT 3;");
        dBMySQL.quit();
        return tempAmountList;
    }
    @Step
    public List<String> getPaidTillForFleet(String fleetId, String tariffId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempPaidTillAndTariffStartList = dBMySQL.selectResultSet("SELECT paid_till FROM eld_scanners WHERE fleet = " + fleetId + " AND status = 4 AND tariffId = " + tariffId + "");
        dBMySQL.quit();
        return tempPaidTillAndTariffStartList;
    }
    @Step
    public List<String> getPaidTillForSolo(String soloId, String tariffId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempPaidTillAndTariffStartList = dBMySQL.selectResultSet("SELECT paid_till FROM eld_scanners WHERE userId = " + soloId + " AND status = 4 AND tariffId = " + tariffId + "");
        dBMySQL.quit();
        return tempPaidTillAndTariffStartList;
    }
    @Step
    public List<String> getDateTimeEzDue(String soloOrFleetString, String userId) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        List<String> tempDateTimeEzDue = dBMySQL.selectResultSet("SELECT dateTime FROM ez_due  WHERE " + soloOrFleetString + " = " + userId + " ORDER BY dateTime desc LIMIT 3;");
        dBMySQL.quit();
        return tempDateTimeEzDue;
    }


}
