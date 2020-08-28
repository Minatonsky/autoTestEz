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
        return dBMySQL.selectValue("SELECT id FROM eld_orders WHERE " + fleetUserId + " = " + id + " ORDER BY orderDate desc LIMIT 1;");
    }
    @Step
    public String getOrderStatus(String idOrder) throws SQLException {
        return dBMySQL.selectValue("SELECT status FROM eld_orders WHERE id = " + idOrder + ";");
    }
    @Step
    public List<String> getIdOrderWithStatusNew(String fleetUserId, String fleetId) throws SQLException {
        return dBMySQL.selectResultSet("SELECT id FROM eld_orders WHERE " + fleetUserId + " = " + fleetId + " AND status = 0;");
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
        return dBMySQL.getRowNumber("SELECT count(*) FROM eld_orders WHERE " + fleetUserId + " = " + fleetId + " AND status = 0;");
    }

    @Step
    public void changeStatusOrderToCancel(String idOrder) throws SQLException {
        dBMySQL.changeTable("UPDATE eld_orders SET status = 2 WHERE id = " + idOrder + ";");
    }

// eld_scanners

    @Step
    public List<String> getStatusEldInOrder(String idOrder) throws SQLException {
        return dBMySQL.selectResultSet("SELECT status FROM eld_scanners WHERE id IN (SELECT scannerId FROM eld_orders_ids WHERE orderId = " + idOrder + ");");
    }
    @Step
    public String getRandomDevicesRandomLocalId(String fleetId) throws SQLException {
        return dBMySQL.selectValue("SELECT e.localId FROM eld_scanners e WHERE e.fleet = " + fleetId + " AND e.`status` = 4 ORDER BY RAND()LIMIT 1;");
    }

    @Step
    public List<String> getLocalIdDevices(String idOrder) throws SQLException {
        return dBMySQL.selectResultSet("SELECT localId FROM eld_scanners WHERE id IN (SELECT scannerId FROM eld_orders_ids WHERE orderId = " + idOrder + ");");
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
        return dBMySQL.selectResultSet("SELECT id FROM eld_scanners WHERE id IN (SELECT scannerId FROM eld_orders_ids WHERE orderId = " + idOrder + ");");
    }
    @Step
    public List<String> getIdChargeScannersByTariff(String soloOrFleetString, String userId, String tariffId, int countDevices) throws SQLException {
        return dBMySQL.selectResultSet("SELECT id FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND tariffId = " + tariffId + " AND status = 4 ORDER BY id DESC LIMIT " + countDevices + ";");
    }
    @Step
    public void setPaidTillAndTariffStartScannerForSolo(String soloId, String paidTill, String tariffStart, String tariffId) throws SQLException {
        dBMySQL.changeTable("UPDATE eld_scanners SET `paid_till`='" + paidTill + "', `tariffStart` = '" + tariffStart + "' WHERE userId = " + soloId + " AND tariffId = " + tariffId + ";");
    }
    @Step
    public int countChargeScannersByTariff(String soloOrFleetString, String userId, String tariffId) throws SQLException {
        return dBMySQL.getRowNumber("SELECT COUNT(DISTINCT es.id) FROM eld_scanners es LEFT JOIN eld_returns er ON es.id = er.scannerId WHERE es." + soloOrFleetString + " = " + userId + " AND es.status IN (4, 8, 103) AND es.tariffId = " + tariffId + " or es." + soloOrFleetString + " = " + userId + " AND es.status = 11 and er.returnReason IN (2, 1) AND er.status IN (0, 1, 3) AND es.tariffId = " + tariffId + ";");
    }
    @Step
    public int countChargeScanners(String soloOrFleetString, String userId, String firstDayNextMonth) throws SQLException {
        return dBMySQL.getRowNumber("SELECT count(*) FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status IN (4, 5, 8, 103) AND paid_till < " + firstDayNextMonth + ";");
    }
    @Step
    public int countDeactivatedChargeScannersMonthToMonth(String soloOrFleetString, String userId) throws SQLException {
        return dBMySQL.getRowNumber("SELECT count(*) FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status = 5 AND tariffId IN (0, 9);");
    }
    @Step
    public List<String> getScannersTariffStart(String scannerId) throws SQLException {
        return dBMySQL.selectResultSet("SELECT tariffStart FROM eld_scanners WHERE id IN(" + scannerId + ");");
    }
    @Step
    public List<String> getPaidTillFromEldScanners(String soloOrFleetString, String userId, String tariffId) throws SQLException {
        return dBMySQL.selectResultSet("SELECT paid_till FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status = 4 AND tariffId IN (" + tariffId + ")");
    }
    @Step
    public List<String> getParamsDeactivatedScanners(String soloOrFleetString, String userId) throws SQLException {
        return dBMySQL.selectResultSet("SELECT params FROM eld_scanners WHERE id IN (SELECT id FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status = 5 AND tariffId = 0);");
    }
    @Step
    public List<String> getIdScannersByStatus(String soloOrFleetString, String userId, String numberStatus) throws SQLException {
        return dBMySQL.selectResultSet("SELECT id FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status = " + numberStatus + ";");
    }
    @Step
    public List<String> getScannersStatus(String scannerId) throws SQLException {
        return dBMySQL.selectResultSet("SELECT status FROM eld_scanners WHERE id IN(" + scannerId + ");");
    }
    @Step
    public String getScannersStatusById(String scannerId) throws SQLException {
        return dBMySQL.selectValue("SELECT status FROM eld_scanners WHERE id = " + scannerId + ";");
    }
    @Step
    public String getIdDeviceByLocalId(String fleetId, String localId) throws SQLException {
        return dBMySQL.selectValue("SELECT e.id FROM eld_scanners e WHERE e.localId = " + localId + " AND e.fleet = " + fleetId + ";");
    }

