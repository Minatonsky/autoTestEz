package chargeTest;

import org.junit.Test;
import parentTest.ParentChargeTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ChargeDefaultersTest extends ParentChargeTest {
    String monthIOSXTariffId = "0";
    String oneYearIOSXTariffId = "1";
    String twoYearsIOSXTariffId = "2";
    String monthGeometricsTariffId = "9";
    String oneYearGeometricsTariffId = "10";
    String twoYearsGeometricsTariffId = "11";
    String monthEzHardTariffId = "12";
    String oneYearEzHardTariffId = "13";
    String twoYearsEzHardTariffId = "14";

    String carrierIdString = "carrierId";
    String fleetString = "fleet";
    String userIdString = "userId";

    String soloId = "3455";
    String fleetId = "518";

    int countMonthForTariffStartMonthIOSX = 3;
    int countMonthForTariffStartMonthGeometrics = 2;
    int countMonthForTariffStartMonthEzHard = 2;
    int countYearOneYearIOSX = 1;
    int countYearTwoYearIOSX = 2;
    String currentDue = "0";


    @Test
    public void chargeDefaultersFleetTest() throws SQLException, IOException, ClassNotFoundException {
        utilsForDB.setCurrentDueForFleet(currentDue, fleetId);
        utilsForDB.setCurrentCard_0_Fleet(fleetId);

//  COUNT IOSX TARIFF
        int countScannerMonthIOSXTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, monthIOSXTariffId);
        int countDeactivatedScannerMonthIOSXTariff = utilsForDB.countDeactivatedChargeScannersMonthToMonth(fleetString, fleetId);
        int countScannerOneYearIOSXTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, oneYearIOSXTariffId);
        int countScannerTwoYearsIOSXTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, twoYearsIOSXTariffId);
        int countScannerMonthIOSXChargeReturned = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, monthIOSXTariffId);
        int countScannerOneYearIOSXChargeReturned = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, oneYearIOSXTariffId);
        int countScannerTwoYearIOSXChargeReturned = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, twoYearsIOSXTariffId);


//   COUNT GEOMETRICS TARIFF
        int countScannerMonthGeometricsTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, monthGeometricsTariffId);
        int countScannerOneYearGeometricsTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, oneYearGeometricsTariffId);
        int countScannerTwoYearsGeometricsTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, twoYearsGeometricsTariffId);
        int countGeometricsMonthChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, monthGeometricsTariffId);
        int countGeometricsOneYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, oneYearGeometricsTariffId);
        int countGeometricsTwoYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, twoYearsGeometricsTariffId);

//   COUNT EZ HARD TARIFF
        int countScannerMonthEzHardTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, monthEzHardTariffId);
        int countScannerOneYearEzHardTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, oneYearEzHardTariffId);
        int countScannerTwoYearsEzHardTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, twoYearsEzHardTariffId);
        int countEzHardMonthChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, monthEzHardTariffId);
        int countEzHardOneYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, oneYearEzHardTariffId);
        int countEzHardTwoYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, twoYearsEzHardTariffId);

        String setPaidTillForAllTariff = chargePage.paidTillForAllTariff();

        String setTariffStartMonthIOSX = chargePage.tariffStartForMonthToMonth(countMonthForTariffStartMonthIOSX, 5);
        String setTariffStartMonthGeometrics = chargePage.tariffStartForMonthToMonth(countMonthForTariffStartMonthGeometrics, 5);
        String setTariffStartMonthEzHard = chargePage.tariffStartForMonthToMonth(countMonthForTariffStartMonthEzHard, 22);

        String setTariffStartOneYear = chargePage.tariffStartForOneYear(1);
        String setTariffStartTwoYears = chargePage.tariffStartForTwoYears(2);


        checkAC("* No all tariffs are presented on fleet",
                chargePage.checkIfTariffPresent(
                        countScannerMonthIOSXTariff, countScannerOneYearIOSXTariff, countScannerTwoYearsIOSXTariff ,
                        countScannerMonthGeometricsTariff, countScannerOneYearGeometricsTariff, countScannerTwoYearsGeometricsTariff,
                        countScannerMonthEzHardTariff, countScannerOneYearEzHardTariff, countScannerTwoYearsEzHardTariff
                ), true);

        chargePage.informationOfDeactivatedAndReturnedScanners(countDeactivatedScannerMonthIOSXTariff, countScannerMonthIOSXChargeReturned, countScannerOneYearIOSXChargeReturned, countScannerTwoYearIOSXChargeReturned,
                countGeometricsMonthChargeReturnedScanner, countGeometricsOneYearChargeReturnedScanner, countGeometricsTwoYearChargeReturnedScanner,
                countEzHardMonthChargeReturnedScanner, countEzHardOneYearChargeReturnedScanner, countEzHardTwoYearChargeReturnedScanner);


