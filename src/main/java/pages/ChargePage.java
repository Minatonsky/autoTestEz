package pages;

import io.qameta.allure.Step;
import libs.ConfigProperties;
import libs.UtilsForDB;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class ChargePage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    UtilsForDB utilsForDB = new UtilsForDB();
    String checkFleets = "https://dev.ezlogz.com/cron/check_fleets.php";
    String checkDrivers = "https://dev.ezlogz.com/cron/check_drivers.php";
    protected static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
    public ChargePage(WebDriver webDriver) {
        this.webDriver = webDriver;

    }

    @Step
    public String paidTillForAllTariff(){
        LocalDate yesterday = LocalDate.parse(LocalDate.now().minusDays(1).toString());
        long startYesterday = yesterday.atStartOfDay().toEpochSecond(ZoneOffset.UTC);
        String tempPaidTill = Long.toString(startYesterday);
        return tempPaidTill;
    }

    @Step
    public String tariffStartForMonthToMonth(){
        LocalDateTime tariffStart = LocalDateTime.parse(LocalDateTime.now().minusMonths(1).minusDays(1).toString());
        long previousMonthMinusDay = tariffStart.toEpochSecond(ZoneOffset.UTC);
        String tempTariffMonthStart = Long.toString(previousMonthMinusDay);
        return tempTariffMonthStart;
    }

    @Step
    public String tariffStartForOneYear(){
        LocalDateTime tariffStart = LocalDateTime.parse(LocalDateTime.now().minusYears(1).minusDays(1).toString());
        long previousYearMinusDay = tariffStart.toEpochSecond(ZoneOffset.UTC);
        String tempTariffYearStart = Long.toString(previousYearMinusDay);
        return tempTariffYearStart;
    }

    @Step
    public String tariffStartForTwoYears(){
        LocalDateTime tariffStart = LocalDateTime.parse(LocalDateTime.now().minusYears(2).minusDays(1).toString());
        long previousTwoYearMinusDay = tariffStart.toEpochSecond(ZoneOffset.UTC);
        String tempTariffTwoYearsStart = Long.toString(previousTwoYearMinusDay);
        return tempTariffTwoYearsStart;
    }

    @Step
    public String paidTillForEzFinances(){
        LocalDate yesterday = LocalDate.parse(LocalDate.now().minusDays(1).toString());
        String startYesterday = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return startYesterday;
    }

    @Step
    public String runCronCheckFleet(){
        LocalDateTime startCronTime = LocalDateTime.parse(LocalDateTime.now(ZoneId.from(ZoneOffset.UTC)).toString());
        String startCronTimeLong = startCronTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        webDriver.get(checkFleets);
        logger.info("Cron check fleets was run: " + startCronTimeLong);
        return startCronTimeLong;
    }
    @Step
    public String runCronCheckDrivers(){
        LocalDateTime startCronTime = LocalDateTime.parse(LocalDateTime.now(ZoneId.from(ZoneOffset.UTC)).toString());
        String startCronTimeLong = startCronTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        logger.info("Cron check Drivers was run: " + startCronTimeLong);
        webDriver.get(checkDrivers);
        return startCronTimeLong;
    }
    @Step
    public boolean checkDateTimeDue(String soloOrFleetString, String userId, String timeRunCron) throws SQLException, IOException, ClassNotFoundException {
        List<String> dateTimeList = utilsForDB.getDateTimeEzDue(soloOrFleetString, userId);
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        logger.info("dateTime: " + dateTimeList);
        for (String element : dateTimeList) {
            if (LocalDateTime.parse(element, dTF).isAfter(LocalDateTime.parse(timeRunCron, dTF))) {
            } else return false;
        } return true;
    }

    @Step
    public boolean checkIfTariffPresent(int monthToMonthTariff, int oneYearTariff, int twoYearsTariff){
        if (monthToMonthTariff > 0 & oneYearTariff > 0 & twoYearsTariff > 0) {
            return true;
        } else return false;
    }
    @Step
    public double sumDeactivatedScannerMonthToMonthTariff(String soloOrFleetString, String userId, int countDeactivatedScannerMonthToMonthTariff) throws SQLException, IOException, ClassNotFoundException {
        if (countDeactivatedScannerMonthToMonthTariff > 0){
            List<String> tempIdEld = utilsForDB.getParamsDeactivatedScanners(soloOrFleetString, userId);

            LocalDate firstDayOfNextMonth = LocalDate.parse(LocalDate.now().toString()).with(TemporalAdjusters.firstDayOfNextMonth());
            LocalDate today = LocalDate.parse(LocalDate.now().toString());
            double sum = 0;

            for (String element : tempIdEld) {
                JSONObject obj2 = new JSONObject(element);

                String deactivateDate = obj2.getString("deactivateDate");
                int deactivateDays = obj2.getInt("deactivateDays");
                int monthDays = (int) ChronoUnit.DAYS.between(today, firstDayOfNextMonth);
                LocalDate deactivatedTill = LocalDate.parse(deactivateDate).plus(Period.ofDays(deactivateDays));
                int activeChargeDays = (int) ChronoUnit.DAYS.between(deactivatedTill, firstDayOfNextMonth);

                if (activeChargeDays > 0){
                    int deactivatedDays = monthDays - activeChargeDays;
                    double deactivatedCharge = 29.99 / 2 / monthDays * deactivatedDays;
                    double activeCharge = 29.99 / monthDays * activeChargeDays;
                    double charge_amount = Math.round((activeCharge + deactivatedCharge) * 100.0) / 100.0;
                    sum += charge_amount;

                } else {
                    double charge_amount = Math.round((29.99 / 2) * 100.0) / 100.0;
                    sum += charge_amount;
                }
            } return sum;

        } else return 0;

    }

    @Step
    public boolean compareDueCharge(String soloOrFleetString, String userId, int countScannerMonthToMonthTariff, int countScannerOneYearTariff, int countScannerTwoYearsTariff, double sumDeactivatedScannerMonthToMonthTariff) throws SQLException, IOException, ClassNotFoundException {
        List<String> amountDue = utilsForDB.getAmountEzDue(soloOrFleetString, userId);

        logger.info("amountDue from db: " + amountDue);
        logger.info("countScannerMonthToMonthTariff: " + countScannerMonthToMonthTariff);
        logger.info("countScannerOneYearTariff: " + countScannerOneYearTariff);
        logger.info("countScannerTwoYearsTariff: " + countScannerTwoYearsTariff);

        double tempMonthToMonth = Math.round((countScannerMonthToMonthTariff * 29.99) * 100.0) / 100.0;
        logger.info("Without deactivated scanners MonthToMonth: " + tempMonthToMonth);
        double monthToMonth = tempMonthToMonth + sumDeactivatedScannerMonthToMonthTariff;
        logger.info("charge all scanners monthToMonth: " + monthToMonth);
        double tempOneYearTariff = Math.round((countScannerOneYearTariff * 329.89) * 100.0) / 100.0;
        logger.info("charge tempOneYearTariff: " + tempOneYearTariff);
        double tempTwoYearsTariff = Math.round((countScannerTwoYearsTariff * 629.79) * 100.0) / 100.0;
        logger.info("charge tempTwoYearsTariff: " + tempTwoYearsTariff);

        double tempCountDueCharge = Math.round((monthToMonth + tempOneYearTariff + tempTwoYearsTariff) * 100.0) / 100.0;
        logger.info("tempCountDueCharge: " + tempCountDueCharge);
        double sum = 0;

        for (String element :
                amountDue) {
            sum += Double.parseDouble(element);
        }
        logger.info("sumCountDueChargeFront: " + Math.round((sum) * 100.0) / 100.0);
        boolean tempCompareDue = tempCountDueCharge == Math.round((sum) * 100.0) / 100.0;
        return tempCompareDue;
    }


    @Step
    public boolean comparePaidTillMonthToMonth(String soloOrFleetString, String userId, String monthToMonthTariff) throws SQLException, IOException, ClassNotFoundException {
        List<String> tempPaidTillMonthToMonth = utilsForDB.getPaidTillFromEldScanners(soloOrFleetString, userId, monthToMonthTariff);
        LocalDate firstDayOfMonth = LocalDate.parse(LocalDate.now().toString()).with(TemporalAdjusters.firstDayOfNextMonth());
        long firstDayOfNextMonth = firstDayOfMonth.atStartOfDay().toEpochSecond(ZoneOffset.UTC);

        for (String element : tempPaidTillMonthToMonth) {
            if (element.equals(Long.toString(firstDayOfNextMonth))){
            } else return false;
        } return true;
    }


    @Step
    public boolean comparePaidTillOneYear(String soloOrFleetString, String userId, String oneYearTariff) throws SQLException, IOException, ClassNotFoundException {
        List<String> tempPaidTillOneYear = utilsForDB.getPaidTillFromEldScanners(soloOrFleetString, userId, oneYearTariff);
        LocalDate firstDayOfYear = LocalDate.parse(LocalDate.now().plusYears(1).toString());
        long firstDayToOneYear = firstDayOfYear.atStartOfDay().toEpochSecond(ZoneOffset.UTC);

        for (String element : tempPaidTillOneYear) {
            if (element.equals(Long.toString(firstDayToOneYear))){
            } else return false;
        } return true;
    }

    @Step
    public boolean comparePaidTillTwoYears(String soloOrFleetString, String userId, String twoYearsTariffId) throws SQLException, IOException, ClassNotFoundException {
        List<String> tempPaidTillTwoYears = utilsForDB.getPaidTillFromEldScanners(soloOrFleetString, userId, twoYearsTariffId);
        LocalDate firstDayOfTwoYear = LocalDate.parse(LocalDate.now().plusYears(2).toString());
        long firstDayToTwoYear = firstDayOfTwoYear.atStartOfDay().toEpochSecond(ZoneOffset.UTC);

        for (String element : tempPaidTillTwoYears) {
            if (element.equals(Long.toString(firstDayToTwoYear))){
            } else return false;
        } return true;
    }

    @Step
    public boolean comparePaidTillFleet(String fleetId) throws SQLException, IOException, ClassNotFoundException {
        String tempPaidTill = utilsForDB.getPaidTillEzFinancesFleet(fleetId);
        String tempCurrentDue = utilsForDB.getCurrentDueEzFinancesFleet(fleetId);
        LocalDate firstDayOfNextMonth = LocalDate.parse(LocalDate.now().toString()).with(TemporalAdjusters.firstDayOfNextMonth());
        String firstDayOfNextMonthString = firstDayOfNextMonth.toString();
        logger.info("tempPaidTill: " + tempPaidTill);
        if (Double.parseDouble(tempCurrentDue) == 0.00){
            boolean result = tempPaidTill.equals(firstDayOfNextMonthString);
            return result;
        } else return true;
    }
    @Step
    public boolean compareEstimatedTillFleet(String fleetId) throws SQLException, IOException, ClassNotFoundException {
        String tempEstimatedTill = utilsForDB.getEstimatedTillEzFinancesFleet(fleetId);
        LocalDate firstDayOfNextMonth = LocalDate.parse(LocalDate.now().toString()).with(TemporalAdjusters.firstDayOfNextMonth());
        String firstDayOfNextMonthString = firstDayOfNextMonth.toString();
        logger.info("tempEstimatedTill: " + tempEstimatedTill);
        boolean result = tempEstimatedTill.equals(firstDayOfNextMonthString);
        return result;
    }
    @Step
    public boolean comparePaidTillSolo(String soloId) throws SQLException, IOException, ClassNotFoundException {
        String tempPaidTill = utilsForDB.getPaidTillEzFinancesSolo(soloId);
        String tempCurrentDue = utilsForDB.getCurrentDueEzFinancesSolo(soloId);
        LocalDate firstDayOfNextMonth = LocalDate.parse(LocalDate.now().toString()).with(TemporalAdjusters.firstDayOfNextMonth());
        String firstDayOfNextMonthString = firstDayOfNextMonth.toString();
        logger.info("tempPaidTill: " + tempPaidTill);
        if (Double.parseDouble(tempCurrentDue) == 0.00){
            boolean result = tempPaidTill.equals(firstDayOfNextMonthString);
            return result;
        } else return true;
    }
    @Step
    public boolean compareEstimatedTillSolo(String soloId) throws SQLException, IOException, ClassNotFoundException {
        String tempEstimatedTill = utilsForDB.getEstimatedTillEzFinancesSolo(soloId);
        LocalDate firstDayOfNextMonth = LocalDate.parse(LocalDate.now().toString()).with(TemporalAdjusters.firstDayOfNextMonth());
        String firstDayOfNextMonthString = firstDayOfNextMonth.toString();
        logger.info("tempEstimatedTill: " + tempEstimatedTill);
        boolean result = tempEstimatedTill.equals(firstDayOfNextMonthString);
        return result;
    }
}