//    eld_orders_ids

    @Step
    public boolean isEldBlinded(String idOrder) throws SQLException {
        return dBMySQL.isRowPresent("SELECT * FROM eld_orders_ids WHERE orderId = " + idOrder + ";");
    }

//   eld_returns
    @Step
    public int countChargeReturnedScannerByTariff(String soloOrFleetString, String userId, String tariffId) throws SQLException {
        return dBMySQL.getRowNumber("SELECT count(*) FROM eld_returns WHERE scannerId IN (SELECT id FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status = 11 AND tariffId = " + tariffId + ") AND returnReason IN (2, 1) AND status IN (0, 1, 3);");
    }
    @Step
    public int countChargeReturnedScanners(String soloOrFleetString, String userId, String firstDayNextMonth) throws SQLException {
        return dBMySQL.getRowNumber("SELECT count(*) FROM eld_returns WHERE scannerId IN (SELECT id FROM eld_scanners WHERE " + soloOrFleetString + " = " + userId + " AND status = 11 AND paid_till < " + firstDayNextMonth + ") AND returnReason IN (2, 1) AND status IN (0, 1, 3);");
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
        return dBMySQL.selectValue("SELECT paidTill FROM ez_finances WHERE carrierId = " + fleetId + ";");
    }
    @Step
    public String getEstimatedTillEzFinancesFleet(String fleetId) throws SQLException {
        return dBMySQL.selectValue("SELECT estimatedTill FROM ez_finances WHERE carrierId = " + fleetId + ";");
    }
    @Step
    public String getCurrentDueEzFinancesFleet(String fleetId) throws SQLException {
        return dBMySQL.selectValue("SELECT currentDue FROM ez_finances WHERE carrierId = " + fleetId + ";");
    }

//    eld_personal_finances

    @Step
    public void setCurrentDueForSolo(String valueCurrentDue, String idSolo) throws SQLException {
        dBMySQL.changeTable("UPDATE eld_personal_finances SET `currentDue`=-" + valueCurrentDue + " WHERE `userId`= " + idSolo + ";");
    }