//  SET PAID TILL AND ORDER DATE FOR IOSX TARIFF

        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartMonthIOSX, monthIOSXTariffId);
        utilsForDB.setOrderDateByTariffId(fleetString, fleetId, setTariffStartMonthIOSX, monthIOSXTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearIOSXTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsIOSXTariffId);


//  SET PAID TILL AND ORDER DATE FOR GEOMETRICS TARIFF

        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartMonthGeometrics, monthGeometricsTariffId);
        utilsForDB.setOrderDateByTariffId(fleetString, fleetId, setTariffStartMonthGeometrics, monthGeometricsTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearGeometricsTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsGeometricsTariffId);

//  SET PAID TILL AND ORDER DATE FOR EZ HARD TARIFF

        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartMonthGeometrics, monthEzHardTariffId);
        utilsForDB.setOrderDateByTariffId(fleetString, fleetId, setTariffStartMonthEzHard, monthEzHardTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearEzHardTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsEzHardTariffId);

//  SET PAID TILL FOR USER FINANCES

        String paidTillForEzFinances = chargePage.paidTillForEzFinances();
        utilsForDB.setPaidTillEstimatedTillEzFinancesFleet(fleetId, paidTillForEzFinances, paidTillForEzFinances);
        String timeRunCron = chargePage.runCronCheckFleet();
        checkAC("DateTime dues are not correct", chargePage.checkDateTimeDue(carrierIdString, fleetId, timeRunCron), true);

        double sumDeactivatedScannerMonthIOSXTariff = chargePage.sumDeactivatedScannerMonthToMonthTariff(fleetString, fleetId, countDeactivatedScannerMonthIOSXTariff);
        double sumIOSXCharge = chargePage.sumCharge(
                countScannerMonthIOSXTariff + countScannerMonthIOSXChargeReturned ,
                countScannerOneYearIOSXTariff + countScannerOneYearIOSXChargeReturned,
                countScannerTwoYearsIOSXTariff + countScannerTwoYearIOSXChargeReturned, "IOSX");
        double sumGeometricsCharge = chargePage.sumCharge(
                countScannerMonthGeometricsTariff + countGeometricsMonthChargeReturnedScanner,
                countScannerOneYearGeometricsTariff + countGeometricsOneYearChargeReturnedScanner,
                countScannerTwoYearsGeometricsTariff + countGeometricsTwoYearChargeReturnedScanner, "Geometrics");
        double sumEzHardCharge = chargePage.sumCharge(
                countScannerMonthEzHardTariff + countEzHardMonthChargeReturnedScanner,
                countScannerOneYearEzHardTariff + countEzHardOneYearChargeReturnedScanner,
                countScannerTwoYearsEzHardTariff + countEzHardTwoYearChargeReturnedScanner, "EzHard");
        checkAC("Charge due is not correct", chargePage.compareDueCharge(carrierIdString, fleetId, sumIOSXCharge + sumDeactivatedScannerMonthIOSXTariff + sumGeometricsCharge + sumEzHardCharge, timeRunCron), true);

        checkAC("Fleet is not in defaulters", utilsForDB.checkFleetInDefaulters(fleetId), true);

        chargePage.setDaysDefaulterFleet(fleetId, 10);
        chargePage.runCronCheckFleet();
        List<String> listOfStatusDevices =  utilsForDB.getIdScannersByStatus(fleetString, fleetId, "4");
        String stringOfStatusesDevices = String.join(",", listOfStatusDevices);
        checkAC("Late Fee is not correct", chargePage.checkLateFeeFleet(fleetId, sumIOSXCharge + sumDeactivatedScannerMonthIOSXTariff + sumGeometricsCharge + sumEzHardCharge, fleetString), true);

        chargePage.setDaysDefaulterFleet(fleetId, 15);
        chargePage.runCronCheckFleet();
        checkAC("Devices does not have status Not Paid", chargePage.checkStatusesDevices(listOfStatusDevices, "8"), true);
        checkAC("Fleet is not deactivated", utilsForDB.checkFleetIsDeactivated(fleetId), true);
        String currentDueWithLateFee = utilsForDB.getCurrentDueEzFinancesFleet(fleetId);
        chargePage.setDaysDefaulterFleet(fleetId, 52);
        chargePage.runCronCheckFleet();
        String currentDueWithLateReturnedProratedFee = utilsForDB.getCurrentDueEzFinancesFleet(fleetId);
        int countDevicesAfter_12Month = chargePage.countScannersByTariffStart(listOfStatusDevices);
        checkAC("ProratedAndNotReturnedFee is not correct", chargePage.checkProratedAndNotReturnedFee(listOfStatusDevices, currentDueWithLateFee, currentDueWithLateReturnedProratedFee, countDevicesAfter_12Month), true);
        checkAC("Devices does not have status Not disconnected", chargePage.checkStatusesDevices(listOfStatusDevices, "12"), true);
        checkAC("Fleet is not Banned", utilsForDB.checkFleetIsBanned(fleetId), true);
        utilsForDB.setCurrentCard(carrierIdString, fleetId);
        utilsForDB.setUnbanFleet(fleetId);
        utilsForDB.set_0_DeactivatedFleet(fleetId);
        utilsForDB.setStatusesForDevices(stringOfStatusesDevices, "8");
        utilsForDB.setCurrentDueForFleet("0", fleetId);
        chargePage.runCronCheckFleet();

    }
    @Test
    public void chargeDefaultersSoloTest() throws SQLException, IOException, ClassNotFoundException {
        utilsForDB.setCurrentDueForSolo(currentDue, soloId);
        utilsForDB.setCurrentCard_0_Solo(soloId);

//   COUNT IOSX TARIFF
        int countScannerMonthIOSXTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, monthIOSXTariffId);
        int countDeactivatedScannerMonthIOSXTariff = utilsForDB.countDeactivatedChargeScannersMonthToMonth(userIdString, soloId);
        int countScannerOneYearIOSXTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, oneYearIOSXTariffId);
        int countScannerTwoYearsIOSXTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, twoYearsIOSXTariffId);

        int countScannerMonthIOSXChargeReturned = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, monthIOSXTariffId);
        int countScannerOneYearIOSXChargeReturned = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, oneYearIOSXTariffId);
        int countScannerTwoYearIOSXChargeReturned = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, twoYearsIOSXTariffId);

