package frankesteinTest;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static libs.DataForTests.carrierIdString;
import static libs.Utils.*;

public class ReturnDevicesTest extends ParentTest {
    Map dataForFleet = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");

    String login = dataForFleet.get("login").toString();
    String fleetId = dataForFleet.get("fleetId").toString();
    String pass = "testtest";
    Map dataForManagerValidLogIn = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "ManagerLogin");

    public ReturnDevicesTest() throws IOException {
    }

    @Test
    public void warrantyReplacement() throws SQLException, ParseException, IOException, ClassNotFoundException {
        String localIdDevices = utilsForDB.getRandomDevicesRandomLocalId(fleetId);
        String idDevice = utilsForDB.getIdDeviceByLocalId(fleetId, localIdDevices);
        String textDescription = RandomStringUtils.randomAlphanumeric(5, 10) + " " + RandomStringUtils.randomAlphanumeric(1, 10);

        loginPage.userValidLogIn(login, pass);
        checkAC("User wasn`t logined", dashboardPage.isDashboardPresent(), true);
        dashboardPage.goToEldPage();
        eldPage.enterLocalId(localIdDevices);
        eldPage.clickOnDeviceInList(localIdDevices);
        eldPage.clickOnReplacementRequestButton();
        eldPage.clickOnWarrantyReplacementRadioButton();
        eldPage.enterTextToDescription(textDescription);
        eldPage.clickOnConfirmButton();
        checkAC("Pop up Return request was created is not displayed", eldPage.popUpRequestCreatedIsDisplayed(), true);
        List<ArrayList> eldReturnList = utilsForDB.getEldReturnsData(idDevice);
        Map<String, Object> tempDataDocMap = listArrayToMap(eldReturnList);

        checkAC("Return status is not correct", tempDataDocMap.get("status").equals("0"), true);
        checkAC("Return reason is not correct", tempDataDocMap.get("returnReason").equals("2"), true);
        checkAC("Description is not correct", tempDataDocMap.get("description").equals(textDescription), true);
        checkAC("Scanner status is not correct", utilsForDB.getScannersStatusById(idDevice).equals("11"), true);

        LocalDateTime lastDueDateTime = financesPage.getDateTimeLastDue(carrierIdString, fleetId);
        LocalDateTime returnDateTime = getLocalDateTimeFromString(tempDataDocMap.get("dateTime").toString());
        checkAC("User has charge after return", compareDiffDateTime(lastDueDateTime, returnDateTime, 1), true);

        tearDown();
        setUp();
        loginPage.userValidLogIn(dataForManagerValidLogIn.get("login").toString(),dataForManagerValidLogIn.get("pass").toString());
        dashboardPage.goToReturnsPage();
        returnsPage.enterIdDevice(idDevice);
        returnsPage.clickOnDeviceInList(idDevice);
        returnsPage.clickOnConfirmToSend();
        returnsPage.clickOnConfirmInPopUp();
        waitABit(10);
    }
}
