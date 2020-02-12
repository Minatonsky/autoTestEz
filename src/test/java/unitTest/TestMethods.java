package unitTest;

import com.mifmif.common.regex.Generex;
import libs.UtilsForDB;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.logging.Logger;
// This test page is not using on test project, it just for check some methods

public class TestMethods {
    // testtesttesttest
    static Logger logger = Logger.getLogger(String.valueOf(TestMethods.class));
    UtilsForDB utilsForDB = new UtilsForDB();


    @Test
    public void testDBSetCurrentDueForFleet() throws SQLException, IOException, ClassNotFoundException {
        LocalDateTime yesterday = LocalDateTime.parse(LocalDateTime.now().minusDays(1).toString());
        String startYesterday = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(startYesterday);
    }

    @Test
    public void testCancelEldDevices() throws SQLException, IOException, ClassNotFoundException {
        UtilsForDB utilsForDB = new UtilsForDB();
        String idLastOrderAfterTest = "2460";
        List<String> localId = utilsForDB.getLocalIdDevices(idLastOrderAfterTest);
        logger.info(" result = " + localId.get(1));
        for (String element :
                localId
        ) {
            System.out.println(element);

        }
        for (int i = 0; i < localId.size(); i++) {
            System.out.println(localId.get(i));

        }
    }


    @Test
    public void testIsEldBlinded() throws SQLException, IOException, ClassNotFoundException {
        UtilsForDB utilsForDB = new UtilsForDB();
        String orderId = "3066";
        System.out.println(utilsForDB.isEldBlinded(orderId));
    }

    @Test
    public void testGetEldId() throws SQLException, IOException, ClassNotFoundException {
        String idOrder = "3064";
        List<String> tempIdEld = utilsForDB.getIdEldFromOrder(idOrder);
        for (String element : tempIdEld
        ) {
            System.out.println(element);
        }
    }

    @Test
    public void testActionNewOrder() throws SQLException, IOException, ClassNotFoundException {
        String idOrder = "3075";
        utilsForDB.deleteEventNewOrder(idOrder);
    }

    @Test
    public void testGetParams() throws SQLException, IOException, ClassNotFoundException, ParseException {
        String soloOrFleetString = "fleet";
        String userId = "518";
        List<String> tempIdEld = utilsForDB.getParamsDeactivatedScanners(soloOrFleetString, userId);

        LocalDate firstDayOfNextMonth = LocalDate.parse(LocalDate.now().toString()).with(TemporalAdjusters.firstDayOfNextMonth());
        LocalDate today = LocalDate.parse(LocalDate.now().toString());

        for (String element : tempIdEld) {
            JSONObject obj2 = new JSONObject(element);

            String deactivateDate = obj2.getString("deactivateDate");
            int deactivateDays = obj2.getInt("deactivateDays");
            int monthDays = (int) ChronoUnit.DAYS.between(today, firstDayOfNextMonth);
            LocalDate deactivatedTill = LocalDate.parse(deactivateDate).plus(Period.ofDays(deactivateDays));
            int activeChargeDays = (int) ChronoUnit.DAYS.between(deactivatedTill, firstDayOfNextMonth);

            if (activeChargeDays > 0) {
                int deactivatedDays = monthDays - activeChargeDays;
                double deactivatedCharge = 29.99 / 2 / monthDays * deactivatedDays;
                double activeCharge = 29.99 / monthDays * activeChargeDays;
                double charge_amount = Math.round((activeCharge + deactivatedCharge) * 100.0) / 100.0;

            } else {
                double charge_amount = Math.round((29.99 / 2) * 100.0) / 100.0;

            }

        }

    }

    @Test
    public void compareCurrentDueFleetDefaulter() throws SQLException, IOException, ClassNotFoundException {
        double sumCharge = 29.99;
        String currentDueFleet = utilsForDB.getCurrentDueEzFinancesFleet("582");
        boolean tempCompareDueFleet = -sumCharge == Double.parseDouble(currentDueFleet);

        System.out.println(currentDueFleet);
        System.out.println(tempCompareDueFleet);


    }

