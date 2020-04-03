package libs;


import io.qameta.allure.Step;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtilsForDB {
     Database dBMySQL;


    public UtilsForDB(Database dBMySQL) {
        this.dBMySQL = dBMySQL;
    }


// eld_orders

    @Step
    public String getLastOrderId(String fleetUserId, String id) throws SQLException {
        String tempIdOrder = dBMySQL.selectValue("SELECT id FROM eld_orders WHERE " + fleetUserId + " = " + id + " ORDER BY orderDate desc LIMIT 1;");
        return tempIdOrder;
    }
    @Step
    public String getOrderStatus(String idOrder) throws SQLException {
        String tempDeviceStatus = dBMySQL.selectValue("SELECT status FROM eld_orders WHERE id = " + idOrder + ";");
        return tempDeviceStatus;
    }
    @Step
    public List<String> getIdOrderWithStatusNew(String fleetUserId, String fleetId) throws SQLException {
        List<String> tempIdDevices = dBMySQL.selectResultSet("SELECT id FROM eld_orders WHERE " + fleetUserId + " = " + fleetId + " AND status = 0;");
        return tempIdDevices;
    }
    @Step
    public void setOrderDateByTariffId(String soloOrFleetString, String userId, String tariffStart, String tariffId) throws SQLException {
        dBMySQL.changeTable("UPDATE eld_orders SET `orderDate` = '" + tariffStart + "' WHERE id IN (SELECT orderId FROM eld_orders_ids WHERE scannerId IN (SELECT id FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND tariffId = " + tariffId + "));");
    }
    @Step
    public void setOrderDateByDeviceId(String tariffStart, String deviceId) throws SQLException {
        dBMySQL.changeTable("UPDATE eld_orders SET `orderDate` = '" + tariffStart + "' WHERE id IN (SELECT orderId FROM eld_orders_ids WHERE scannerId IN (" + deviceId + "));");
    }

    @Step
    public int getCountNewOrder(String fleetUserId, String fleetId) throws SQLException {
        int tempCountNewOrder = dBMySQL.getRowNumber("SELECT count(*) FROM eld_orders WHERE " + fleetUserId + " = " + fleetId + " AND status = 0;");
        return tempCountNewOrder;
    }

    @Step
    public void changeStatusOrderToCancel(String idOrder) throws SQLException {
        dBMySQL.changeTable("UPDATE eld_orders SET status = 2 WHERE id = " + idOrder + ";");
    }

// eld_scanners

    @Step
    public List<String> getStatusEldInOrder(String idOrder) throws SQLException {
        List<String> tempStatusEld = dBMySQL.selectResultSet("SELECT status FROM eld_scanners WHERE id IN (SELECT scannerId FROM eld_orders_ids WHERE orderId = " + idOrder + ");");
        return tempStatusEld;
    }
    @Step
    public String getRandomDevicesRandomLocalId(String fleetId) throws SQLException {
        String randomLocalId = dBMySQL.selectValue("SELECT e.localId FROM eld_scanners e WHERE e.fleet = " + fleetId + " AND e.`status` = 4 ORDER BY RAND()LIMIT 1;");
        return randomLocalId;
    }

    @Step
    public List<String> getLocalIdDevices(String idOrder) throws SQLException {
        List<String> tempLocalIdDevices = dBMySQL.selectResultSet("SELECT localId FROM eld_scanners WHERE id IN (SELECT scannerId FROM eld_orders_ids WHERE orderId = " + idOrder + ");");
        return tempLocalIdDevices;
    }
    @Step
    public void setPaidTillAndTariffStartScannerForFleet(String fleetId, String paidTill, String tariffStart, String tariffId) throws SQLException {
        dBMySQL.changeTable("UPDATE eld_scanners SET `paid_till`='" + paidTill + "', `tariffStart` = '" + tariffStart + "' WHERE fleet = " + fleetId + " AND tariffId IN(" + tariffId + ");");
    }
    @Step
    public void setPaidTillAndTariffStartScannerById(String paidTill, String tariffStart, String idDevice) throws SQLException {
        dBMySQL.changeTable("UPDATE eld_scanners SET `paid_till`='" + paidTill + "', `tariffStart` = '" + tariffStart + "' WHERE id IN (" + idDevice + ");");
    }
    @Step
    public void setStatusesForDevices(String listOfStatusDevices, String status) throws SQLException {
        dBMySQL.changeTable("UPDATE eld_scanners SET `status`='" + status + "' WHERE id IN(" + listOfStatusDevices + ");");
    }
    @Step
    public List<String> getIdEldFromOrder(String idOrder) throws SQLException{
        List<String> tempIdDevices = dBMySQL.selectResultSet("SELECT id FROM eld_scanners WHERE id IN (SELECT scannerId FROM eld_orders_ids WHERE orderId = " + idOrder + ");");
        return tempIdDevices;
    }
    @Step
    public List<String> getIdChargeScannersByTariff(String soloOrFleetString, String userId, String tariffId, int countDevices) throws SQLException {
        List<String> tempDeviceStatus = dBMySQL.selectResultSet("SELECT id FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND tariffId = " + tariffId + " AND status = 4 ORDER BY id DESC LIMIT " + countDevices + ";");
        return tempDeviceStatus;
    }
    @Step
    public void setPaidTillAndTariffStartScannerForSolo(String soloId, String paidTill, String tariffStart, String tariffId) throws SQLException {
        dBMySQL.changeTable("UPDATE eld_scanners SET `paid_till`='" + paidTill + "', `tariffStart` = '" + tariffStart + "' WHERE userId = " + soloId + " AND tariffId = " + tariffId + ";");
    }
    @Step
    public int countChargeScannersByTariff(String soloOrFleetString, String userId, String tariffId) throws SQLException {
        int tempCountScanner =dBMySQL.getRowNumber("SELECT count(*) FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status IN (4, 8, 103) AND tariffId = " + tariffId + ";");
        return tempCountScanner;
    }
    @Step
    public int countChargeScanners(String soloOrFleetString, String userId, String firstDayNextMonth) throws SQLException {
        int tempCountScanner =dBMySQL.getRowNumber("SELECT count(*) FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status IN (4, 5, 8, 103) AND paid_till < " + firstDayNextMonth + ";");
        return tempCountScanner;
    }
    @Step
    public int countDeactivatedChargeScannersMonthToMonth(String soloOrFleetString, String userId) throws SQLException {
        int tempCountScanner =dBMySQL.getRowNumber("SELECT count(*) FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status = 5 AND tariffId IN (0, 9);");
        return tempCountScanner;
    }
    @Step
    public List<String> getScannersTariffStart(String scannerId) throws SQLException {
        List<String> tempCountScanner =dBMySQL.selectResultSet("SELECT tariffStart FROM eld_scanners WHERE id IN(" + scannerId + ");");
        return tempCountScanner;
    }
    @Step
    public List<String> getPaidTillFromEldScanners(String soloOrFleetString, String userId, String tariffId) throws SQLException {
        List<String> tempPaidTillAndTariffStartList = dBMySQL.selectResultSet("SELECT paid_till FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status = 4 AND tariffId IN (" + tariffId + ")");
        return tempPaidTillAndTariffStartList;
    }
    @Step
    public List<String> getParamsDeactivatedScanners(String soloOrFleetString, String userId) throws SQLException {
        List<String> tempAmountList = dBMySQL.selectResultSet("SELECT params FROM eld_scanners WHERE id IN (SELECT id FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status = 5 AND tariffId = 0);");
        return tempAmountList;
    }
    @Step
    public List<String> getIdScannersByStatus(String soloOrFleetString, String userId, String numberStatus) throws SQLException {
        List<String> tempIdDevices = dBMySQL.selectResultSet("SELECT id FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status = " + numberStatus + ";");
        return tempIdDevices;
    }
    @Step
    public List<String> getScannersStatus(String scannerId) throws SQLException {
        List<String> tempScannersStatuses = dBMySQL.selectResultSet("SELECT status FROM eld_scanners WHERE id IN(" + scannerId + ");");
        return tempScannersStatuses;
    }
    @Step
    public String getScannersStatusById(String scannerId) throws SQLException {
        String tempScannersStatuses = dBMySQL.selectValue("SELECT status FROM eld_scanners WHERE id = " + scannerId + ";");
        return tempScannersStatuses;
    }
    @Step
    public String getIdDeviceByLocalId(String fleetId, String localId) throws SQLException {
        String idDevice = dBMySQL.selectValue("SELECT e.id FROM eld_scanners e WHERE e.localId = " + localId + " AND e.fleet = " + fleetId + ";");
        return idDevice;
    }

//    eld_orders_ids

    @Step
    public boolean isEldBlinded(String idOrder) throws SQLException {
        boolean tempResult = dBMySQL.isRowPresent("SELECT * FROM eld_orders_ids WHERE orderId = " + idOrder + ";");
        return tempResult;
    }

//   eld_returns
    @Step
    public int countChargeReturnedScannerByTariff(String soloOrFleetString, String userId, String tariffId) throws SQLException {
        int tempCountScanner = dBMySQL.getRowNumber("SELECT count(*) FROM eld_returns WHERE scannerId IN (SELECT id FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status = 11 AND tariffId = " + tariffId + ") AND returnReason IN (2, 1) AND status IN (0, 1, 3);");
        return tempCountScanner;
    }
    @Step
    public int countChargeReturnedScanners(String soloOrFleetString, String userId, String firstDayNextMonth) throws SQLException {
        int tempCountScanner = dBMySQL.getRowNumber("SELECT count(*) FROM eld_returns WHERE scannerId IN (SELECT id FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status = 11 AND paid_till < " + firstDayNextMonth + ") AND returnReason IN (2, 1) AND status IN (0, 1, 3);");
        return tempCountScanner;
    }

//    ez_finances

    @Step
    public void setCurrentDueForFleet(String valueCurrentDue, String idCarrier) throws SQLException {
        dBMySQL.changeTable("UPDATE ez_finances SET `currentDue`=-" + valueCurrentDue + " WHERE `carrierId`= " + idCarrier + ";");
    }

    @Step
    public void setPaidTillEstimatedTillEzFinancesFleet(String fleetId, String paidTill, String estimatedTill) throws SQLException {
        dBMySQL.changeTable("UPDATE ez_finances SET `paidTill` = '" + paidTill + "', `estimatedTill` = '" + estimatedTill + "' WHERE carrierId = " + fleetId + ";");
    }
    @Step
    public String getPaidTillEzFinancesFleet(String fleetId) throws SQLException {
        String tempPaidTill = dBMySQL.selectValue("SELECT paidTill FROM ez_finances WHERE carrierId = " + fleetId + ";");
        return tempPaidTill;
    }
    @Step
    public String getEstimatedTillEzFinancesFleet(String fleetId) throws SQLException {
        String tempEstimatedTill = dBMySQL.selectValue("SELECT estimatedTill FROM ez_finances WHERE carrierId = " + fleetId + ";");
        return tempEstimatedTill;
    }
    @Step
    public String getCurrentDueEzFinancesFleet(String fleetId) throws SQLException {
        String tempCurrentDue = dBMySQL.selectValue("SELECT currentDue FROM ez_finances WHERE carrierId = " + fleetId + ";");
        return tempCurrentDue;
    }

//    eld_personal_finances

    @Step
    public void setCurrentDueForSolo(String valueCurrentDue, String idSolo) throws SQLException {
        dBMySQL.changeTable("UPDATE eld_personal_finances SET `currentDue`=-" + valueCurrentDue + " WHERE `userId`= " + idSolo + ";");
    }

// ez_due

    @Step
    public List<ArrayList> getLastDueDataList(String soloOrFleetString, String userId) throws SQLException {
        List<ArrayList> tempLastDue = dBMySQL.selectTable("SELECT e.amount, e.dateTime, e.description, e.userId, e.balance, e.`type` FROM ez_due e WHERE " + soloOrFleetString + " = " + userId + " ORDER BY e.dateTime DESC LIMIT 1;");
        return tempLastDue;
    }

    @Step
    public String getLastDueForFleet(String idCarrier) throws SQLException {
        String tempLastDue = dBMySQL.selectValue("SELECT amount FROM ez_due  WHERE carrierId = " + idCarrier + " ORDER BY dateTime desc LIMIT 1;");
        return tempLastDue;
    }
    @Step
    public String getLastDueForSolo(String idSolo) throws SQLException{
        String tempLastDue = dBMySQL.selectValue("SELECT amount FROM ez_due  WHERE userId = " + idSolo + " ORDER BY dateTime desc LIMIT 1;");
        return tempLastDue;
    }
    @Step
    public List<String> getAmountEzDue(String soloOrFleetString, String userId, String timeRunCron) throws SQLException{
        List<String> tempAmountList = dBMySQL.selectResultSet("SELECT amount FROM ez_due WHERE " + soloOrFleetString + " = " + userId + "  AND dateTime >= " + "'" +  timeRunCron + "'" + ";");
        return tempAmountList;
    }

    @Step
    public List<String> getDateTimeEzDue(String soloOrFleetString, String userId) throws SQLException{
        List<String> tempDateTimeEzDue = dBMySQL.selectResultSet("SELECT dateTime FROM ez_due  WHERE " + soloOrFleetString + " = " + userId + " ORDER BY dateTime desc LIMIT 1;");
        return tempDateTimeEzDue;
    }
    @Step
    public List<String> getDateTimeEzDueMonthToMonth(String soloOrFleetString, String userId) throws SQLException{
        List<String> tempDateTimeEzDue = dBMySQL.selectResultSet("SELECT dateTime FROM ez_due  WHERE " + soloOrFleetString + " = " + userId + " ORDER BY dateTime desc LIMIT 1;");
        return tempDateTimeEzDue;
    }
    @Step
    public List<String> getAmountEzDueMonthToMonth(String soloOrFleetString, String userId, String timeRunCron) throws SQLException{
        List<String> tempAmountList = dBMySQL.selectResultSet("SELECT amount FROM ez_due WHERE " + soloOrFleetString + " = " + userId + " AND dateTime >= " + "'" +  timeRunCron + "'" + ";");
        return tempAmountList;
    }


//    user_event_manager

    @Step
    public void deleteEventNewOrder(String idOrder) throws SQLException{
        dBMySQL.changeTable("DELETE FROM user_event_manager WHERE action = 'newOrder' AND data LIKE '%orderId%:" + idOrder + "%';");
    }

//    eld_history

    @Step
    public void setDateTimeEldHistoryForMonthToMonth(String soloOrFleetString, String userId, String tariffStart) throws SQLException{
        dBMySQL.changeTable("UPDATE eld_history SET `dateTime` = '" + tariffStart + "' WHERE status = 4 AND scannerId IN (SELECT id FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND tariffId IN (0, 9, 12));");
    }
    @Step
    public void setDateTimeEldHistoryByIdDevice(String tariffStart, String deviceId) throws SQLException{
        dBMySQL.changeTable("UPDATE eld_history SET `dateTime` = '" + tariffStart + "' WHERE status = 4 AND scannerId IN (" + deviceId + ");");
    }
    @Step
    public void delete_102_Status(String userIdOrFleetString, String userId, String tariffId) throws SQLException{
        dBMySQL.changeTable("DELETE FROM eld_history WHERE status = 102 AND scannerId IN (SELECT id FROM eld_scanners WHERE " + userIdOrFleetString + " = " + userId + " AND tariffId = " + tariffId + ");");
    }
    @Step
    public void delete_102_StatusByIdDevice(String deviceId) throws SQLException{
        dBMySQL.changeTable("DELETE FROM eld_history WHERE status = 102 AND scannerId IN (" + deviceId + ");");
    }


// eld_personal_finances

    @Step
    public void setPaidTillEstimatedTillEzFinancesSolo(String soloId, String paidTill, String estimatedTill) throws SQLException{
        dBMySQL.changeTable("UPDATE eld_personal_finances SET `paidTill` = '" + paidTill + "', `estimatedTill` = '" + estimatedTill + "' WHERE userId = " + soloId + ";");
    }
    @Step
    public String getPaidTillEzFinancesSolo(String soloId) throws SQLException{
        String tempPaidTill = dBMySQL.selectValue("SELECT paidTill FROM eld_personal_finances WHERE userId = " + soloId + ";");
        return tempPaidTill;
    }
    @Step
    public String getEstimatedTillEzFinancesSolo(String soloId) throws SQLException{
        String tempEstimatedTill = dBMySQL.selectValue("SELECT estimatedTill FROM eld_personal_finances WHERE userId = " + soloId + ";");
        return tempEstimatedTill;
    }
    @Step
    public String getCurrentDueEzFinancesSolo(String soloId) throws SQLException{
        String tempCurrentDue = dBMySQL.selectValue("SELECT currentDue FROM eld_personal_finances WHERE userId = " + soloId + ";");
        return tempCurrentDue;
    }


//    authorize_clients

    @Step
    public void setCurrentCard_0_Fleet(String fleetId) throws SQLException{
        dBMySQL.changeTable("UPDATE authorize_clients SET currentCard = 0 WHERE carrierId = " + fleetId + " AND currentCard = 1;");
    }
    @Step
    public void setCurrentCard_0_Solo(String soloId) throws SQLException{
        dBMySQL.changeTable("UPDATE authorize_clients SET currentCard = 0 WHERE userId = " + soloId + " AND currentCard = 1;");
    }
    @Step
    public void setCurrentCard(String soloOrFleetString,  String userId) throws SQLException{
        dBMySQL.changeTable("UPDATE authorize_clients SET currentCard = 1 WHERE " + soloOrFleetString + " = " + userId + " AND validCard = 1 ORDER BY id DESC LIMIT 1;");
    }

//  fleet_defaulters

    @Step
    public void setDateAndEmailFleetDefaulters(String dateTime, String lastEmailTime, String fleetId) throws SQLException{
        dBMySQL.changeTable("UPDATE fleet_defaulters SET `dateTime` = '" + dateTime + "', `lastEmailTime` = '" + lastEmailTime + "' WHERE fleetId = " + fleetId + ";");
    }

    @Step
    public boolean checkFleetInDefaulters(String fleetId) throws SQLException{
        boolean tempResult = dBMySQL.isRowPresent("SELECT * FROM fleet_defaulters WHERE fleetId = " + fleetId + ";");
        return tempResult;
    }

    @Step
    public boolean checkFleetIsDeactivated(String fleetId) throws SQLException{
        boolean tempResult = dBMySQL.isRowPresent("SELECT * FROM fleet_defaulters WHERE fleetId = " + fleetId + " AND deactivated = 1;");
        return tempResult;
    }
    @Step
    public void set_0_DeactivatedFleet(String fleetId) throws SQLException{
        dBMySQL.changeTable("UPDATE fleet_defaulters SET `deactivated` = 0 WHERE fleetId = " + fleetId + ";");
    }


//    carriers

    @Step
    public boolean checkFleetIsBanned(String fleetId) throws SQLException{
        boolean tempResult = dBMySQL.isRowPresent("SELECT * FROM carriers WHERE id = " + fleetId + " AND banned = 1;");
        return tempResult;
    }
    @Step
    public void setUnbanFleet(String fleetId) throws SQLException{
        dBMySQL.changeTable("UPDATE carriers SET `banned` = 0 WHERE id = " + fleetId + ";");
    }

//    users

    @Step
    public boolean checkSoloIsBanned(String soloId) throws SQLException{
        boolean tempResult = dBMySQL.isRowPresent("SELECT * FROM users WHERE id = " + soloId + " AND banned = 1;");
        return tempResult;
    }
    @Step
    public void setUnbanSolo(String soloId) throws SQLException{
        dBMySQL.changeTable("UPDATE users SET `banned` = 0 WHERE id = " + soloId + ";");
    }
    @Step
    public String getUserIdByEmail(String email) throws SQLException{
        String tempCurrentDue = dBMySQL.selectValue("SELECT id FROM users WHERE email = '" + email + "';");
        return tempCurrentDue;
    }
    @Step
    public String getFleetIdByEmail(String email) throws SQLException{
        String tempCurrentDue = dBMySQL.selectValue("SELECT carrierId FROM users WHERE email = '" + email + "';");
        return tempCurrentDue;
    }

    @Step
    public String getRandomDriverEmail(String fleetId) throws SQLException {
        String tempCurrentDue = dBMySQL.selectValue("SELECT u.email FROM users u WHERE u.carrierId = " + fleetId + " AND companyPosition = 7 ORDER BY RAND()LIMIT 1;");
        return tempCurrentDue;
    }
    @Step
    public String getUserEmailById(String id) throws SQLException{
        String tempCurrentDue = dBMySQL.selectValue("SELECT email FROM users WHERE id = '" + id + "';");
        return tempCurrentDue;
    }

//    driver_defaulters

    @Step
    public void setDateAndEmailSoloDefaulters(String dateTime, String lastEmailTime, String soloId) throws SQLException{
        dBMySQL.changeTable("UPDATE driver_defaulters SET `dateTime` = '" + dateTime + "', `lastEmailTime` = '" + lastEmailTime + "' WHERE userId = " + soloId + ";");
    }
    @Step
    public void set_0_DeactivatedSolo(String soloId) throws SQLException{
        dBMySQL.changeTable("UPDATE driver_defaulters SET `deactivated` = 0 WHERE userId = " + soloId + ";");
    }
    @Step
    public boolean checkSoloInDefaulters(String soloId) throws SQLException{
        boolean tempResult = dBMySQL.isRowPresent("SELECT * FROM driver_defaulters WHERE userId = " + soloId + ";");
        return tempResult;
    }
    @Step
    public boolean checkSoloIsDeactivated(String soloId) throws SQLException{
        boolean tempResult = dBMySQL.isRowPresent("SELECT * FROM driver_defaulters WHERE userId = " + soloId + " AND deactivated = 1;");
        return tempResult;
    }

//    SETTINGS

    @Step
    public void set_0_AobrdMPHDriverSettings(String userId) throws SQLException{
        dBMySQL.changeTable("UPDATE driversRules dr SET dr.aobrdMPH = 0 WHERE userId = " + userId + ";");
    }
    @Step
    public void set_0_AobrdMPHCarrierSettings(String carrierId) throws SQLException{
        dBMySQL.changeTable("UPDATE carriers c SET c.aobrdMPH = 0 WHERE c.id = " + carrierId + ";");
    }
    @Step
    public boolean checkAobrdMPHDriverSettings(String userId) throws SQLException{
        boolean tempResult = dBMySQL.isRowPresent("SELECT * FROM driversRules WHERE userId = " + userId + " AND aobrdMPH > 0;");
        return tempResult;
    }

    @Step
    public List<ArrayList> getDataDriverSettings(String userId) throws SQLException{
        List<ArrayList> tempData = dBMySQL.selectTable("SELECT HazMat, Insurance, TankerEndorsment, yard, conv, hideEngineStatuses, Sms, City, Address, notes, Phone, SSN, EIN, MedCard, DateOfBirth, HireDate, TermitaneDate, PullNotice, DLExpiration, DLNumber, DLState, scanner_type  FROM driversData WHERE userId = " + userId + " AND companyId = 0;");
        return tempData;
    }

//    EQUIPMENT


    @Step
    public List<ArrayList> getDataEquipment(String carrierUser, String userId, String idEquipment) throws SQLException{
        List<ArrayList> tempData = dBMySQL.selectTable("SELECT e.id, e.truckTrailer, e.Notes, e.Name, e.Owner, e.Year, e.`Type`, e.TireSize, e.Fuel, e.Axel, e.Color, e.Make, e.Model, e.VIN, e.GrossWeight, " +
                "e.UnlandWeight, e.Plate, e.State, e.NYCert, e.InspectionDue, e.`90DayExp`, e.ProRateExp, e.ExpDate, e.isActive, e.Length FROM equipment e WHERE " + carrierUser + " = " + userId + " and e.id = '" + idEquipment + "' ORDER BY e.id DESC LIMIT 1;");
        return tempData;
    }
    @Step
    public String getEquipmentId(String carrierUser, String soloId, String equipName) throws SQLException{
        String tempData = dBMySQL.selectValue("SELECT e.id FROM equipment e WHERE " + carrierUser + " = " + soloId + " AND e.Name = '" + equipName + "' ORDER BY e.id DESC LIMIT 1;");
        return tempData;
    }

    @Step
    public String getRandomEquipmentId(String soloId, String truckTrailer) throws SQLException{
        String tempCurrentDue = dBMySQL.selectValue("SELECT e.id FROM equipment e WHERE e.userId = " + soloId + " AND e.truckTrailer = " + truckTrailer + "  ORDER BY RAND()LIMIT 1;");
        return tempCurrentDue;
    }

    @Step
    public String getRandomEquipmentIdCarrier(String carrierId, String truckTrailer) throws SQLException{
        String tempCurrentDue = dBMySQL.selectValue("SELECT e.id FROM equipment e WHERE e.carrierId = " + carrierId + " AND e.truckTrailer = " + truckTrailer + "  ORDER BY RAND()LIMIT 1;");
        return tempCurrentDue;
    }
    @Step
    public String getEquipmentName(String equipmentId) throws SQLException{
        String tempCurrentDue = dBMySQL.selectValue("SELECT e.Name FROM equipment e WHERE e.id = " + equipmentId + " ;");
        return tempCurrentDue;
    }

    @Step
    public String getRandomUserIdCarrier(String carrierId) throws SQLException{
        String tempCurrentDue = dBMySQL.selectValue("SELECT id FROM users u WHERE u.carrierId = " + carrierId + " ORDER BY RAND()LIMIT 1;");
        return tempCurrentDue;
    }
    @Step
    public String getRandomDriverIdInFleet(String carrierId) throws SQLException{
        String tempCurrentDue = dBMySQL.selectValue("SELECT id FROM users u WHERE u.carrierId = " + carrierId + " AND u.companyPosition = 7 ORDER BY RAND()LIMIT 1;");
        return tempCurrentDue;
    }
    @Step
    public String getRandomDriverNameInFleet(String userId) throws SQLException{
        String tempCurrentDue = dBMySQL.selectValue("SELECT CONCAT(u.name, \" \", u.`last`) FROM users u WHERE u.id = " + userId + ";");
        return tempCurrentDue;
    }

    @Step
    public String getDocInfoData(String docId, String infoName) throws SQLException{
        String tempData = dBMySQL.selectValue("SELECT infoData from docsInfo di WHERE di.docId = " + docId + " AND infoName = '" + infoName + "';");
        return tempData;
    }
    @Step
    public List<ArrayList> getDocData(String userId, String reference) throws SQLException{
        List<ArrayList> tempData = dBMySQL.selectTable("SELECT d.id, d.awsName, d.`type`, d.date, d.carrierId, d.initiatorId, d.truckId, d.note FROM documents d WHERE d.userId = " + userId + " and d.reference = '" + reference + "';");
        return tempData;
    }
    @Step
    public String getRandomDocumentReference(String userId, String typeDoc) throws SQLException{
        String tempCurrentDue = dBMySQL.selectValue("SELECT reference FROM documents d WHERE d.`type` = " + typeDoc + " AND d.userId = " + userId + " ORDER BY RAND()LIMIT 1;");
        return tempCurrentDue;
    }
    @Step
    public String getUrlPermitDoc(String equipmentId, String shortName) throws SQLException{
        String tempUrl = dBMySQL.selectValue("SELECT url FROM permitDocs pd WHERE pd.equipmentId = " + equipmentId + " AND pd.shortName = '" + shortName + "';");
        return tempUrl;
    }

    @Step
    public List<ArrayList> getUsersAndDriversRulesData(String userEmail) throws SQLException{
        List<ArrayList> tempData = dBMySQL.selectTable("SELECT dr.carrierName, dr.carrierAddress, dr.carrierCity, dr.carrierState, dr.carrierZip, dr.homeTerminal, dr.coDrivers, dr.cycleId, dr.timeZoneId, dr.usdot, dr.odometerId,dr.restBreakId, \n" +
                "dr.logIncrementId, dr.cargoTypeId, dr.wellSiteId, dr.restartId, dr.iftaDistances, dr.old_allow_edit, dr.aobrdMPH, dr.teamDriver, dr.teamDriverPassword, u.id, u.name, u.`last`, u.phone, \n" +
                "u.password, u.googleId, u.fbId, u.carrierId, u.companyPosition, u.banned FROM driversRules dr LEFT JOIN users u ON u.id = dr.userId WHERE u.email = '" + userEmail + "';");
        return tempData;
    }
    @Step
    public List<ArrayList> getCarrierRulesData(String carrierId) throws SQLException {
        List<ArrayList> tempData = dBMySQL.selectTable("SELECT c.usdot, c.name, c.state, c.city, c.address, c.zip, c.size, c.registrationDate, c.aobrdMPH, c.ownerId, c.ein, c.banned, f.cycleId, f.timeZoneId, f.agricultureDeliveries FROM carriers c\n" +
                "LEFT JOIN fleetRules f ON f.carrierId = c.id\n" +
                " WHERE c.id = " + carrierId + ";");
        return tempData;
    }
    @Step
    public List<ArrayList> getReminderData(String id, String name) throws SQLException{
        List<ArrayList> tempData = dBMySQL.selectTable("SELECT e.`type`, e.`status`, case when e.remindDateTime = 0 then '0' ELSE e.remindDateTime end as remindDateTime, e.term, e.odometerStart, e.currentOdometer, e.whatSend FROM equipment_reminder e WHERE e.equipId = " + id + " AND e.name = '" + name + "';");
        return tempData;
    }
    @Step
    public void deleteStatuses(String userId) throws SQLException{
        dBMySQL.changeTable("DELETE FROM status WHERE userId = " + userId + " AND DATETIME != '2019-11-14 00:00:00';");
    }
    @Step
    public void updateLastStatus(String userId) throws SQLException{
        dBMySQL.changeTable("UPDATE driver_last_status d SET d.dateTime = '2019-11-14 00:00:00', d.dateTimeUtc = '2019-11-14 05:00:00', d.drive = 39600, d.shift = 50400, d.cycle = 216000, d.eight = 28800, d.shiftWork = 9999999, d.restart34 = 0 WHERE d.userId = " + userId + ";");
    }
    @Step
    public boolean isCurrentCardSame(String userId, String cardNumber) throws SQLException{
        boolean tempResult = dBMySQL.isRowPresent("SELECT * FROM authorize_clients a WHERE a.userId = " + userId + " AND a.currentCard = 1 AND a.creditCard = '" + cardNumber + "';");
        return tempResult;
    }

//    SERVICES

    @Step
    public String getSmartSafetyUserId(String fleetId) throws SQLException{
        String tempId = dBMySQL.selectValue("SELECT u.id FROM user_app_info uai LEFT JOIN users u ON uai.userId = u.id WHERE u.carrierId = " + fleetId + " AND uai.field = 'smart_safety' ORDER BY RAND() LIMIT 1;");
        return tempId;
    }
    @Step
    public void updateServicesConnections(String fleetId, String userId, String dateTimeAt, String dateTimeTill) throws SQLException{
        dBMySQL.changeTable("UPDATE services_connections SET created_at = '" + dateTimeAt + "', subscribed_till = '" + dateTimeTill + "' WHERE user_id = " + userId + " AND carrier_id = " + fleetId + ";");
    }
    @Step
    public boolean checkForTransactions(String fleetId, String dateTime) throws SQLException{
        boolean tempResult = dBMySQL.isRowPresent("SELECT * FROM services_transactions s WHERE s.carrier_id = " + fleetId + " AND s.created_at > '" + dateTime + "';");
        return tempResult;
    }
    @Step
    public String getSubscribedTillDateTime(String fleetId, String userId) throws SQLException {
        String tempDateTime = dBMySQL.selectValue("SELECT s.subscribed_till FROM services_connections s WHERE s.carrier_id = " + fleetId + " AND s.user_id = " + userId + " AND s.service_id = 1;");
        return tempDateTime;
    }
    @Step
    public List<ArrayList> getAtTillDateTimeServices(String fleetId, String userId) throws SQLException {
        List<ArrayList> tempData = dBMySQL.selectTable("SELECT s.created_at, s.updated_at, s.subscribed_till,  s.noticed_at FROM services_connections s WHERE s.carrier_id = " + fleetId + " AND s.user_id = " + userId + " AND s.service_id = 1;");
        return tempData;
    }
    @Step
    public void deleteSmartSafetyFoDriver(String driverId) throws SQLException {
        dBMySQL.changeTable("DELETE FROM user_app_info WHERE userId = " + driverId + ";");
        dBMySQL.changeTable("DELETE FROM services_history WHERE user_id = " + driverId + ";");
        dBMySQL.changeTable("DELETE FROM services_connections WHERE user_id = " + driverId + ";");
    }
    @Step
    public boolean isSmartSafetyInUserApp(String driverId) throws SQLException {
        boolean tempResult = dBMySQL.isRowPresent("SELECT * FROM user_app_info u WHERE u.field = 'smart_safety' AND u.userId = " + driverId + " AND u.value = 1;");
        return tempResult;
    }
    @Step
    public List<ArrayList> getEldReturnsData(String deviceId) throws SQLException {
        List<ArrayList> tempEldReturnData = dBMySQL.selectTable("SELECT e.userId, e.`status`, e.description, e.returnReason FROM eld_returns e WHERE e.id = " + deviceId + ";");
        return tempEldReturnData;
    }
    @Step
    public void setFleetsCronRunTime(String time) throws SQLException {
        dBMySQL.changeTable("UPDATE cronCheck SET `dateTime` = '" + time + "' WHERE NAME = '/cron/check_fleets.php';");
    }
    @Step
    public void setDriversCronRunTime(String time) throws SQLException {
        dBMySQL.changeTable("UPDATE cronCheck SET `dateTime` = '" + time + "' WHERE NAME = '/cron/check_drivers.php';");
    }
}