// ez_due

    @Step
    public List<ArrayList> getLastDueDataList(String soloOrFleetString, String userId) throws SQLException {
        return dBMySQL.selectTable("SELECT e.amount, e.dateTime, e.description, e.userId, e.balance, e.`type` FROM ez_due e WHERE " + soloOrFleetString + " = " + userId + " ORDER BY e.dateTime DESC LIMIT 1;");
    }

    @Step
    public String getLastDueForFleet(String idCarrier) throws SQLException {
        return dBMySQL.selectValue("SELECT amount FROM ez_due  WHERE carrierId = " + idCarrier + " ORDER BY dateTime desc LIMIT 1;");
    }
    @Step
    public String getLastDueForSolo(String idSolo) throws SQLException{
        return dBMySQL.selectValue("SELECT amount FROM ez_due  WHERE userId = " + idSolo + " ORDER BY dateTime desc LIMIT 1;");
    }
    @Step
    public List<String> getAmountEzDue(String soloOrFleetString, String userId, String timeRunCron) throws SQLException{
        return dBMySQL.selectResultSet("SELECT amount FROM ez_due WHERE " + soloOrFleetString + " = " + userId + "  AND dateTime >= " + "'" +  timeRunCron + "'" + ";");
    }

    @Step
    public List<String> getDateTimeEzDue(String soloOrFleetString, String userId) throws SQLException{
        return dBMySQL.selectResultSet("SELECT dateTime FROM ez_due  WHERE " + soloOrFleetString + " = " + userId + " ORDER BY dateTime desc LIMIT 1;");
    }
    @Step
    public List<String> getDateTimeEzDueMonthToMonth(String soloOrFleetString, String userId) throws SQLException{
        return dBMySQL.selectResultSet("SELECT dateTime FROM ez_due  WHERE " + soloOrFleetString + " = " + userId + " ORDER BY dateTime desc LIMIT 1;");
    }
    @Step
    public List<String> getAmountEzDueMonthToMonth(String soloOrFleetString, String userId, String timeRunCron) throws SQLException{
        return dBMySQL.selectResultSet("SELECT amount FROM ez_due WHERE " + soloOrFleetString + " = " + userId + " AND dateTime >= " + "'" +  timeRunCron + "'" + ";");
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
        return dBMySQL.selectValue("SELECT paidTill FROM eld_personal_finances WHERE userId = " + soloId + ";");
    }
    @Step
    public String getEstimatedTillEzFinancesSolo(String soloId) throws SQLException{
        return dBMySQL.selectValue("SELECT estimatedTill FROM eld_personal_finances WHERE userId = " + soloId + ";");
    }
    @Step
    public String getCurrentDueEzFinancesSolo(String soloId) throws SQLException{
        return dBMySQL.selectValue("SELECT currentDue FROM eld_personal_finances WHERE userId = " + soloId + ";");
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
        return dBMySQL.isRowPresent("SELECT * FROM fleet_defaulters WHERE fleetId = " + fleetId + ";");
    }

    @Step
    public boolean checkFleetIsDeactivated(String fleetId) throws SQLException{
        return dBMySQL.isRowPresent("SELECT * FROM fleet_defaulters WHERE fleetId = " + fleetId + " AND deactivated = 1;");
    }
    @Step
    public void set_0_DeactivatedFleet(String fleetId) throws SQLException{
        dBMySQL.changeTable("UPDATE fleet_defaulters SET `deactivated` = 0 WHERE fleetId = " + fleetId + ";");
    }


//    carriers

    @Step
    public boolean checkFleetIsBanned(String fleetId) throws SQLException{
        return dBMySQL.isRowPresent("SELECT * FROM carriers WHERE id = " + fleetId + " AND banned = 1;");
    }
    @Step
    public void setUnbanFleet(String fleetId) throws SQLException{
        dBMySQL.changeTable("UPDATE carriers SET `banned` = 0 WHERE id = " + fleetId + ";");
    }

