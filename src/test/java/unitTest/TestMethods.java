package unitTest;

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
import java.util.Arrays;
import java.util.List;

import static libs.Utils.getDurationBetweenTime;
// This test page is not using on test project, it just for check some methods


public class TestMethods extends ParentTestWithoutWebDriver {
    org.apache.log4j.Logger logger = Logger.getLogger(getClass());



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

    //    cycle type
//    public static int USA_70hr_8days = 0; //numbers['shiftHours'] = 14; numbers['driveHours'] = 11;
//    public static int USA_60hr_7days = 1; //numbers['shiftHours'] = 14; numbers['driveHours'] = 11;
//    public static int Alaska_70hr_7days = 2; //numbers['shiftHours'] = 20; numbers['driveHours'] = 15;
//    public static int Alaska_80hr_8days = 3; //numbers['shiftHours'] = 20; numbers['driveHours'] = 15;
//    public static int Canada_70hr_7days = 4; //numbers['shiftHours'] = 16; numbers['driveHours'] = 13;
//    public static int Canada_120hr_14days = 5; //numbers['shiftHours'] = 16; numbers['driveHours'] = 13;
//    public static int Texas70hr_7days = 6; //numbers['shiftHours'] = 15; numbers['driveHours'] = 12;
//    public static int California_80hr_7days = 7; //numbers['shiftHours'] = 16; numbers['driveHours'] = 14;
//    public static int CanadaNorth_60_80_7 = 8; //numbers['shiftHours'] = ; numbers['driveHours'] = ;
//    public static int Other = 9;
//
//    //    CARGO TYPE
//    public static int Property = 0;
//    public static int Agriculture = 1;
//    public static int Passenger = 2;
//    public static int OilGas = 3;
//    public static int ShortHaul = 4;

    public int recurseKeys() throws Exception {
        List<String> list = Arrays.asList("06:00:00 AM/10:00:00 AM", "01:00:00 PM/05:00:00 PM");
        int temp = 0;
        for (String i :
                list) {
            String[] parts = i.split("/");
            temp += (int) getDurationBetweenTime(parts[0], parts[1]);

        }
        return temp;

    }


    @Test
    public void test() {
        String time = "00:00:00";
        if (time.equals("00:00:00")){
            System.out.println("ura");
        } else {
            System.out.println("blat");
        }
    }

}