    @Test
    public void checkDevicesIsNotPaid() throws SQLException, IOException, ClassNotFoundException {
        String fleetString = "fleet";
        String fleetId = "518";
        List<String> listOfActiveDevices = utilsForDB.getIdScannersByStatus(fleetString, fleetId, "11");
        String stringOfActiveDevices = String.join(",", listOfActiveDevices);
        List<String> listOfStatuses = utilsForDB.getScannersStatus(stringOfActiveDevices);
        System.out.println("listOfStatuses count = " + listOfStatuses.size());
        System.out.println("listOfStatuses " + listOfStatuses);
        for (String element : listOfStatuses) {
            if (element.equals("1")) {
                System.out.println("Ok ");
            } else System.out.println("No Ok ");
        }
        System.out.println("#");

    }

    @Test
    public void compareEldStatusInCompletedOrder() throws SQLException, IOException, ClassNotFoundException {
        LocalDateTime oneYear = LocalDateTime.parse(LocalDateTime.now().minusMonths(12).toString()).with(TemporalAdjusters.firstDayOfNextMonth());
        long firstDayOfNextMonth = oneYear.toEpochSecond(ZoneOffset.UTC);
        String tempOneYear = Long.toString(firstDayOfNextMonth);
        System.out.println("firstDayOfNextMonth = " + tempOneYear);
    }

    @Test
    public void checkProratedAndNotReturnedFee() throws SQLException, IOException, ClassNotFoundException {
        String fleetId = "581";
        String fleetString = "fleet";
        String currentDueWithLateFee = "10";

        List<String> listOfActiveDevices = utilsForDB.getIdScannersByStatus(fleetString, fleetId, "4");

        LocalDateTime tempDate = LocalDateTime.parse(LocalDateTime.now().toString());
        long date = tempDate.toEpochSecond(ZoneOffset.UTC);
        String tempDateToday = Long.toString(date);

        logger.info("tempDateToday = " + tempDateToday);
        String stringOfActiveDevices = String.join(",", listOfActiveDevices);
        logger.info("listOfActiveDevices = " + listOfActiveDevices);
        List<String> listDevicesTariffStart = utilsForDB.getScannersTariffStart(stringOfActiveDevices);

        int count = 0;
        for (String element :
                listDevicesTariffStart) {
            if (Integer.parseInt(element) < Integer.parseInt(tempDateToday)) {
                int tempCount = count++;
                System.out.println("tempCount" + tempCount);

            } else System.out.println("0");
        }


    }

    @Test
    public void testFor() throws SQLException, IOException, ClassNotFoundException {
        int monthToMonthTariff = 3;
        String geometricsMonthlyTariffId = "9";
        int tempTariffId = monthToMonthTariff % 2;
        int tempTariffId2 = monthToMonthTariff - tempTariffId;
        System.out.println(tempTariffId + " - " + tempTariffId2);
    }

    @Test
    public void getAmountEzDueMonthToMonth() throws SQLException, IOException, ClassNotFoundException {
        String soloOrFleetString = "531";
        String carrierId = "carrierId";
        String timeRunCron = "2019-12-03 10:23:19";

        List<String> tempAmountList = utilsForDB.getAmountEzDueMonthToMonth(soloOrFleetString, carrierId, timeRunCron);
        System.out.println(tempAmountList);

    }

    @Test
    public void test2() throws SQLException, IOException, ClassNotFoundException {
        Generex generex = new Generex("[A-Z]{1}[A-Z0-9]{11}|[A-Z]{7}[A-Z0-9]{5}");
//  "1-7 Alpha + any combination of Alpha, Numeric, and * for a total of 12 characters"

// generate the second String in lexicographical order that matches the given Regex.
//        String secondString = generex.getMatchedString(2);
//        System.out.println(secondString);// it print '0b'

// Generate all String that matches the given Regex.
//        List<String> matchedStrs = generex.getAllMatchedStrings();
//        System.out.println(matchedStrs);
// Using Generex iterator
//        Iterator iterator = generex.iterator();
//        while (iterator.hasNext()) {
//            System.out.print(iterator.next() + " ");
//        }

        String randomStr = generex.random();
        System.out.println(randomStr);

    }

}
