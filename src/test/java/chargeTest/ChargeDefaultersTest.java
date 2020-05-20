package chargeTest;

import org.junit.Ignore;
import org.junit.Test;
import parentTest.ParentTestWithoutWebDriver;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static libs.DataForTests.*;

public class ChargeDefaultersTest extends ParentTestWithoutWebDriver {

    String soloId = "3470";
    String fleetId = "572";

    int countMonthForTariffStartMonthIOSX = 3;
    int countMonthForTariffStartMonthGeometrics = 2;

    String currentDue = "0";


    @Test
    public void chargeDefaultersFleetTest() throws SQLException, IOException, ClassNotFoundException {
        try {
            utilsForDB.setCurrentDueForFleet(currentDue, fleetId);
            utilsForDB.setCurrentCard_0_Fleet(fleetId);

//  COUNT IOSX TARIFF
            int countScannerMonthIOSXTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, IOSXMonthTariffId);
            int countDeactivatedScannerMonthIOSXTariff = utilsForDB.countDeactivatedChargeScannersMonthToMonth(fleetString, fleetId);
            int countScannerOneYearIOSXTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, oneYearIOSXTariffId);
            int countScannerTwoYearsIOSXTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, twoYearsIOSXTariffId);

//   COUNT GEOMETRICS TARIFF
            int countScannerMonthGeometricsTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, geometricsMonthTariffId);
            int countScannerOneYearGeometricsTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, oneYearGeometricsTariffId);
            int countScannerTwoYearsGeometricsTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, twoYearsGeometricsTariffId);

            String paidTillForAllTariffValue = chargeMethods.paidTillForAllTariff();

            String tariffStartMonthIOSXValue = chargeMethods.tariffStartForMonthToMonth(countMonthForTariffStartMonthIOSX, 2);
            String tariffStartMonthGeometricsValue = chargeMethods.tariffStartForMonthToMonth(countMonthForTariffStartMonthGeometrics, 6);

            String setTariffStartOneYear = chargeMethods.tariffStartForOneYear(1);
            String setTariffStartTwoYears = chargeMethods.tariffStartForTwoYears(2);


            checkAC("* No all tariffs are presented on fleet",
                    chargeMethods.checkIfTariffPresent(countScannerMonthIOSXTariff, countScannerOneYearIOSXTariff, countScannerTwoYearsIOSXTariff,
                            countScannerMonthGeometricsTariff, countScannerOneYearGeometricsTariff, countScannerTwoYearsGeometricsTariff), true);


//  SET PAID TILL AND ORDER DATE FOR IOSX TARIFF

            chargeMethods.setPaidTillAndTariffStartScannerForFleet(fleetId, paidTillForAllTariffValue, tariffStartMonthIOSXValue, IOSXMonthTariffId, countScannerMonthIOSXTariff);
            chargeMethods.setPaidTillAndTariffStartScannerForFleet(fleetId, paidTillForAllTariffValue, setTariffStartOneYear, oneYearIOSXTariffId, countScannerOneYearIOSXTariff);
            chargeMethods.setPaidTillAndTariffStartScannerForFleet(fleetId, paidTillForAllTariffValue, setTariffStartTwoYears, twoYearsIOSXTariffId, countScannerTwoYearsIOSXTariff);
            chargeMethods.setOrderDateByTariffId(fleetString, fleetId, tariffStartMonthIOSXValue, IOSXMonthTariffId, countScannerMonthIOSXTariff);


//  SET PAID TILL AND ORDER DATE FOR GEOMETRICS TARIFF
            chargeMethods.setPaidTillAndTariffStartScannerForFleet(fleetId, paidTillForAllTariffValue, tariffStartMonthGeometricsValue, geometricsMonthTariffId, countScannerMonthGeometricsTariff);
            chargeMethods.setPaidTillAndTariffStartScannerForFleet(fleetId, paidTillForAllTariffValue, setTariffStartOneYear, oneYearGeometricsTariffId, countScannerOneYearGeometricsTariff);
            chargeMethods.setPaidTillAndTariffStartScannerForFleet(fleetId, paidTillForAllTariffValue, setTariffStartTwoYears, twoYearsGeometricsTariffId, countScannerTwoYearsGeometricsTariff);
            chargeMethods.setOrderDateByTariffId(fleetString, fleetId, tariffStartMonthGeometricsValue, geometricsMonthTariffId, countScannerMonthGeometricsTariff);


