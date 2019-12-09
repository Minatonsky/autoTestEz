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
    double eldMonthToMonthPrice = 29.99;
    double eld1YearSubscriptionPrice = 329.89;
    double eld2YearsSubscriptionPrice = 629.79;

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
    public String tariffStartForMonthToMonth(int countMonth, int countDays){
        LocalDateTime tariffStart = LocalDateTime.parse(LocalDateTime.now().minusMonths(countMonth).minusDays(countDays).toString());
        long previousMonthMinusDay = tariffStart.toEpochSecond(ZoneOffset.UTC);
        String tempTariffMonthStart = Long.toString(previousMonthMinusDay);
        logger.info("# Temp Tariff Month Start " + tariffStart);
        return tempTariffMonthStart;
    }
    @Step
    public String dateTimeEldHistoryForMonthToMonth(int countMonth, int countDays){
        LocalDateTime dateTimeEldHistory = LocalDateTime.parse(LocalDateTime.now().minusMonths(countMonth).minusDays(countDays).toString());
        long previousMonthMinusDay = dateTimeEldHistory.toEpochSecond(ZoneOffset.UTC);
        String tempDateTimeEldHistory = Long.toString(previousMonthMinusDay);
        logger.info("# Temp Date Time Eld History " + dateTimeEldHistory);
        return tempDateTimeEldHistory;
    }

    @Step
    public String tariffStartForOneYear(int countYears){
        LocalDateTime tariffStart = LocalDateTime.parse(LocalDateTime.now().minusYears(countYears).minusDays(1).toString());
        long previousYearMinusDay = tariffStart.toEpochSecond(ZoneOffset.UTC);
        String tempTariffYearStart = Long.toString(previousYearMinusDay);
        return tempTariffYearStart;
    }

    @Step
    public String tariffStartForTwoYears(int countYears){
        LocalDateTime tariffStart = LocalDateTime.parse(LocalDateTime.now().minusYears(countYears).minusDays(1).toString());
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
    public boolean  checkDateTimeDue(String soloOrFleetString, String userId, String timeRunCron) throws SQLException, IOException, ClassNotFoundException {
        List<String> dateTimeList = utilsForDB.getDateTimeEzDue(soloOrFleetString, userId);
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        logger.info("dateTime: " + dateTimeList);
        for (String element : dateTimeList) {
            if (LocalDateTime.parse(element, dTF).plusMinutes(1).isAfter(LocalDateTime.parse(timeRunCron, dTF))) {
            } else return false;
        } return true;
    }
    @Step
    public boolean checkDateTimeDueMonthToMonth(String soloOrFleetString, String userId, String timeRunCron) throws SQLException, IOException, ClassNotFoundException {
        List<String> dateTimeList = utilsForDB.getDateTimeEzDueMonthToMonth(soloOrFleetString, userId);
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        logger.info("# Date Time Last Due = " + dateTimeList);
        for (String element : dateTimeList) {
            if (LocalDateTime.parse(element, dTF).plusMinutes(1).isAfter(LocalDateTime.parse(timeRunCron, dTF))) {
            } else return false;
        } return true;
    }

    @Step
    public boolean checkIfTariffPresent(int monthIOSXTariff, int oneYearIOSXTariff, int twoYearsIOSXTariff, int monthGeometricsTariff, int oneYearGeometricsTariff, int twoYearsGeometricsTariff){
        logger.info("# COUNT MONTH TO MONTH IOSX TARIFF = " + monthIOSXTariff);
        logger.info("# COUNT ONE YEAR IOSX TARIFF = " + oneYearIOSXTariff);
        logger.info("# COUNT TWO YEARS IOSX TARIFF = " + twoYearsIOSXTariff);
        logger.info("# COUNT MONTH TO MONTH GEOMETRICS TARIFF = " + monthGeometricsTariff);
        logger.info("# COUNT ONE YEAR GEOMETRICS TARIFF = " + oneYearGeometricsTariff);
        logger.info("# COUNT TWO YEARS GEOMETRICS TARIFF = " + twoYearsGeometricsTariff);
        if (monthIOSXTariff > 0 & oneYearIOSXTariff > 0 & twoYearsIOSXTariff > 0 & monthGeometricsTariff > 0 & oneYearGeometricsTariff > 0 & twoYearsGeometricsTariff > 0) {
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
                    double deactivatedCharge = eldMonthToMonthPrice / 2 / monthDays * deactivatedDays;
                    double activeCharge = eldMonthToMonthPrice / monthDays * activeChargeDays;
                    double charge_amount = Math.round((activeCharge + deactivatedCharge) * 100.0) / 100.0;
                    sum += charge_amount;

                } else {
                    double charge_amount = Math.round((eldMonthToMonthPrice / 2) * 100.0) / 100.0;
                    sum += charge_amount;
                }
            } logger.info("# DEACTIVATED SUM CHARGE = " + sum); return sum;

        } else return 0;

    }
    @Step
    public double sumCharge(int countScannerMonthToMonthTariff, int countScannerOneYearTariff, int countScannerTwoYearsTariff, String typeTariff){

        double tempMonthToMonth = Math.round((countScannerMonthToMonthTariff * eldMonthToMonthPrice) * 100.0) / 100.0;
        logger.info("# MONTH CHARGE " + typeTariff + " = " + tempMonthToMonth);
        double tempOneYearTariff = Math.round((countScannerOneYearTariff * eld1YearSubscriptionPrice) * 100.0) / 100.0;
        logger.info("# ONE YEAR CHARGE " + typeTariff + " = "  + tempOneYearTariff);
        double tempTwoYearsTariff = Math.round((countScannerTwoYearsTariff * eld2YearsSubscriptionPrice) * 100.0) / 100.0;
        logger.info("# TWO YEARS CHARGE " + typeTariff + " = "  + tempTwoYearsTariff);
        double tempCountDueCharge = Math.round((tempMonthToMonth + tempOneYearTariff + tempTwoYearsTariff) * 100.0) / 100.0;
        logger.info("# SUM DUE CHARGE " + typeTariff + " = "  + tempCountDueCharge);
        return tempCountDueCharge;
    }

    @Step
    public boolean compareDueCharge(String soloOrFleetString, String userId, double sumCharge, String timeRunCron) throws SQLException, IOException, ClassNotFoundException {
        List<String> amountDue = utilsForDB.getAmountEzDue(soloOrFleetString, userId, timeRunCron);
        double tempSumCharge = Math.round((sumCharge) * 100.0) / 100.0;
        logger.info("# LIST AMOUNT DUE FROM DB = " + amountDue);
        logger.info("# PROGRAM COUNT CHARGE  = " + tempSumCharge);
        double sum = 0;

        for (String element :
                amountDue) {
            sum += Double.parseDouble(element);
        }
        logger.info("# SUM DB DUE (CHARGE) = " + Math.round((sum) * 100.0) / 100.0);
        boolean tempCompareDue = tempSumCharge == Math.round((sum) * 100.0) / 100.0;
        return tempCompareDue;
    }
    @Step
    public boolean compareCurrentDueFleetDefaulters(String fleetId, double sumCharge) throws SQLException, IOException, ClassNotFoundException {
        String currentDueFleet = utilsForDB.getCurrentDueEzFinancesFleet(fleetId);
        logger.info("# CURRENT DUE FLEET " + currentDueFleet);
        logger.info("# SUM CHARGE " + sumCharge);
        boolean tempCompareDueFleet = sumCharge == Double.parseDouble(currentDueFleet);
        return tempCompareDueFleet;
    }
    @Step
    public boolean compareCurrentDueSoloDefaulters(String soloId, double sumCharge) throws SQLException, IOException, ClassNotFoundException {
        String currentDueSolo = utilsForDB.getCurrentDueEzFinancesSolo(soloId);
        logger.info("# CURRENT DUE SOLO " + currentDueSolo);
        logger.info("# SUM CHARGE " + sumCharge);
        boolean tempCompareDueSolo = sumCharge == Double.parseDouble(currentDueSolo);
        return tempCompareDueSolo;
    }

    @Step
    public boolean comparePaidTillMonthToMonth(String soloOrFleetString, String userId, String tempTariffId) throws SQLException, IOException, ClassNotFoundException {
        List<String> tempPaidTillMonthToMonth = utilsForDB.getPaidTillFromEldScanners(soloOrFleetString, userId, tempTariffId);
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
        logger.info("# tempPaidTill: " + tempPaidTill);
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
        logger.info("# tempEstimatedTill: " + tempEstimatedTill);
        boolean result = tempEstimatedTill.equals(firstDayOfNextMonthString);
        return result;
    }
    @Step
    public boolean compareDueChargeMonthToMonthTariff(String activationDate, String soloOrFleetString, String userId, int countScannerMonthToMonthTariff,
                                                      double sumDeactivatedScannerMonthToMonthTariff, String timeRunCron) throws SQLException, IOException, ClassNotFoundException {
        List<String> amountDue = utilsForDB.getAmountEzDueMonthToMonth(soloOrFleetString, userId, timeRunCron);
        logger.info("# AMOUNT DUE FROM DB = " + amountDue);
        logger.info("# Sum Of Deactivated Scanner = " + sumDeactivatedScannerMonthToMonthTariff);


        LocalDateTime currentTime = LocalDateTime.parse(LocalDateTime.now().toString());
        LocalDateTime tempActivationDate = LocalDateTime.ofEpochSecond(Long.parseLong(activationDate), 0, ZoneOffset.UTC);
        int monthDays =  currentTime.getMonth().length(true);
        double priceOneDay = eldMonthToMonthPrice / monthDays;
        int activeChargeDays = (int) ChronoUnit.DAYS.between(tempActivationDate, currentTime) - 1;
        double sum = 0;

        if (activeChargeDays <= monthDays) {
            double chargeByDevice = Math.round((activeChargeDays * priceOneDay) * 100.0) / 100.0;
            double chargeByAllDevices = Math.round((countScannerMonthToMonthTariff * chargeByDevice) * 100.0) / 100.0;

            logger.info("***** Month Days = " + monthDays);
            logger.info("***** Price For One Day = " + priceOneDay);
            logger.info("***** Active Charge Days = " + activeChargeDays);
            logger.info("***** chargeByDevice = " + chargeByDevice);
            logger.info("***** chargeByAllDevices = " + chargeByAllDevices);

            for (String element :
                    amountDue) {
                sum += Double.parseDouble(element);
            }
            boolean tempCompareDue = chargeByAllDevices == Math.round((sum) * 100.0) / 100.0;
            return tempCompareDue;
        } else {
            for (String element :
                    amountDue) {
                sum += Double.parseDouble(element);
            }
            double tempMonthToMonth = Math.round(((countScannerMonthToMonthTariff * eldMonthToMonthPrice) + sumDeactivatedScannerMonthToMonthTariff) * 100.0) / 100.0;
            logger.info("# Program Charge Month To Month = " + tempMonthToMonth);
            logger.info("# SUM DB DUE (CHARGE) = " + Math.round((sum) * 100.0) / 100.0);
            boolean tempCompareDue = tempMonthToMonth == Math.round((sum) * 100.0) / 100.0;
            return tempCompareDue;
        }
    }

    @Step
    public void setDaysDefaulterFleet(String fleetId, int contDays) throws SQLException, IOException, ClassNotFoundException {
        LocalDate tempDay_10_Defaulter = LocalDate.parse(LocalDate.now().minusDays(contDays).toString());
        long tenDays = tempDay_10_Defaulter.atStartOfDay().toEpochSecond(ZoneOffset.UTC);
        String day_10_Defaulter = Long.toString(tenDays);
        LocalDate yesterday = LocalDate.parse(LocalDate.now().minusDays(1).toString());
        long tempYesterday = yesterday.atStartOfDay().toEpochSecond(ZoneOffset.UTC);
        String lastEmailTime = Long.toString(tempYesterday);
        utilsForDB.setDateAndEmailFleetDefaulters(day_10_Defaulter, lastEmailTime, fleetId);
    }
    @Step
    public void setDaysDefaulterSolo(String soloId, int contDays) throws SQLException, IOException, ClassNotFoundException {
        LocalDate tempDay_10_Defaulter = LocalDate.parse(LocalDate.now().minusDays(contDays).toString());
        long tenDays = tempDay_10_Defaulter.atStartOfDay().toEpochSecond(ZoneOffset.UTC);
        String day_10_Defaulter = Long.toString(tenDays);
        LocalDate yesterday = LocalDate.parse(LocalDate.now().minusDays(1).toString());
        long tempYesterday = yesterday.atStartOfDay().toEpochSecond(ZoneOffset.UTC);
        String lastEmailTime = Long.toString(tempYesterday);
        utilsForDB.setDateAndEmailSoloDefaulters(day_10_Defaulter, lastEmailTime, soloId);
    }
    @Step
    public boolean checkLateFeeFleet(String fleetId, double sumCharge, String fleetString) throws SQLException, IOException, ClassNotFoundException {
        LocalDate firstDayOfMonth = LocalDate.parse(LocalDate.now().toString()).with(TemporalAdjusters.firstDayOfNextMonth());
        long tempDayOfNextMonth = firstDayOfMonth.atStartOfDay().plusSeconds(1).toEpochSecond(ZoneOffset.UTC);
        String firstDayOfNextMonth = Long.toString(tempDayOfNextMonth);
        System.out.println("firstDayOfNextMonth = " + firstDayOfNextMonth);
        String currentDue = utilsForDB.getCurrentDueEzFinancesFleet(fleetId);
        int countChargeScanners = utilsForDB.countChargeScanners(fleetString, fleetId, firstDayOfNextMonth);
        int countReturnDevices = utilsForDB.countChargeReturnedScanners(fleetString, fleetId, firstDayOfNextMonth);
        logger.info("currentDue = " + currentDue);
        logger.info("countChargeLateFeeDevices = " + (countChargeScanners + countReturnDevices));
        double tempDueWithLateFee = Math.round((((countChargeScanners + countReturnDevices) * 7.99) + sumCharge) * 100.0) / 100.0;
        logger.info("tempDueWithLateFee = " + tempDueWithLateFee);
        boolean compareDues = tempDueWithLateFee == Double.parseDouble(currentDue);
        return compareDues;
    }
    @Step
    public boolean checkLateFeeSolo(String soloId, double sumCharge, String userIdString) throws SQLException, IOException, ClassNotFoundException {
        LocalDate firstDayOfMonth = LocalDate.parse(LocalDate.now().toString()).with(TemporalAdjusters.firstDayOfNextMonth());
        long tempDayOfNextMonth = firstDayOfMonth.atStartOfDay().plusSeconds(1).toEpochSecond(ZoneOffset.UTC);
        String firstDayOfNextMonth = Long.toString(tempDayOfNextMonth);
        System.out.println("firstDayOfNextMonth = " + firstDayOfNextMonth);
        String currentDue = utilsForDB.getCurrentDueEzFinancesSolo(soloId);
        int countChargeScanners = utilsForDB.countChargeScanners(userIdString, soloId, firstDayOfNextMonth);
        int countReturnDevices = utilsForDB.countChargeReturnedScanners(userIdString, soloId, firstDayOfNextMonth);
        logger.info("currentDue = " + currentDue);
        logger.info("countChargeLateFeeDevices = " + (countChargeScanners + countReturnDevices));
        double tempDueWithLateFee = Math.round((((countChargeScanners + countReturnDevices) * 7.99) + sumCharge) * 100.0) / 100.0;
        logger.info("tempDueWithLateFee = " + tempDueWithLateFee);
        boolean compareDues = tempDueWithLateFee == Double.parseDouble(currentDue);
        return compareDues;
    }
    @Step
    public boolean checkStatusesDevices(List<String> listOfActiveDevices, String numberStatus) throws SQLException, IOException, ClassNotFoundException {
        String stringOfActiveDevices = String.join(",", listOfActiveDevices);
        List<String> listOfStatuses = utilsForDB.getScannersStatus(stringOfActiveDevices);
        for (String element:
                listOfStatuses) {
            if (element.equals(numberStatus)){
            } else return false;
        }
        return true;
    }
    @Step
    public int countScannersByTariffStart(List<String> listOfActiveDevices) throws SQLException, IOException, ClassNotFoundException {
        LocalDateTime tempDate = LocalDateTime.parse(LocalDateTime.now().toString());
        long date = tempDate.toEpochSecond(ZoneOffset.UTC);
        String tempDateToday = Long.toString(date);

        String stringOfActiveDevices = String.join(",", listOfActiveDevices);
        List<String> listDevicesTariffStart = utilsForDB.getScannersTariffStart(stringOfActiveDevices);
        logger.info("listDevicesTariffStart = " + listDevicesTariffStart);
        int count = 0;
        for (String element :
                listDevicesTariffStart) {
            if (((Integer.parseInt(tempDateToday) - Integer.parseInt(element))/2629743) > 12) {
                 count++;
            }
        } int tempCount = count;
        logger.info("countDevicesAfter_12Month = " + tempCount);
        return tempCount;
    }
    @Step
    public boolean checkProratedAndNotReturnedFee(List<String> listOfActiveDevices, String currentDueWithLateFee, String currentDueWithLateReturnedProratedFee, int countDevicesAfter_12Month) throws SQLException, IOException, ClassNotFoundException {

        int countDevices = listOfActiveDevices.size();
        int countDevicesBefore_12Month = countDevices - countDevicesAfter_12Month;
        logger.info("countDevices = " + countDevices);
        double tempNotReturnedFee = Math.round((countDevices * 199.99) * 100.0) / 100.0;
        logger.info("tempNotReturnedFee = " + tempNotReturnedFee);

        double tempProratedFee = Math.round(((countDevicesBefore_12Month * eldMonthToMonthPrice) + (countDevicesAfter_12Month * 59.98)) * 100.0) / 100.0;
        logger.info("tempProratedFee = " + tempProratedFee);
        double tempDueWithReturnedProratedFee = Math.round((tempNotReturnedFee + tempProratedFee + Double.parseDouble(currentDueWithLateFee)) * 100.0) / 100.0;
        logger.info("tempDueWithReturnedProratedFee = " + tempDueWithReturnedProratedFee);
        boolean tempResult = tempDueWithReturnedProratedFee == Double.parseDouble(currentDueWithLateReturnedProratedFee);
        return tempResult;
    }

    public void informationOfDeactivatedAndReturnedScanners(int countDeactivatedScannerMonthIOSXTariff, int countScannerMonthIOSXChargeReturned, int countScannerOneYearIOSXChargeReturned, int countScannerTwoYearIOSXChargeReturned,
                                                            int countGeometricsMonthChargeReturnedScanner, int countGeometricsOneYearChargeReturnedScanner, int countGeometricsTwoYearChargeReturnedScanner) {
        logger.info("# Deactivated Month IOSX Tariff = " + countDeactivatedScannerMonthIOSXTariff);
        logger.info("# Month IOSX Charge Returned = " + countScannerMonthIOSXChargeReturned);
        logger.info("# One Year IOSX Charge Returned = " + countScannerOneYearIOSXChargeReturned);
        logger.info("# Two Year IOSX Charge Returned = " + countScannerTwoYearIOSXChargeReturned);

        logger.info("# Geometrics Month Charge Returned = " + countGeometricsMonthChargeReturnedScanner);
        logger.info("# Geometrics One Year Charge Returned = " + countGeometricsOneYearChargeReturnedScanner);
        logger.info("# Geometrics Two Year Charge Returned = " + countGeometricsTwoYearChargeReturnedScanner);
    }

    public void informationOfScannersMonth(int countScannerMonthIOSXTariff, int countScannerMonthGeometricsTariff, int countDeactivatedScannerMonthIOSXTariff, int countMonthIOSXChargeReturnedScanner, int countMonthGeometricsChargeReturnedScanner) {
        logger.info("# COUNT Scanner Month IOSX Tariff = " + countScannerMonthIOSXTariff);
        logger.info("# count Scanner Month Geometrics Tariff = " + countScannerMonthGeometricsTariff);
        logger.info("# count Deactivated Scanner Month IOSX Tariff = " + countDeactivatedScannerMonthIOSXTariff);
        logger.info("# count Month IOSX Charge Returned Scanner = " + countMonthIOSXChargeReturnedScanner);
        logger.info("# count Month Geometric sCharge Returned Scanner = " + countMonthGeometricsChargeReturnedScanner);
    }
}




