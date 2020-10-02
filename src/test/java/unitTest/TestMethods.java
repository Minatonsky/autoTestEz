package unitTest;

import com.github.javafaker.Faker;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Test;
import parentTest.ParentTestWithoutWebDriver;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
// This test page is not using on test project, it just for check some methods


public class TestMethods extends ParentTestWithoutWebDriver {
    org.apache.log4j.Logger logger = Logger.getLogger(getClass());
    protected Faker faker = new Faker();



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
    public void testFor() throws SQLException, IOException, ClassNotFoundException {
        int currentDueFromDB = Integer.parseInt("-10000.00");
        System.out.println(currentDueFromDB);

        String test = Integer.toString(currentDueFromDB).replaceAll("\\D+", "");
        System.out.println(test);
    }

//    @Test
//    public Map<String, String> listArrayToMap()throws Exception {
//
//        List<ArrayList> tempAmountList = utilsForDB.getUserPhoneVerificationData("4401");
//
//        List<String> placeHolderList = tempAmountList.get(0);
//        List<String> valueList = tempAmountList.get(1);
//
//        Map<String, String> phoneBook =
//                placeHolderList.stream()
//                        .collect(Collectors.toMap(
//                                valueList::get,
//                                valueList::get,
//                                (address1, address2) -> {
//                                    System.out.println("duplicate key found!");
//                                    return address1;
//                                }
//                        ));
//
//    }


    @Test
    public void test() throws SQLException {
        String name = faker.phoneNumber().subscriberNumber(10);
        System.out.println(name);
    }

}