//  SET PAID TILL FOR USER FINANCES

            String paidTillForEzFinances = chargeMethods.paidTillForEzFinances();
            utilsForDB.setPaidTillEstimatedTillEzFinancesFleet(fleetId, paidTillForEzFinances, paidTillForEzFinances);
            String timeRunCron = chargeMethods.runCronCheckFleet();
            checkAC("DateTime dues are not correct", chargeMethods.checkDateTimeDue(carrierIdString, fleetId, timeRunCron), true);

            double sumDeactivatedScannerMonthIOSXTariff = chargeMethods.sumDeactivatedScannerMonthToMonthTariff(fleetString, fleetId, countDeactivatedScannerMonthIOSXTariff);
            double sumIOSXCharge = chargeMethods.sumCharge(countScannerMonthIOSXTariff, countScannerOneYearIOSXTariff, countScannerTwoYearsIOSXTariff, "IOSX");
            double sumGeometricsCharge = chargeMethods.sumCharge(countScannerMonthGeometricsTariff, countScannerOneYearGeometricsTariff, countScannerTwoYearsGeometricsTariff, "Geometrics");

            checkAC("Charge due is not correct", chargeMethods.compareDueCharge(carrierIdString, fleetId, sumIOSXCharge + sumDeactivatedScannerMonthIOSXTariff + sumGeometricsCharge, timeRunCron), true);

            checkAC("Fleet is not in defaulters", utilsForDB.checkFleetInDefaulters(fleetId), true);

            chargeMethods.setDaysDefaulterFleet(fleetId, 10);
            chargeMethods.runCronCheckFleet();
            List<String> listOfStatusDevices = utilsForDB.getIdScannersByStatus(fleetString, fleetId, "4");
            String stringOfStatusesDevices = String.join(",", listOfStatusDevices);
            checkAC("Late Fee is not correct", chargeMethods.checkLateFeeFleet(fleetId, sumIOSXCharge + sumDeactivatedScannerMonthIOSXTariff + sumGeometricsCharge, fleetString), true);

            chargeMethods.setDaysDefaulterFleet(fleetId, 15);
            chargeMethods.runCronCheckFleet();
            checkAC("Devices does not have status Not Paid", chargeMethods.checkStatusesDevices(listOfStatusDevices, "8"), true);
            checkAC("Fleet is not deactivated", utilsForDB.checkFleetIsDeactivated(fleetId), true);
            String currentDueWithLateFee = utilsForDB.getCurrentDueEzFinancesFleet(fleetId);
            chargeMethods.setDaysDefaulterFleet(fleetId, 52);
            chargeMethods.runCronCheckFleet();
            String currentDueWithLateReturnedProratedFee = utilsForDB.getCurrentDueEzFinancesFleet(fleetId);
            int countDevicesAfter_12Month = chargeMethods.countScannersByTariffStart(listOfStatusDevices);
            checkAC("ProratedAndNotReturnedFee is not correct", chargeMethods.checkProratedAndNotReturnedFee(listOfStatusDevices, currentDueWithLateFee, currentDueWithLateReturnedProratedFee, countDevicesAfter_12Month), true);
            checkAC("Devices does not have status Not disconnected", chargeMethods.checkStatusesDevices(listOfStatusDevices, "12"), true);
            checkAC("Fleet is not Banned", utilsForDB.checkFleetIsBanned(fleetId), true);
            utilsForDB.setCurrentCard(carrierIdString, fleetId);
            utilsForDB.setUnbanFleet(fleetId);
            utilsForDB.set_0_DeactivatedFleet(fleetId);
            utilsForDB.setStatusesForDevices(stringOfStatusesDevices, "8");


        } finally {
            utilsForDB.setCurrentCard(carrierIdString, fleetId);
            utilsForDB.setCurrentDueForFleet("0", fleetId);
            chargeMethods.runCronCheckFleet();
        }

    }
    @Test
    public void chargeDefaultersSoloTest() throws SQLException, IOException, ClassNotFoundException {
        try {
            utilsForDB.setCurrentDueForSolo(currentDue, soloId);
            utilsForDB.setCurrentCard_0_Solo(soloId);

//   COUNT IOSX TARIFF
            int countScannerMonthIOSXTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, IOSXMonthTariffId);
            int countDeactivatedScannerMonthIOSXTariff = utilsForDB.countDeactivatedChargeScannersMonthToMonth(userIdString, soloId);
            int countScannerOneYearIOSXTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, oneYearIOSXTariffId);
            int countScannerTwoYearsIOSXTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, twoYearsIOSXTariffId);

//   COUNT GEOMETRICS TARIFF
            int countScannerMonthGeometricsTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, geometricsMonthTariffId);
            int countScannerOneYearGeometricsTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, oneYearGeometricsTariffId);
            int countScannerTwoYearsGeometricsTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, twoYearsGeometricsTariffId);

            String paidTillForAllTariffValue = chargeMethods.paidTillForAllTariff();
            String tariffStartMonthIOSXValue = chargeMethods.tariffStartForMonthToMonth(countMonthForTariffStartMonthIOSX, 3);
            String tariffStartMonthGeometricsValue = chargeMethods.tariffStartForMonthToMonth(countMonthForTariffStartMonthGeometrics, 5);

            String tariffStartOneYearValue = chargeMethods.tariffStartForOneYear(1);
            String tariffStartTwoYearsValue = chargeMethods.tariffStartForTwoYears(2);

            checkAC("No all tariffs are presented in eld scanners",
                    chargeMethods.checkIfTariffPresent(countScannerMonthIOSXTariff, countScannerOneYearIOSXTariff, countScannerTwoYearsIOSXTariff, countScannerMonthGeometricsTariff, countScannerOneYearGeometricsTariff, countScannerTwoYearsGeometricsTariff), true);


