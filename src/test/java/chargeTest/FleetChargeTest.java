package chargeTest;

import org.junit.Test;
import parentTest.ParentChargeTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class FleetChargeTest extends ParentChargeTest {

    Map dataForFleet = excelDriver.getData(configProperties.DATA_FILE_PATH() + "testLogin.xls", "validFleetLogin");

    public FleetChargeTest() throws IOException {
    }

    @Test
    public  void chargeTest() throws SQLException, IOException, ClassNotFoundException {
        String monthToMonthTariffId = "0";
        String oneYearTariffId = "1";
        String twoYearsTariffId = "2";
        int countScannerMonthToMonthTariff = utilsForDB.countActiveScannersWithTariffForFleet(dataForFleet.get("fleetId").toString(), monthToMonthTariffId);
        int countScannerOneYearTariff = utilsForDB.countActiveScannersWithTariffForFleet(dataForFleet.get("fleetId").toString(), oneYearTariffId);
        int countScannerTwoYearsTariff = utilsForDB.countActiveScannersWithTariffForFleet(dataForFleet.get("fleetId").toString(), twoYearsTariffId);
        System.out.println(countScannerMonthToMonthTariff);
        System.out.println(countScannerOneYearTariff);
        System.out.println(countScannerTwoYearsTariff);

    }
}
