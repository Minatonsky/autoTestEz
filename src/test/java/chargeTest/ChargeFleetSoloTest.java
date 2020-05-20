package chargeTest;

import org.junit.Test;
import parentTest.ParentTestWithoutWebDriver;

import java.io.IOException;
import java.sql.SQLException;

import static libs.DataForTests.*;

public class ChargeFleetSoloTest extends ParentTestWithoutWebDriver {

    String soloId = "3470";
    String fleetId = "572";

    String unionMonthTariffId = IOSXMonthTariffId + ", " + geometricsMonthTariffId;
    String unionOneYearTariffId = oneYearIOSXTariffId + ", " + oneYearGeometricsTariffId;
    String unionTwoYearsTariffId = twoYearsIOSXTariffId + ", " + twoYearsGeometricsTariffId;

    int countMonthForTariffStartMonthIOSX = 5;
    int countMonthForTariffStartMonthGeometrics = 2;

    @Test
    public void chargeFleetTest() throws SQLException, IOException, ClassNotFoundException{

//  COUNT IOSX TARIFF
        int countScannerMonthIOSXTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, IOSXMonthTariffId);
        int countDeactivatedScannerMonthIOSXTariff = utilsForDB.countDeactivatedChargeScannersMonthToMonth(fleetString, fleetId);
        int countScannerOneYearIOSXTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, oneYearIOSXTariffId);
        int countScannerTwoYearsIOSXTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, twoYearsIOSXTariffId);


//   COUNT GEOMETRICS TARIFF
        int countScannerMonthGeometricsTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, geometricsMonthTariffId);
        int countScannerOneYearGeometricsTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, oneYearGeometricsTariffId);
        int countScannerTwoYearsGeometricsTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, twoYearsGeometricsTariffId);


// STRING TARIFF START AND PAID TILL FOR ALL
        String paidTillForAllTariffValue = chargeMethods.paidTillForAllTariff();

        String tariffStartMonthIOSXValue = chargeMethods.tariffStartForMonthToMonth(countMonthForTariffStartMonthIOSX, 2);
        String tariffStartMonthGeometricsValue = chargeMethods.tariffStartForMonthToMonth(countMonthForTariffStartMonthGeometrics, 6);

        String setTariffStartOneYear = chargeMethods.tariffStartForOneYear(1);
        String setTariffStartTwoYears = chargeMethods.tariffStartForTwoYears(2);


//        checkAC("* No all tariffs are presented on fleet",
//                chargeMethods.checkIfTariffPresent(countScannerMonthIOSXTariff, countScannerOneYearIOSXTariff, countScannerTwoYearsIOSXTariff ,
//                        countScannerMonthGeometricsTariff, countScannerOneYearGeometricsTariff, countScannerTwoYearsGeometricsTariff), true);


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

// charge by days ***************************************************************************
        String setTariffStartChargeByDaysMonthIOSX = chargeMethods.tariffStartForMonthToMonth(0, 6);
        String setTariffStartChargeByDaysMonthGeometrics = chargeMethods.tariffStartForMonthToMonth(0, 23);

        int countDevicesChargeByDaysIOSX = chargeMethods.setPaidTillChargeByDays(fleetString, fleetId, IOSXMonthTariffId, countScannerMonthIOSXTariff, paidTillForAllTariffValue, setTariffStartChargeByDaysMonthIOSX);
        int countDevicesChargeByDaysGeometrics = chargeMethods.setPaidTillChargeByDays(fleetString, fleetId, geometricsMonthTariffId, countScannerMonthGeometricsTariff, paidTillForAllTariffValue, setTariffStartChargeByDaysMonthGeometrics);