//  SET PAID TILL AND ORDER DATE FOR IOSX TARIFF
            chargeMethods.setPaidTillAndTariffStartScannerForSolo(soloId, paidTillForAllTariffValue, tariffStartMonthIOSXValue,IOSXMonthTariffId, countScannerMonthIOSXTariff);
            chargeMethods.setPaidTillAndTariffStartScannerForSolo(soloId, paidTillForAllTariffValue, tariffStartOneYearValue,oneYearIOSXTariffId, countScannerOneYearIOSXTariff);
            chargeMethods.setPaidTillAndTariffStartScannerForSolo(soloId, paidTillForAllTariffValue, tariffStartTwoYearsValue,twoYearsIOSXTariffId, countScannerTwoYearsIOSXTariff);
            chargeMethods.setOrderDateByTariffId(userIdString, soloId, tariffStartMonthIOSXValue, IOSXMonthTariffId, countScannerMonthIOSXTariff);

//  SET PAID TILL AND ORDER DATE FOR GEOMETRICS TARIFF

            chargeMethods.setPaidTillAndTariffStartScannerForSolo(soloId, paidTillForAllTariffValue, tariffStartMonthGeometricsValue,geometricsMonthTariffId, countScannerMonthGeometricsTariff);
            chargeMethods.setPaidTillAndTariffStartScannerForSolo(soloId, paidTillForAllTariffValue, tariffStartOneYearValue,oneYearGeometricsTariffId, countScannerOneYearGeometricsTariff);
            chargeMethods.setPaidTillAndTariffStartScannerForSolo(soloId, paidTillForAllTariffValue, tariffStartTwoYearsValue,twoYearsGeometricsTariffId, countScannerTwoYearsGeometricsTariff);
            chargeMethods.setOrderDateByTariffId(userIdString, soloId, tariffStartMonthGeometricsValue, geometricsMonthTariffId, countScannerMonthGeometricsTariff);