//    users

    @Step
    public boolean checkSoloIsBanned(String soloId) throws SQLException{
        return dBMySQL.isRowPresent("SELECT * FROM users WHERE id = " + soloId + " AND banned = 1;");
    }
    @Step
    public void setUnbanSolo(String soloId) throws SQLException{
        dBMySQL.changeTable("UPDATE users SET `banned` = 0 WHERE id = " + soloId + ";");
    }
    @Step
    public String getUserIdByEmail(String email) throws SQLException{
        return dBMySQL.selectValue("SELECT id FROM users WHERE email = '" + email + "';");
    }
    @Step
    public String getFleetIdByEmail(String email) throws SQLException{
        return dBMySQL.selectValue("SELECT carrierId FROM users WHERE email = '" + email + "';");
    }

    @Step
    public String getRandomDriverEmail(String fleetId) throws SQLException {
        return dBMySQL.selectValue("SELECT u.email FROM users u WHERE u.carrierId = " + fleetId + " AND companyPosition = 7 ORDER BY RAND()LIMIT 1;");
    }
    @Step
    public String getUserEmailById(String id) throws SQLException{
        return dBMySQL.selectValue("SELECT email FROM users WHERE id = '" + id + "';");
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
        return dBMySQL.isRowPresent("SELECT * FROM driver_defaulters WHERE userId = " + soloId + ";");
    }
    @Step
    public boolean checkSoloIsDeactivated(String soloId) throws SQLException{
        return dBMySQL.isRowPresent("SELECT * FROM driver_defaulters WHERE userId = " + soloId + " AND deactivated = 1;");
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
        return dBMySQL.isRowPresent("SELECT * FROM driversRules WHERE userId = " + userId + " AND aobrdMPH > 0;");
    }

    @Step
    public List<ArrayList> getDataDriverSettings(String userId) throws SQLException{
        return dBMySQL.selectTable("SELECT HazMat, Insurance, TankerEndorsment, yard, conv, hideEngineStatuses, Sms, City, Address, notes, Phone, SSN, EIN, MedCard, DateOfBirth, HireDate, TermitaneDate, PullNotice, DLExpiration, DLNumber, DLState, scanner_type  FROM driversData WHERE userId = " + userId + " AND companyId = 0;");
    }

