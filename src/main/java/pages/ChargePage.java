package pages;

import io.qameta.allure.Step;
import libs.ConfigProperties;
import libs.UtilsForDB;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
        logger.info("Cron check fleets was run");
        return startCronTimeLong;
    }
    @Step
    public String runCronCheckDrivers(){
        LocalDateTime startCronTime = LocalDateTime.parse(LocalDateTime.now(ZoneId.from(ZoneOffset.UTC)).toString());
        String startCronTimeLong = startCronTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        webDriver.get(checkDrivers);
        logger.info("Cron check Drivers was run");
        return startCronTimeLong;
    }
    @Step
    public boolean checkDateTimeDue(String soloOrFleetString, String userId, String timeRunCron) throws SQLException, IOException, ClassNotFoundException {
        List<String> dateTimeList = utilsForDB.getDateTimeEzDue(soloOrFleetString, userId);
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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
    public boolean compareDueCharge(String soloOrFleetString, String userId, int countScannerMonthToMonthTariff, int countScannerOneYearTariff, int countScannerTwoYearsTariff, int countDeactivatedScannerMonthToMonthTariff) throws SQLException, IOException, ClassNotFoundException {
        List<String> amountDue = utilsForDB.getAmountEzDue(soloOrFleetString, userId);
        double sum = 0;
        logger.info(countScannerMonthToMonthTariff);
        logger.info(countScannerOneYearTariff);
        logger.info(countScannerTwoYearsTariff);
        logger.info(countDeactivatedScannerMonthToMonthTariff);
        double tempMonthToMonth = Math.round((countScannerMonthToMonthTariff * 29.99) * 100.0) / 100.0;
        double tempDeactivatedMonthToMonth = countDeactivatedScannerMonthToMonthTariff * 15;
        double monthToMonth = tempMonthToMonth + tempDeactivatedMonthToMonth;
        logger.info(monthToMonth);
        double tempOneYearTariff = Math.round((countScannerOneYearTariff * 329.89) * 100.0) / 100.0;
        logger.info(tempOneYearTariff);
        double tempTwoYearsTariff = Math.round((countScannerTwoYearsTariff * 629.79) * 100.0) / 100.0;
        logger.info(tempTwoYearsTariff);
        double tempCountDueCharge = monthToMonth + tempOneYearTariff + tempTwoYearsTariff;
        logger.info("tempCountDueCharge " + tempCountDueCharge);

        for (String element :
                amountDue) {
            sum += Double.parseDouble(element);
        }
        logger.info("sum" + Math.round((sum) * 100.0) / 100.0);
        boolean tempCompareDue = tempCountDueCharge == Math.round((sum) * 100.0) / 100.0;
        return tempCompareDue;
    }


    @Step
    public boolean comparePaidTillMonthToMonthForFleet(String fleetId, String monthToMonthTariff) throws SQLException, IOException, ClassNotFoundException {
        List<String> tempPaidTillMonthToMonth = utilsForDB.getPaidTillForFleet(fleetId, monthToMonthTariff);
        LocalDate firstDayOfMonth = LocalDate.parse(LocalDate.now().toString()).with(TemporalAdjusters.firstDayOfNextMonth());
        long firstDayOfNextMonth = firstDayOfMonth.atStartOfDay().toEpochSecond(ZoneOffset.UTC);

        for (String element : tempPaidTillMonthToMonth) {
            if (element.equals(Long.toString(firstDayOfNextMonth))){
            } else return false;
        } return true;
    }

    @Step
    public boolean comparePaidTillMonthToMonthForSolo(String soloId, String monthToMonthTariff) throws SQLException, IOException, ClassNotFoundException {
        List<String> tempPaidTillMonthToMonth = utilsForDB.getPaidTillForSolo(soloId, monthToMonthTariff);
        LocalDate firstDayOfMonth = LocalDate.parse(LocalDate.now().toString()).with(TemporalAdjusters.firstDayOfNextMonth());
        long firstDayOfNextMonth = firstDayOfMonth.atStartOfDay().toEpochSecond(ZoneOffset.UTC);

        for (String element : tempPaidTillMonthToMonth) {
            if (element.equals(Long.toString(firstDayOfNextMonth))){
            } else return false;
        } return true;
    }
    @Step
    public boolean comparePaidTillOneYearForFleet(String fleetId, String oneYearTariff) throws SQLException, IOException, ClassNotFoundException {
        List<String> tempPaidTillOneYear = utilsForDB.getPaidTillForFleet(fleetId, oneYearTariff);
        LocalDate firstDayOfYear = LocalDate.parse(LocalDate.now().plusYears(1).toString());
        long firstDayToOneYear = firstDayOfYear.atStartOfDay().toEpochSecond(ZoneOffset.UTC);

        for (String element : tempPaidTillOneYear) {
            if (element.equals(Long.toString(firstDayToOneYear))){
            } else return false;
        } return true;
    }
    @Step
    public boolean comparePaidTillOneYearForSolo(String soloId, String oneYearTariff) throws SQLException, IOException, ClassNotFoundException {
        List<String> tempPaidTillOneYear = utilsForDB.getPaidTillForSolo(soloId, oneYearTariff);
        LocalDate firstDayOfYear = LocalDate.parse(LocalDate.now().plusYears(1).toString());
        long firstDayToOneYear = firstDayOfYear.atStartOfDay().toEpochSecond(ZoneOffset.UTC);

        for (String element : tempPaidTillOneYear) {
            if (element.equals(Long.toString(firstDayToOneYear))){
            } else return false;
        } return true;
    }
    @Step
    public boolean comparePaidTillTwoYearsForFleet(String fleetId, String twoYearsTariffId) throws SQLException, IOException, ClassNotFoundException {
        List<String> tempPaidTillTwoYears = utilsForDB.getPaidTillForFleet(fleetId, twoYearsTariffId);
        LocalDate firstDayOfTwoYear = LocalDate.parse(LocalDate.now().plusYears(2).toString());
        long firstDayToTwoYear = firstDayOfTwoYear.atStartOfDay().toEpochSecond(ZoneOffset.UTC);

        for (String element : tempPaidTillTwoYears) {
            if (element.equals(Long.toString(firstDayToTwoYear))){
            } else return false;
        } return true;
    }
    @Step
    public boolean comparePaidTillTwoYearsForSolo(String soloId, String twoYearsTariffId) throws SQLException, IOException, ClassNotFoundException {
        List<String> tempPaidTillTwoYears = utilsForDB.getPaidTillForSolo(soloId, twoYearsTariffId);
        LocalDate firstDayOfTwoYear = LocalDate.parse(LocalDate.now().plusYears(2).toString());
        long firstDayToTwoYear = firstDayOfTwoYear.atStartOfDay().toEpochSecond(ZoneOffset.UTC);

        for (String element : tempPaidTillTwoYears) {
            if (element.equals(Long.toString(firstDayToTwoYear))){
            } else return false;
        } return true;
    }
}