//  SET PAID TILL FOR USER FINANCES
            String paidTillForEzFinances = chargeMethods.paidTillForEzFinances();
            utilsForDB.setPaidTillEstimatedTillEzFinancesSolo(soloId, paidTillForEzFinances, paidTillForEzFinances);
            String timeRunCron = chargeMethods.runCronCheckDrivers();
            checkAC("DateTime dues are not correct", chargeMethods.checkDateTimeDue(userIdString, soloId, timeRunCron), true);

            double sumDeactivatedScannerMonthIOSXTariff = chargeMethods.sumDeactivatedScannerMonthToMonthTariff(userIdString, soloId, countDeactivatedScannerMonthIOSXTariff);
            double sumIOSXCharge = chargeMethods.sumCharge(countScannerMonthIOSXTariff, countScannerOneYearIOSXTariff, countScannerTwoYearsIOSXTariff, "IOSX");
            double sumGeometricsCharge = chargeMethods.sumCharge(countScannerMonthGeometricsTariff, countScannerOneYearGeometricsTariff, countScannerTwoYearsGeometricsTariff, "Geometrics");

            checkAC("Charge due is not correct", chargeMethods.compareDueCharge(userIdString, soloId, sumIOSXCharge + sumDeactivatedScannerMonthIOSXTariff + sumGeometricsCharge, timeRunCron), true);
            checkAC("Solo is not in defaulters", utilsForDB.checkSoloInDefaulters(soloId), true);

            chargeMethods.setDaysDefaulterSolo(soloId, 10);
            chargeMethods.runCronCheckDrivers();
            List<String> listOfStatusDevices = utilsForDB.getIdScannersByStatus(userIdString, soloId, "4");
            String stringOfStatusesDevices = String.join(",", listOfStatusDevices);
            checkAC("Late Fee is not correct", chargeMethods.checkLateFeeSolo(soloId, sumIOSXCharge + sumDeactivatedScannerMonthIOSXTariff + sumGeometricsCharge, userIdString), true);

            chargeMethods.setDaysDefaulterSolo(soloId, 15);
            chargeMethods.runCronCheckDrivers();

            checkAC("Devices does not have status Not Paid", chargeMethods.checkStatusesDevices(listOfStatusDevices, "8"), true);
            checkAC("Fleet is not deactivated", utilsForDB.checkSoloIsDeactivated(soloId), true);
            String currentDueWithLateFee = utilsForDB.getCurrentDueEzFinancesSolo(soloId);
            chargeMethods.setDaysDefaulterSolo(soloId, 52);
            chargeMethods.runCronCheckDrivers();
            String currentDueWithLateReturnedProratedFee = utilsForDB.getCurrentDueEzFinancesSolo(soloId);
            int countDevicesAfter_12Month = chargeMethods.countScannersByTariffStart(listOfStatusDevices);
            checkAC("ProratedAndNotReturnedFee is not correct", chargeMethods.checkProratedAndNotReturnedFee(listOfStatusDevices, currentDueWithLateFee, currentDueWithLateReturnedProratedFee, countDevicesAfter_12Month), true);
            checkAC("Devices does not have status Not disconnected", chargeMethods.checkStatusesDevices(listOfStatusDevices, "12"), true);
            checkAC("Fleet is not Banned", utilsForDB.checkSoloIsBanned(soloId), true);
            utilsForDB.setCurrentCard(userIdString, soloId);
            utilsForDB.setUnbanSolo(soloId);
            utilsForDB.set_0_DeactivatedSolo(soloId);
            utilsForDB.setStatusesForDevices(stringOfStatusesDevices, "8");
            utilsForDB.setCurrentDueForSolo("0", soloId);
            chargeMethods.runCronCheckDrivers();
        } finally {
            utilsForDB.setCurrentCard(carrierIdString, fleetId);
        }

    }
    @Test
    @Ignore
    public void makeFleetDefaulter() throws SQLException, IOException, ClassNotFoundException {
        utilsForDB.setCurrentDueForFleet(currentDue, fleetId);
        utilsForDB.setCurrentCard_0_Fleet(fleetId);
        String setPaidTillForAllTariff = chargeMethods.paidTillForAllTariff();
        String setTariffStartMonth = chargeMethods.tariffStartForMonthToMonth(countMonthForTariffStartMonthGeometrics, 5);
        String setTariffStartOneYear = chargeMethods.tariffStartForOneYear(2);
        String setTariffStartTwoYears = chargeMethods.tariffStartForTwoYears(1);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartMonth, geometricsMonthTariffId);
        utilsForDB.setOrderDateByTariffId(fleetString, fleetId, setTariffStartMonth, geometricsMonthTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearGeometricsTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsGeometricsTariffId);
        String paidTillForEzFinances = chargeMethods.paidTillForEzFinances();
        utilsForDB.setPaidTillEstimatedTillEzFinancesFleet(fleetId, paidTillForEzFinances, paidTillForEzFinances);

        chargeMethods.runCronCheckFleet();
        chargeMethods.setDaysDefaulterFleet(fleetId, 10);
        chargeMethods.runCronCheckFleet();
        chargeMethods.setDaysDefaulterFleet(fleetId, 15);
        chargeMethods.runCronCheckFleet();
        chargeMethods.setDaysDefaulterFleet(fleetId, 52);
        chargeMethods.runCronCheckFleet();
    }
    @Test
    @Ignore
    public void makeSoloDefaulter() throws SQLException, IOException, ClassNotFoundException {
        utilsForDB.setCurrentDueForSolo(currentDue, soloId);
        utilsForDB.setCurrentCard_0_Solo(soloId);

        String setPaidTillForAllTariff = chargeMethods.paidTillForAllTariff();
        String setTariffStartMonth = chargeMethods.tariffStartForMonthToMonth(countMonthForTariffStartMonthGeometrics, 5);
        String setTariffStartOneYear = chargeMethods.tariffStartForOneYear(1);
        String setTariffStartTwoYears = chargeMethods.tariffStartForTwoYears(2);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartMonth, geometricsMonthTariffId);
        utilsForDB.setOrderDateByTariffId(userIdString, soloId, setTariffStartMonth, geometricsMonthTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearGeometricsTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsGeometricsTariffId);
        String paidTillForEzFinances = chargeMethods.paidTillForEzFinances();
        utilsForDB.setPaidTillEstimatedTillEzFinancesSolo(soloId, paidTillForEzFinances, paidTillForEzFinances);

        chargeMethods.runCronCheckDrivers();
        chargeMethods.setDaysDefaulterSolo(soloId, 10);
        chargeMethods.runCronCheckDrivers();
        chargeMethods.setDaysDefaulterSolo(soloId, 14);
        chargeMethods.runCronCheckDrivers();
        chargeMethods.setDaysDefaulterSolo(soloId, 15);
        chargeMethods.runCronCheckDrivers();
        chargeMethods.setDaysDefaulterSolo(soloId, 16);
        chargeMethods.runCronCheckDrivers();
        chargeMethods.setDaysDefaulterSolo(soloId, 52);
        chargeMethods.runCronCheckDrivers();
    }
}