//    EQUIPMENT


    @Step
    public List<ArrayList> getDataEquipment(String idEquipment) throws SQLException{
        return dBMySQL.selectTable("SELECT id, truckTrailer, Notes, Name, Owner, Year, Type, TireSize, Fuel, Axel, Color, Make, Model, VIN, GrossWeight, UnlandWeight, Plate, State, NYCert, InspectionDue, ProRateExp, isActive, Length, 90DayExp, ExpDate FROM equipment e WHERE e.id = '" + idEquipment + "' ORDER BY e.id DESC LIMIT 1;");
    }
    @Step
    public String getEquipmentId(String carrierUser, String soloId, String equipName) throws SQLException{
        return dBMySQL.selectValue("SELECT e.id FROM equipment e WHERE " + carrierUser + " = " + soloId + " AND e.Name = '" + equipName + "' ORDER BY e.id DESC LIMIT 1;");
    }

    @Step
    public String getRandomEquipmentId(String carrierUserId, String soloId, String truckTrailer) throws SQLException{
        return dBMySQL.selectValue("SELECT e.id FROM equipment e WHERE " + carrierUserId + " = " + soloId + " AND e.truckTrailer = " + truckTrailer + "  ORDER BY RAND()LIMIT 1;");
    }

    @Step
    public String getEquipmentName(String equipmentId) throws SQLException{
        return dBMySQL.selectValue("SELECT e.Name FROM equipment e WHERE e.id = " + equipmentId + " ;");
    }

    @Step
    public String getRandomUserIdCarrier(String carrierId) throws SQLException{
        return dBMySQL.selectValue("SELECT id FROM users u WHERE u.carrierId = " + carrierId + " ORDER BY RAND()LIMIT 1;");
    }
    @Step
    public String getRandomDriverIdInFleet(String carrierId) throws SQLException{
        return dBMySQL.selectValue("SELECT id FROM users u WHERE u.carrierId = " + carrierId + " AND u.companyPosition = 7 ORDER BY RAND()LIMIT 1;");
    }
    @Step
    public String getDriverNameById(String userId) throws SQLException{
        return dBMySQL.selectValue("SELECT CONCAT(u.name, \" \", u.`last`) FROM users u WHERE u.id = " + userId + ";");
    }

    @Step
    public String getDocInfoData(String docId, String infoName) throws SQLException{
        return dBMySQL.selectValue("SELECT infoData from docsInfo di WHERE di.docId = " + docId + " AND infoName = '" + infoName + "';");
    }

    public List<ArrayList> getDocData(String carrierId, String reference) throws SQLException{
        return dBMySQL.selectTable("SELECT d.id, d.awsName, d.`type`, d.date, d.carrierId, d.initiatorId, d.truckId, d.note, d.userId FROM documents d WHERE d.carrierId = " + carrierId + " and d.reference = '" + reference + "';");
    }

    public String getRandomDocumentReference(String carrierId, String typeDoc) throws SQLException{
        return dBMySQL.selectValue("SELECT reference FROM documents d WHERE d.`type` = " + typeDoc + " AND d.carrierId = " + carrierId + " ORDER BY RAND()LIMIT 1;");
    }

    public String getUrlPermitDoc(String equipmentId, String shortName) throws SQLException{
        return dBMySQL.selectValue("SELECT url FROM permitDocs pd WHERE pd.equipmentId = " + equipmentId + " AND pd.shortName = '" + shortName + "';");
    }


    public List<ArrayList> getUsersAndDriversRulesData(String userEmail) throws SQLException{
        return dBMySQL.selectTable("SELECT dr.carrierName, dr.carrierAddress, dr.carrierCity, dr.carrierState, dr.carrierZip, dr.homeTerminal, dr.coDrivers, dr.cycleId, dr.timeZoneId, dr.usdot, dr.odometerId,dr.restBreakId, \n" +
                "dr.logIncrementId, dr.cargoTypeId, dr.wellSiteId, dr.restartId, dr.iftaDistances, dr.old_allow_edit, dr.aobrdMPH, dr.teamDriver, dr.teamDriverPassword, u.id, u.name, u.`last`, u.phone, \n" +
                "u.password, u.googleId, u.fbId, u.carrierId, u.companyPosition, u.banned FROM driversRules dr LEFT JOIN users u ON u.id = dr.userId WHERE u.email = '" + userEmail + "';");
    }

    public List<ArrayList> getCarrierRulesData(String carrierId) throws SQLException {
        return dBMySQL.selectTable("SELECT c.usdot, c.name, c.state, c.city, c.address, c.zip, c.size, c.registrationDate, c.aobrdMPH, c.ownerId, c.ein, c.banned, f.cycleId, f.timeZoneId, f.agricultureDeliveries FROM carriers c\n" +
                "LEFT JOIN fleetRules f ON f.carrierId = c.id\n" +
                " WHERE c.id = " + carrierId + ";");
    }

    public List<ArrayList> getReminderData(String id, String name) throws SQLException{
        return dBMySQL.selectTable("SELECT e.`type`, e.`status`, case when e.remindDateTime = 0 then '0' ELSE e.remindDateTime end as remindDateTime, e.term, e.odometerStart, e.currentOdometer, e.whatSend FROM equipment_reminder e WHERE e.equipId = " + id + " AND e.name = '" + name + "';");
    }

    public void deleteStatuses(String userId) throws SQLException{
        dBMySQL.changeTable("DELETE FROM status WHERE userId = " + userId + " AND DATETIME != '2019-11-14 00:00:00';");
    }

    public void updateLastStatus(String userId) throws SQLException{
        dBMySQL.changeTable("UPDATE driver_last_status d SET d.dateTime = '2019-11-14 00:00:00', d.dateTimeUtc = '2019-11-14 05:00:00', d.drive = 39600, d.shift = 50400, d.cycle = 216000, d.eight = 28800, d.shiftWork = 9999999, d.restart34 = 0 WHERE d.userId = " + userId + ";");
    }

    public boolean isCurrentCardSame(String userId, String cardNumber) throws SQLException{
        return dBMySQL.isRowPresent("SELECT * FROM authorize_clients a WHERE a.userId = " + userId + " AND a.currentCard = 1 AND a.creditCard = '" + cardNumber + "';");
    }

