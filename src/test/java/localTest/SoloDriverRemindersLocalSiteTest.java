package localTest;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static libs.Utils.*;

public class SoloDriverRemindersLocalSiteTest extends ParentTest {

    Map dataForValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "driverLogin");

    String login = dataForValidLogIn.get("login").toString();
    String pass = dataForValidLogIn.get("pass").toString();


    public SoloDriverRemindersLocalSiteTest() throws IOException {
    }

    @Test
    public void addReminderByDateForTruck() throws SQLException, IOException, ClassNotFoundException {
        String userId = utilsForDB.getUserIdByEmail(login);
        String equipId = utilsForDB.getRandomEquipmentId(userId, "0");

        List<ArrayList> tempDataEquipmentList = utilsForDB.getDataEquipment(userId, equipId);
        Map<String, Object> tempDataEquipmentMap = listArrayToMap(tempDataEquipmentList);

        String nameTruck = tempDataEquipmentMap.get("Name").toString();
        String nameReminder = RandomStringUtils.randomAlphanumeric(1, 10);
        String dateReminder = getDateFormat();

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToEquipmentPage();
        equipmentLocalSitePage.enterOnEquipmentPlaceHolder(nameTruck);
        waitABit(3);
        equipmentLocalSitePage.clickOnEquipmentOnRow();
        waitABit(3);
        String idTruck = equipmentLocalSitePage.getVehicleIdText();
        equipmentLocalSitePage.clickOnRemindersButton();
        waitABit(3);
        equipmentLocalSitePage.clickOnRemindByDateButton();
        equipmentLocalSitePage.enterNameReminder(nameReminder);
        equipmentLocalSitePage.enterDateReminder(dateReminder);
        equipmentLocalSitePage.moveSlider(32);
        waitABit(5);
        equipmentLocalSitePage.clickSaveButton();
        waitABit(5);

        List<ArrayList> tempDataReminderList = utilsForDB.getReminderData(idTruck, nameReminder);
        Map<String, Object> tempDataReminderMap = listArrayToMap(tempDataReminderList);

        checkAC("Date failed", tempDataReminderMap.get("remindDateTime").equals(dateReminder + " 00:00:00"), true);
        checkAC("First reminder failed", Integer.parseInt(tempDataReminderMap.get("term").toString()) > 0, true);
    }
    @Test
    public void addReminderByDateForTrailer() throws SQLException, IOException, ClassNotFoundException {
        String userId = utilsForDB.getUserIdByEmail(login);
        String equipId = utilsForDB.getRandomEquipmentId(userId, "0");
        List<ArrayList> tempDataSettingsList = utilsForDB.getDataEquipment(userId, equipId);
        Map<String, Object> tempDataSettingsMap = listArrayToMap(tempDataSettingsList);
        String nameTrailer = tempDataSettingsMap.get("Name").toString();
        String nameReminder = RandomStringUtils.randomAlphanumeric(1, 10) + " " + RandomStringUtils.randomAlphanumeric(1, 10) + ".";
        String dateReminder = getDateFormat();

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToEquipmentPage();
        equipmentLocalSitePage.enterOnEquipmentPlaceHolder(nameTrailer);
        waitABit(3);
        equipmentLocalSitePage.clickOnEquipmentOnRow();
        waitABit(3);
        String idTruck = equipmentLocalSitePage.getVehicleIdText();
        equipmentLocalSitePage.clickOnRemindersButton();
        waitABit(3);
        equipmentLocalSitePage.clickOnRemindByDateButton();
        equipmentLocalSitePage.enterNameReminder(nameReminder);
        equipmentLocalSitePage.enterDateReminder(dateReminder);
        equipmentLocalSitePage.moveSlider(32);
        waitABit(5);
        equipmentLocalSitePage.clickSaveButton();
        waitABit(5);

        List<ArrayList> tempDataReminderList = utilsForDB.getReminderData(idTruck, nameReminder);
        Map<String, Object> tempDataReminderMap = listArrayToMap(tempDataReminderList);

        checkAC("Date failed", tempDataReminderMap.get("remindDateTime").equals(dateReminder + " 00:00:00"), true);
        checkAC("First reminder failed", Integer.parseInt(tempDataReminderMap.get("term").toString()) > 0, true);
    }

    @Test
    public void addReminderByMileForTruck() throws SQLException, IOException, ClassNotFoundException {
        String userId = utilsForDB.getUserIdByEmail(login);
        String equipId = utilsForDB.getRandomEquipmentId(userId, "0");
        List<ArrayList> tempDataSettingsList = utilsForDB.getDataEquipment(userId, equipId);
        Map<String, Object> tempDataSettingsMap = listArrayToMap(tempDataSettingsList);
        String nameTruck = tempDataSettingsMap.get("Name").toString();
        String nameReminder = RandomStringUtils.randomAlphanumeric(1, 10) + " " + RandomStringUtils.randomAlphanumeric(1, 10) + ".";
        String countMiles = RandomStringUtils.randomNumeric(4);

        loginLocalSitePage.userValidLogIn(login, pass);
        dashboardLocalSitePage.goToEquipmentPage();
        equipmentLocalSitePage.enterOnEquipmentPlaceHolder(nameTruck);
        waitABit(3);
        equipmentLocalSitePage.clickOnEquipmentOnRow();
        waitABit(3);
        String idTruck = equipmentLocalSitePage.getVehicleIdText();
        equipmentLocalSitePage.clickOnRemindersButton();
        waitABit(3);
        equipmentLocalSitePage.clickOnRemindByMileButton();
        equipmentLocalSitePage.enterNameReminder(nameReminder);
        equipmentLocalSitePage.enterMiles(countMiles);
        equipmentLocalSitePage.clickSaveButton();
        waitABit(5);

        List<ArrayList> tempDataReminderList = utilsForDB.getReminderData(idTruck, nameReminder);
        System.out.println(tempDataReminderList);
        Map<String, Object> tempDataReminderMap = listArrayToMap(tempDataReminderList);

        checkAC("Date failed", tempDataReminderMap.get("remindDateTime").equals("0"), true);
        checkAC("First reminder failed", tempDataReminderMap.get("term").equals(countMiles), true);


    }
}