//  SET PAID TILL FOR USER FINANCES

        String paidTillForEzFinances = chargeMethods.paidTillForEzFinances();
        utilsForDB.setPaidTillEstimatedTillEzFinancesFleet(fleetId, paidTillForEzFinances, paidTillForEzFinances);

        String timeRunCron = chargeMethods.runCronCheckFleet();
        checkAC("DateTime dues are not correct", chargeMethods.checkDateTimeDue(carrierIdString, fleetId, timeRunCron), true);


        double sumDeactivatedScannerMonthIOSXTariff = chargeMethods.sumDeactivatedScannerMonthToMonthTariff(fleetString, fleetId, countDeactivatedScannerMonthIOSXTariff);
        double sumIOSXCharge = chargeMethods.sumCharge((countScannerMonthIOSXTariff) - countDevicesChargeByDaysIOSX, countScannerOneYearIOSXTariff, countScannerTwoYearsIOSXTariff, "IOSX");
        double sumGeometricsCharge = chargeMethods.sumCharge((countScannerMonthGeometricsTariff) - countDevicesChargeByDaysGeometrics, countScannerOneYearGeometricsTariff, countScannerTwoYearsGeometricsTariff, "Geometrics");

        double sumIOSXChargeByDays = chargeMethods.sumChargeMonthToMonthTariffByDays(setTariffStartChargeByDaysMonthIOSX, countDevicesChargeByDaysIOSX);
        double sumGeometricsChargeByDays = chargeMethods.sumChargeMonthToMonthTariffByDays(setTariffStartChargeByDaysMonthGeometrics, countDevicesChargeByDaysGeometrics);
        checkAC("Charge due is not correct", chargeMethods.compareDueCharge(carrierIdString, fleetId, sumIOSXCharge + sumDeactivatedScannerMonthIOSXTariff + sumGeometricsCharge + sumIOSXChargeByDays + sumGeometricsChargeByDays, timeRunCron), true);

        checkAC("PaidTill for month-to-month is not correct", chargeMethods.comparePaidTillMonthToMonth(fleetString, fleetId, unionMonthTariffId), true);
        checkAC("PaidTill for one year is not correct", chargeMethods.comparePaidTillOneYear(fleetString, fleetId, unionOneYearTariffId), true);
        checkAC("PaidTill for two years is not correct", chargeMethods.comparePaidTillTwoYears(fleetString, fleetId, unionTwoYearsTariffId), true);
        checkAC("EstimatedTill in ez_finance is not correct", chargeMethods.compareEstimatedTillFleet(fleetId), true);
        checkAC("PaidTill in ez_finance is not correct", chargeMethods.comparePaidTillFleet(fleetId), true);

    }

    @Test
    public  void chargeSoloTest() throws SQLException, IOException, ClassNotFoundException{

//  COUNT IOSX TARIFF
        int countScannerMonthIOSXTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, IOSXMonthTariffId);
        int countScannerOneYearIOSXTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, oneYearIOSXTariffId);
        int countScannerTwoYearsIOSXTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, twoYearsIOSXTariffId);

        int countDeactivatedScannerMonthIOSXTariff = utilsForDB.countDeactivatedChargeScannersMonthToMonth(userIdString, soloId);


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
                chargeMethods.checkIfTariffPresent(countScannerMonthIOSXTariff, countScannerOneYearIOSXTariff, countScannerTwoYearsIOSXTariff ,
                        countScannerMonthGeometricsTariff, countScannerOneYearGeometricsTariff, countScannerTwoYearsGeometricsTariff), true);


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


// charge by days ***************************************************************************
        String tariffStartChargeByDaysMonthIOSXValue = chargeMethods.tariffStartForMonthToMonth(0, 10);
        String tariffStartChargeByDaysMonthGeometricsValue = chargeMethods.tariffStartForMonthToMonth(0, 8);

        int countDevicesChargeByDaysIOSX = chargeMethods.setPaidTillChargeByDays(userIdString, soloId, IOSXMonthTariffId, countScannerMonthIOSXTariff, paidTillForAllTariffValue, tariffStartChargeByDaysMonthIOSXValue);
        int countDevicesChargeByDaysGeometrics = chargeMethods.setPaidTillChargeByDays(userIdString, soloId, geometricsMonthTariffId, countScannerMonthGeometricsTariff, paidTillForAllTariffValue, tariffStartChargeByDaysMonthGeometricsValue);


//  SET PAID TILL FOR USER FINANCES
        String paidTillForEzFinances = chargeMethods.paidTillForEzFinances();
        utilsForDB.setPaidTillEstimatedTillEzFinancesSolo(soloId, paidTillForEzFinances, paidTillForEzFinances);
        String timeRunCron = chargeMethods.runCronCheckDrivers();
        checkAC("DateTime dues are not correct", chargeMethods.checkDateTimeDue(userIdString, soloId, timeRunCron), true);

        double sumDeactivatedScannerMonthIOSXTariff = chargeMethods.sumDeactivatedScannerMonthToMonthTariff(userIdString, soloId, countDeactivatedScannerMonthIOSXTariff);
        double sumIOSXCharge = chargeMethods.sumCharge(
                (countScannerMonthIOSXTariff) - countDevicesChargeByDaysIOSX, countScannerOneYearIOSXTariff, countScannerTwoYearsIOSXTariff, "IOSX");
        double sumGeometricsCharge = chargeMethods.sumCharge(
                (countScannerMonthGeometricsTariff) - countDevicesChargeByDaysGeometrics, countScannerOneYearGeometricsTariff, countScannerTwoYearsGeometricsTariff, "Geometrics");

        double sumIOSXChargeByDays = chargeMethods.sumChargeMonthToMonthTariffByDays(tariffStartChargeByDaysMonthIOSXValue, countDevicesChargeByDaysIOSX);
        double sumGeometricsChargeByDays = chargeMethods.sumChargeMonthToMonthTariffByDays(tariffStartChargeByDaysMonthGeometricsValue, countDevicesChargeByDaysGeometrics);

        checkAC("Charge due is not correct",
                chargeMethods.compareDueCharge(userIdString, soloId, sumIOSXCharge + sumDeactivatedScannerMonthIOSXTariff + sumGeometricsCharge + sumIOSXChargeByDays + sumGeometricsChargeByDays, timeRunCron), true);

        checkAC("PaidTill for month-to-month is not correct", chargeMethods.comparePaidTillMonthToMonth(userIdString, soloId, unionMonthTariffId), true);
        checkAC("PaidTill for one year is not correct", chargeMethods.comparePaidTillOneYear(userIdString, soloId, oneYearIOSXTariffId), true);
        checkAC("PaidTill for two years is not correct", chargeMethods.comparePaidTillTwoYears(userIdString, soloId, twoYearsIOSXTariffId), true);
        checkAC("EstimatedTill in eld_personal_finances is not correct", chargeMethods.compareEstimatedTillSolo(soloId), true);
        checkAC("PaidTill in eld_personal_finances is not correct", chargeMethods.comparePaidTillSolo(soloId), true);

    }

}