//    SERVICES


    public String getSmartSafetyUserId(String fleetId) throws SQLException{
        return dBMySQL.selectValue("SELECT u.id FROM user_app_info uai LEFT JOIN users u ON uai.userId = u.id WHERE u.carrierId = " + fleetId + " AND uai.field = 'smart_safety' ORDER BY RAND() LIMIT 1;");
    }

    public void updateServicesConnections(String fleetId, String userId, String dateTimeAt, String dateTimeTill) throws SQLException{
        dBMySQL.changeTable("UPDATE services_connections SET created_at = '" + dateTimeAt + "', subscribed_till = '" + dateTimeTill + "' WHERE user_id = " + userId + " AND carrier_id = " + fleetId + ";");
    }

    public boolean checkForTransactions(String fleetId, String dateTime) throws SQLException{
        return dBMySQL.isRowPresent("SELECT * FROM services_transactions s WHERE s.carrier_id = " + fleetId + " AND s.created_at > '" + dateTime + "';");
    }

    public String getSubscribedTillDateTime(String fleetId, String userId) throws SQLException {
        return dBMySQL.selectValue("SELECT s.subscribed_till FROM services_connections s WHERE s.carrier_id = " + fleetId + " AND s.user_id = " + userId + " AND s.service_id = 1;");
    }

    public List<ArrayList> getAtTillDateTimeServices(String fleetId, String userId) throws SQLException {
        return dBMySQL.selectTable("SELECT s.created_at, s.updated_at, s.subscribed_till,  s.noticed_at FROM services_connections s WHERE s.carrier_id = " + fleetId + " AND s.user_id = " + userId + " AND s.service_id = 1;");
    }

    public void deleteSmartSafetyFoDriver(String driverId) throws SQLException {
        dBMySQL.changeTable("DELETE FROM user_app_info WHERE userId = " + driverId + ";");
        dBMySQL.changeTable("DELETE FROM services_history WHERE user_id = " + driverId + ";");
        dBMySQL.changeTable("DELETE FROM services_connections WHERE user_id = " + driverId + ";");
    }

    public boolean isSmartSafetyInUserApp(String driverId) throws SQLException {
        return dBMySQL.isRowPresent("SELECT * FROM user_app_info u WHERE u.field = 'smart_safety' AND u.userId = " + driverId + " AND u.value = 1;");
    }

    public List<ArrayList> getEldReturnsData(String deviceId) throws SQLException {
        return dBMySQL.selectTable("SELECT e.userId, e.`status`, e.description, e.returnReason FROM eld_returns e WHERE e.id = " + deviceId + ";");
    }

    public void setFleetsCronRunTime(String time) throws SQLException {
        dBMySQL.changeTable("UPDATE cronCheck SET `dateTime` = '" + time + "' WHERE NAME = '/cron/check_fleets.php';");
    }

    public void setDriversCronRunTime(String time) throws SQLException {
        dBMySQL.changeTable("UPDATE cronCheck SET `dateTime` = '" + time + "' WHERE NAME = '/cron/check_drivers.php';");
    }

//    LOGBOOK

    public void deleteViolation(String driverId) throws SQLException {
        dBMySQL.changeTable("DELETE FROM alerts WHERE userId = " + driverId + ";");

    }
    public List<String> getAlertsData(String driverId, String date) throws SQLException {
        return dBMySQL.selectResultSet("SELECT a.alertId FROM alerts a WHERE a.userId = " + driverId + " AND a.dateTime LIKE '" + date + "%'");
    }
    public void setCycleDriversRules(String driverId, int cycleId) throws SQLException {
        dBMySQL.changeTable("UPDATE driversRules SET `cycleId` = '" + cycleId + "' WHERE userId = " + driverId + ";");
    }
    public void setCycleStatuses(String driverId, int cycleId) throws SQLException {
        dBMySQL.changeTable("UPDATE cycleStatuses SET `statusTypeId` = '" + cycleId + "' WHERE userId = " + driverId + ";");
    }
    public List<ArrayList> getCycleHoursLastStatus(String driverId, String date) throws SQLException {
        return dBMySQL.selectTable("SELECT s.drive, s.shift, s.cycle, s.eight, s.shiftWork, s.restart34 FROM `status` s WHERE s.userId = " + driverId + " AND s.dateTime LIKE '" + date + "%' GROUP BY s.dateTime HAVING MAX(s.dateTime) order BY s.dateTime DESC LIMIT 1;");
    }
    public void setCargoTypeId(String driverId, int cargoTypeId) throws SQLException {
        dBMySQL.changeTable("UPDATE driversRules SET `cargoTypeId` = '" + cargoTypeId + "' WHERE userId = " + driverId + ";");
        dBMySQL.changeTable("update usersParamsHistory SET params = JSON_REPLACE(params, '$.cargoTypeId', " + cargoTypeId + ") WHERE userId = " + driverId + " AND JSON_EXTRACT(params, '$.cargoTypeId');");
    }
    public void deleteStatusEvent(String driverId, String dateMonth) throws SQLException {
        dBMySQL.changeTable("DELETE FROM `status_events_" + dateMonth + "` WHERE userId = " + driverId + ";");
    }
    public String getLastStatusOnDay(String driverId, String date) throws SQLException {
        return dBMySQL.selectValue("SELECT MIN(s.id) FROM status s WHERE s.userId = " + driverId + " AND s.dateTime LIKE '" + date + "%';");
    }
}