//   COUNT GEOMETRICS TARIFF
        int countScannerMonthGeometricsTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, monthGeometricsTariffId);
        int countScannerOneYearGeometricsTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, oneYearGeometricsTariffId);
        int countScannerTwoYearsGeometricsTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, twoYearsGeometricsTariffId);
        int countGeometricsMonthChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, monthGeometricsTariffId);
        int countGeometricsOneYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, oneYearGeometricsTariffId);
        int countGeometricsTwoYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, twoYearsGeometricsTariffId);

//   COUNT EZ HARD TARIFF
        int countScannerMonthEzHardTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, monthEzHardTariffId);
        int countScannerOneYearEzHardTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, oneYearEzHardTariffId);
        int countScannerTwoYearsEzHardTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, twoYearsEzHardTariffId);
        int countEzHardMonthChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, monthEzHardTariffId);
        int countEzHardOneYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, oneYearEzHardTariffId);
        int countEzHardTwoYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, twoYearsEzHardTariffId);


        String setPaidTillForAllTariff = chargePage.paidTillForAllTariff();

        String setTariffStartMonthIOSX = chargePage.tariffStartForMonthToMonth(countMonthForTariffStartMonthIOSX, 5);
        String setTariffStartMonthGeometrics = chargePage.tariffStartForMonthToMonth(countMonthForTariffStartMonthGeometrics, 5);
        String setTariffStartMonthEzHard = chargePage.tariffStartForMonthToMonth(countMonthForTariffStartMonthEzHard, 22);

        String setTariffStartOneYear = chargePage.tariffStartForOneYear(1);
        String setTariffStartTwoYears = chargePage.tariffStartForTwoYears(2);

        checkAC("No all tariffs are presented in eld scanners",
                chargePage.checkIfTariffPresent(countScannerMonthIOSXTariff, countScannerOneYearIOSXTariff, countScannerTwoYearsIOSXTariff ,
                        countScannerMonthGeometricsTariff, countScannerOneYearGeometricsTariff, countScannerTwoYearsGeometricsTariff,
                        countScannerMonthEzHardTariff, countScannerOneYearEzHardTariff, countScannerTwoYearsEzHardTariff), true);

        chargePage.informationOfDeactivatedAndReturnedScanners(countDeactivatedScannerMonthIOSXTariff, countScannerMonthIOSXChargeReturned, countScannerOneYearIOSXChargeReturned, countScannerTwoYearIOSXChargeReturned,
                countGeometricsMonthChargeReturnedScanner, countGeometricsOneYearChargeReturnedScanner, countGeometricsTwoYearChargeReturnedScanner,
                countEzHardMonthChargeReturnedScanner, countEzHardOneYearChargeReturnedScanner, countEzHardTwoYearChargeReturnedScanner);

