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
        List<String> tempIdDevices = dBMySQL.selectResultSet("SELECT status FROM eld_scanners WHERE id IN (SELECT scannerId FROM eld_orders_ids WHERE orderId = " + idOrder + ");");
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
        int effectedRows = dBMySQL.changeTable("DELETE FROM user_event_manager WHERE action = 'newOrder' AND data LIKE '%orderId%:" + idOrder + "%';");
        dBMySQL.quit();
    }

    @Step
    public void changeStatusOrderToCancel(String idOrder) throws SQLException, IOException, ClassNotFoundException {
        dBMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dBMySQL.changeTable("UPDATE eld_orders SET status = 2 WHERE id = " + idOrder + ";");
        dBMySQL.quit();
    }
}