//  SET PAID TILL AND ORDER DATE FOR IOSX TARIFF

        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartMonthIOSX, monthIOSXTariffId);
        utilsForDB.setOrderDateByTariffId(userIdString, soloId, setTariffStartMonthIOSX, monthIOSXTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearIOSXTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsIOSXTariffId);


//  SET PAID TILL AND ORDER DATE FOR GEOMETRICS TARIFF

        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartMonthGeometrics, monthGeometricsTariffId);
        utilsForDB.setOrderDateByTariffId(userIdString, soloId, setTariffStartMonthGeometrics, monthGeometricsTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearGeometricsTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsGeometricsTariffId);

//  SET PAID TILL AND ORDER DATE FOR EZ HARD TARIFF

        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartMonthGeometrics, monthEzHardTariffId);
        utilsForDB.setOrderDateByTariffId(userIdString, soloId, setTariffStartMonthEzHard, monthEzHardTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearEzHardTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsEzHardTariffId);

//  SET PAID TILL FOR USER FINANCES
        String paidTillForEzFinances = chargePage.paidTillForEzFinances();
        utilsForDB.setPaidTillEstimatedTillEzFinancesSolo(soloId, paidTillForEzFinances, paidTillForEzFinances);
        String timeRunCron = chargePage.runCronCheckDrivers();
        checkAC("DateTime dues are not correct", chargePage.checkDateTimeDue(userIdString, soloId, timeRunCron), true);

        double sumDeactivatedScannerMonthIOSXTariff = chargePage.sumDeactivatedScannerMonthToMonthTariff(userIdString, soloId, countDeactivatedScannerMonthIOSXTariff);
        double sumIOSXCharge = chargePage.sumCharge(
                countScannerMonthIOSXTariff + countScannerMonthIOSXChargeReturned ,
                countScannerOneYearIOSXTariff + countScannerOneYearIOSXChargeReturned,
                countScannerTwoYearsIOSXTariff + countScannerTwoYearIOSXChargeReturned, "IOSX");
        double sumGeometricsCharge = chargePage.sumCharge(
                countScannerMonthGeometricsTariff + countGeometricsMonthChargeReturnedScanner,
                countScannerOneYearGeometricsTariff + countGeometricsOneYearChargeReturnedScanner,
                countScannerTwoYearsGeometricsTariff + countGeometricsTwoYearChargeReturnedScanner, "Geometrics");
        double sumEzHardCharge = chargePage.sumCharge(
                countScannerMonthEzHardTariff + countEzHardMonthChargeReturnedScanner,
                countScannerOneYearEzHardTariff + countEzHardOneYearChargeReturnedScanner,
                countScannerTwoYearsEzHardTariff + countEzHardTwoYearChargeReturnedScanner, "EzHard");

        checkAC("Charge due is not correct", chargePage.compareDueCharge(userIdString, soloId, sumIOSXCharge + sumDeactivatedScannerMonthIOSXTariff + sumGeometricsCharge + sumEzHardCharge, timeRunCron), true);


        checkAC("Solo is not in defaulters", utilsForDB.checkSoloInDefaulters(soloId), true);

        chargePage.setDaysDefaulterSolo(soloId, 10);
        chargePage.runCronCheckDrivers();
        List<String> listOfStatusDevices =  utilsForDB.getIdScannersByStatus(userIdString, soloId, "4");
        String stringOfStatusesDevices = String.join(",", listOfStatusDevices);
        checkAC("Late Fee is not correct", chargePage.checkLateFeeSolo(soloId, sumIOSXCharge + sumDeactivatedScannerMonthIOSXTariff + sumGeometricsCharge + sumEzHardCharge, userIdString), true);

        chargePage.setDaysDefaulterSolo(soloId, 15);
        chargePage.runCronCheckDrivers();

        checkAC("Devices does not have status Not Paid", chargePage.checkStatusesDevices(listOfStatusDevices, "8"), true);
        checkAC("Fleet is not deactivated", utilsForDB.checkSoloIsDeactivated(soloId), true);
        String currentDueWithLateFee = utilsForDB.getCurrentDueEzFinancesSolo(soloId);
        chargePage.setDaysDefaulterSolo(soloId, 52);
        chargePage.runCronCheckDrivers();
        String currentDueWithLateReturnedProratedFee = utilsForDB.getCurrentDueEzFinancesSolo(soloId);
        int countDevicesAfter_12Month = chargePage.countScannersByTariffStart(listOfStatusDevices);
        checkAC("ProratedAndNotReturnedFee is not correct", chargePage.checkProratedAndNotReturnedFee(listOfStatusDevices, currentDueWithLateFee, currentDueWithLateReturnedProratedFee, countDevicesAfter_12Month), true);
        checkAC("Devices does not have status Not disconnected", chargePage.checkStatusesDevices(listOfStatusDevices, "12"), true);
        checkAC("Fleet is not Banned", utilsForDB.checkSoloIsBanned(soloId), true);
        utilsForDB.setCurrentCard(userIdString, soloId);
        utilsForDB.setUnbanSolo(soloId);
        utilsForDB.set_0_DeactivatedSolo(soloId);
        utilsForDB.setStatusesForDevices(stringOfStatusesDevices, "8");
        utilsForDB.setCurrentDueForSolo("0", soloId);
        chargePage.runCronCheckDrivers();

    }
    @Test
    public void makeFleetDefaulter() throws SQLException, IOException, ClassNotFoundException {
        utilsForDB.setCurrentDueForFleet(currentDue, fleetId);
        utilsForDB.setCurrentCard_0_Fleet(fleetId);
        String setPaidTillForAllTariff = chargePage.paidTillForAllTariff();
        String setTariffStartMonth = chargePage.tariffStartForMonthToMonth(countMonthForTariffStartMonthGeometrics, 5);
        String setTariffStartOneYear = chargePage.tariffStartForOneYear(2);
        String setTariffStartTwoYears = chargePage.tariffStartForTwoYears(1);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartMonth, monthGeometricsTariffId);
        utilsForDB.setOrderDateByTariffId(fleetString, fleetId, setTariffStartMonth, monthGeometricsTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearGeometricsTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsGeometricsTariffId);
        String paidTillForEzFinances = chargePage.paidTillForEzFinances();
        utilsForDB.setPaidTillEstimatedTillEzFinancesFleet(fleetId, paidTillForEzFinances, paidTillForEzFinances);

        chargePage.runCronCheckFleet();
        chargePage.setDaysDefaulterFleet(fleetId, 10);
        chargePage.runCronCheckFleet();
        chargePage.setDaysDefaulterFleet(fleetId, 15);
        chargePage.runCronCheckFleet();
        chargePage.setDaysDefaulterFleet(fleetId, 52);
        chargePage.runCronCheckFleet();
    }
    @Test
    public void makeSoloDefaulter() throws SQLException, IOException, ClassNotFoundException {
        utilsForDB.setCurrentDueForSolo(currentDue, soloId);
        utilsForDB.setCurrentCard_0_Solo(soloId);

        String setPaidTillForAllTariff = chargePage.paidTillForAllTariff();
        String setTariffStartMonth = chargePage.tariffStartForMonthToMonth(countMonthForTariffStartMonthGeometrics, 5);
        String setTariffStartOneYear = chargePage.tariffStartForOneYear(1);
        String setTariffStartTwoYears = chargePage.tariffStartForTwoYears(2);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartMonth, monthGeometricsTariffId);
        utilsForDB.setOrderDateByTariffId(userIdString, soloId, setTariffStartMonth, monthGeometricsTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearGeometricsTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsGeometricsTariffId);
        String paidTillForEzFinances = chargePage.paidTillForEzFinances();
        utilsForDB.setPaidTillEstimatedTillEzFinancesSolo(soloId, paidTillForEzFinances, paidTillForEzFinances);

        chargePage.runCronCheckDrivers();
        chargePage.setDaysDefaulterSolo(soloId, 10);
        chargePage.runCronCheckDrivers();
        chargePage.setDaysDefaulterSolo(soloId, 14);
        chargePage.runCronCheckDrivers();
        chargePage.setDaysDefaulterSolo(soloId, 15);
        chargePage.runCronCheckDrivers();
        chargePage.setDaysDefaulterSolo(soloId, 16);
        chargePage.runCronCheckDrivers();
        chargePage.setDaysDefaulterSolo(soloId, 52);
        chargePage.runCronCheckDrivers();
    }
}
