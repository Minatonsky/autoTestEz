package chargeTest;

import org.junit.Test;
import parentTest.ParentTestWithoutWebDriver;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static libs.DataForTests.*;

public class ChargeFleetSoloTest extends ParentTestWithoutWebDriver {

    String soloId = "4401";
    String fleetId = "518";

    String unionMonthTariffId = IOSXMonthTariffId + ", " + geometricsMonthTariffId;
    String unionOneYearTariffId = oneYearIOSXTariffId + ", " + oneYearGeometricsTariffId;
    String unionTwoYearsTariffId = twoYearsIOSXTariffId + ", " + twoYearsGeometricsTariffId;

    int countMonthForTariffStartMonthIOSX = 5;
    int countDevicesChargeByDaysIOSX = 1;

    int countMonthForTariffStartMonthGeometrics = 3;
    int countDevicesChargeByDaysGeometrics = 1;

    int countMonthForTariffStartMonthEzHard = 3;
    int countDevicesChargeByDaysEzHard = 1;

    @Test
    public void chargeFleetTest() throws SQLException, IOException, ClassNotFoundException{

//  COUNT IOSX TARIFF
        int countScannerMonthIOSXTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, IOSXMonthTariffId);
        int countDeactivatedScannerMonthIOSXTariff = utilsForDB.countDeactivatedChargeScannersMonthToMonth(fleetString, fleetId);
        int countScannerOneYearIOSXTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, oneYearIOSXTariffId);
        int countScannerTwoYearsIOSXTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, twoYearsIOSXTariffId);
        int countScannerMonthIOSXChargeReturned = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, IOSXMonthTariffId);
        int countScannerOneYearIOSXChargeReturned = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, oneYearIOSXTariffId);
        int countScannerTwoYearIOSXChargeReturned = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, twoYearsIOSXTariffId);


//   COUNT GEOMETRICS TARIFF
        int countScannerMonthGeometricsTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, geometricsMonthTariffId);
        int countScannerOneYearGeometricsTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, oneYearGeometricsTariffId);
        int countScannerTwoYearsGeometricsTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, twoYearsGeometricsTariffId);
        int countGeometricsMonthChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, geometricsMonthTariffId);
        int countGeometricsOneYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, oneYearGeometricsTariffId);
        int countGeometricsTwoYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, twoYearsGeometricsTariffId);

//   COUNT EZ HARD TARIFF
        int countScannerMonthEzHardTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, monthEzHardTariffId);
        int countScannerOneYearEzHardTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, oneYearEzHardTariffId);
        int countScannerTwoYearsEzHardTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, twoYearsEzHardTariffId);
        int countEzHardMonthChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, monthEzHardTariffId);
        int countEzHardOneYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, oneYearEzHardTariffId);
        int countEzHardTwoYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, twoYearsEzHardTariffId);




// STRING TARIFF START AND PAID TILL FOR ALL
        String setPaidTillForAllTariff = chargeMethods.paidTillForAllTariff();

        String setTariffStartMonthIOSX = chargeMethods.tariffStartForMonthToMonth(countMonthForTariffStartMonthIOSX, 2);
        String setTariffStartMonthGeometrics = chargeMethods.tariffStartForMonthToMonth(countMonthForTariffStartMonthGeometrics, 6);
        String setTariffStartMonthEzHard = chargeMethods.tariffStartForMonthToMonth(countMonthForTariffStartMonthEzHard, 3);

        String setTariffStartOneYear = chargeMethods.tariffStartForOneYear(1);
        String setTariffStartTwoYears = chargeMethods.tariffStartForTwoYears(2);


        checkAC("* No all tariffs are presented on fleet",
                chargeMethods.checkIfTariffPresent(
                        countScannerMonthIOSXTariff, countScannerOneYearIOSXTariff, countScannerTwoYearsIOSXTariff ,
                        countScannerMonthGeometricsTariff, countScannerOneYearGeometricsTariff, countScannerTwoYearsGeometricsTariff,
                        countScannerMonthEzHardTariff, countScannerOneYearEzHardTariff, countScannerTwoYearsEzHardTariff
                        ), true);

        chargeMethods.informationOfDeactivatedAndReturnedScanners(countDeactivatedScannerMonthIOSXTariff, countScannerMonthIOSXChargeReturned, countScannerOneYearIOSXChargeReturned, countScannerTwoYearIOSXChargeReturned,
                countGeometricsMonthChargeReturnedScanner, countGeometricsOneYearChargeReturnedScanner, countGeometricsTwoYearChargeReturnedScanner,
                countEzHardMonthChargeReturnedScanner, countEzHardOneYearChargeReturnedScanner, countEzHardTwoYearChargeReturnedScanner);

//  SET PAID TILL AND ORDER DATE FOR IOSX TARIFF

        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartMonthIOSX, IOSXMonthTariffId);
        utilsForDB.setOrderDateByTariffId(fleetString, fleetId, setTariffStartMonthIOSX, IOSXMonthTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearIOSXTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsIOSXTariffId);


//  SET PAID TILL AND ORDER DATE FOR GEOMETRICS TARIFF

        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartMonthGeometrics, geometricsMonthTariffId);
        utilsForDB.setOrderDateByTariffId(fleetString, fleetId, setTariffStartMonthGeometrics, geometricsMonthTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearGeometricsTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsGeometricsTariffId);

//  SET PAID TILL AND ORDER DATE FOR EZ HARD TARIFF

        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartMonthGeometrics, monthEzHardTariffId);
        utilsForDB.setOrderDateByTariffId(fleetString, fleetId, setTariffStartMonthEzHard, monthEzHardTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearEzHardTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsEzHardTariffId);

// charge by days ***************************************************************************
        String setTariffStartChargeByDaysMonthIOSX = chargeMethods.tariffStartForMonthToMonth(0, 6);
        String setTariffStartChargeByDaysMonthGeometrics = chargeMethods.tariffStartForMonthToMonth(0, 23);
        String setTariffStartChargeByDaysMonthEzHard = chargeMethods.tariffStartForMonthToMonth(0, 23);

        List<String> tempIdDeviceIOSXChargeByDays = utilsForDB.getIdChargeScannersByTariff(fleetString, fleetId, IOSXMonthTariffId, countDevicesChargeByDaysIOSX);
        String idDeviceIOSXChargeByDays = String.join(", ", tempIdDeviceIOSXChargeByDays);

        List<String> tempIdDeviceGeometricsChargeByDays = utilsForDB.getIdChargeScannersByTariff(fleetString, fleetId, geometricsMonthTariffId, countDevicesChargeByDaysGeometrics);
        String idDeviceGeometricsChargeByDays = String.join(", ", tempIdDeviceGeometricsChargeByDays);

        List<String> tempIdDeviceEzHardChargeByDays = utilsForDB.getIdChargeScannersByTariff(fleetString, fleetId, monthEzHardTariffId, countDevicesChargeByDaysEzHard);
        String idDeviceEzHardChargeByDays = String.join(", ", tempIdDeviceEzHardChargeByDays);

        utilsForDB.setPaidTillAndTariffStartScannerById(setPaidTillForAllTariff, setTariffStartChargeByDaysMonthIOSX, idDeviceIOSXChargeByDays);
        utilsForDB.setPaidTillAndTariffStartScannerById(setPaidTillForAllTariff, setTariffStartChargeByDaysMonthGeometrics, idDeviceGeometricsChargeByDays);
        utilsForDB.setPaidTillAndTariffStartScannerById(setPaidTillForAllTariff, setTariffStartChargeByDaysMonthEzHard, idDeviceEzHardChargeByDays);

        utilsForDB.delete_102_StatusByIdDevice(idDeviceIOSXChargeByDays);
        utilsForDB.delete_102_StatusByIdDevice(idDeviceGeometricsChargeByDays);
        utilsForDB.delete_102_StatusByIdDevice(idDeviceEzHardChargeByDays);

        utilsForDB.setOrderDateByDeviceId(setTariffStartChargeByDaysMonthIOSX, idDeviceIOSXChargeByDays);
        utilsForDB.setOrderDateByDeviceId(setTariffStartChargeByDaysMonthGeometrics, idDeviceGeometricsChargeByDays);
        utilsForDB.setOrderDateByDeviceId(setTariffStartChargeByDaysMonthEzHard, idDeviceEzHardChargeByDays);

        utilsForDB.setDateTimeEldHistoryByIdDevice(setTariffStartChargeByDaysMonthIOSX, idDeviceIOSXChargeByDays);
        utilsForDB.setDateTimeEldHistoryByIdDevice(setTariffStartChargeByDaysMonthGeometrics, idDeviceGeometricsChargeByDays);
        utilsForDB.setDateTimeEldHistoryByIdDevice(setTariffStartChargeByDaysMonthEzHard, idDeviceEzHardChargeByDays);

//  SET PAID TILL FOR USER FINANCES

        String paidTillForEzFinances = chargeMethods.paidTillForEzFinances();
        utilsForDB.setPaidTillEstimatedTillEzFinancesFleet(fleetId, paidTillForEzFinances, paidTillForEzFinances);

        String timeRunCron = chargeMethods.runCronCheckFleet();
        checkAC("DateTime dues are not correct", chargeMethods.checkDateTimeDue(carrierIdString, fleetId, timeRunCron), true);


        double sumDeactivatedScannerMonthIOSXTariff = chargeMethods.sumDeactivatedScannerMonthToMonthTariff(fleetString, fleetId, countDeactivatedScannerMonthIOSXTariff);
        double sumIOSXCharge = chargeMethods.sumCharge(
                (countScannerMonthIOSXTariff + countScannerMonthIOSXChargeReturned) - countDevicesChargeByDaysIOSX ,
                countScannerOneYearIOSXTariff + countScannerOneYearIOSXChargeReturned,
                countScannerTwoYearsIOSXTariff + countScannerTwoYearIOSXChargeReturned, "IOSX");
        double sumGeometricsCharge = chargeMethods.sumCharge(
                (countScannerMonthGeometricsTariff + countGeometricsMonthChargeReturnedScanner) - countDevicesChargeByDaysGeometrics,
                countScannerOneYearGeometricsTariff + countGeometricsOneYearChargeReturnedScanner,
                countScannerTwoYearsGeometricsTariff + countGeometricsTwoYearChargeReturnedScanner, "Geometrics");
        double sumEzHardCharge = chargeMethods.sumCharge(
                (countScannerMonthEzHardTariff + countEzHardMonthChargeReturnedScanner) - countDevicesChargeByDaysEzHard,
                countScannerOneYearEzHardTariff + countEzHardOneYearChargeReturnedScanner,
                countScannerTwoYearsEzHardTariff + countEzHardTwoYearChargeReturnedScanner, "EzHard");
        double sumIOSXChargeByDays = chargeMethods.sumChargeMonthToMonthTariffByDays(setTariffStartChargeByDaysMonthIOSX, countDevicesChargeByDaysIOSX);
        double sumGeometricsChargeByDays = chargeMethods.sumChargeMonthToMonthTariffByDays(setTariffStartChargeByDaysMonthGeometrics, countDevicesChargeByDaysGeometrics);
        double sumEzHardChargeByDays = chargeMethods.sumChargeMonthToMonthTariffByDays(setTariffStartChargeByDaysMonthEzHard, countDevicesChargeByDaysEzHard);
        checkAC("Charge due is not correct",
                chargeMethods.compareDueCharge(carrierIdString, fleetId,
                        sumIOSXCharge + sumDeactivatedScannerMonthIOSXTariff + sumGeometricsCharge + sumIOSXChargeByDays + sumGeometricsChargeByDays + sumEzHardCharge + sumEzHardChargeByDays, timeRunCron), true);

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
        int countDeactivatedScannerMonthIOSXTariff = utilsForDB.countDeactivatedChargeScannersMonthToMonth(userIdString, soloId);
        int countScannerOneYearIOSXTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, oneYearIOSXTariffId);
        int countScannerTwoYearsIOSXTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, twoYearsIOSXTariffId);

        int countScannerMonthIOSXChargeReturned = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, IOSXMonthTariffId);
        int countScannerOneYearIOSXChargeReturned = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, oneYearIOSXTariffId);
        int countScannerTwoYearIOSXChargeReturned = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, twoYearsIOSXTariffId);

//   COUNT GEOMETRICS TARIFF
        int countScannerMonthGeometricsTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, geometricsMonthTariffId);
        int countScannerOneYearGeometricsTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, oneYearGeometricsTariffId);
        int countScannerTwoYearsGeometricsTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, twoYearsGeometricsTariffId);
        int countGeometricsMonthChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, geometricsMonthTariffId);
        int countGeometricsOneYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, oneYearGeometricsTariffId);
        int countGeometricsTwoYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, twoYearsGeometricsTariffId);

//   COUNT EZ HARD TARIFF
        int countScannerMonthEzHardTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, monthEzHardTariffId);
        int countScannerOneYearEzHardTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, oneYearEzHardTariffId);
        int countScannerTwoYearsEzHardTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, twoYearsEzHardTariffId);
        int countEzHardMonthChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, monthEzHardTariffId);
        int countEzHardOneYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, oneYearEzHardTariffId);
        int countEzHardTwoYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, twoYearsEzHardTariffId);


        String setPaidTillForAllTariff = chargeMethods.paidTillForAllTariff();

        String setTariffStartMonthIOSX = chargeMethods.tariffStartForMonthToMonth(countMonthForTariffStartMonthIOSX, 3);
        String setTariffStartMonthGeometrics = chargeMethods.tariffStartForMonthToMonth(countMonthForTariffStartMonthGeometrics, 5);
        String setTariffStartMonthEzHard = chargeMethods.tariffStartForMonthToMonth(countMonthForTariffStartMonthEzHard, 22);

        String setTariffStartOneYear = chargeMethods.tariffStartForOneYear(1);
        String setTariffStartTwoYears = chargeMethods.tariffStartForTwoYears(2);

        checkAC("No all tariffs are presented in eld scanners",
                chargeMethods.checkIfTariffPresent(countScannerMonthIOSXTariff, countScannerOneYearIOSXTariff, countScannerTwoYearsIOSXTariff ,
                        countScannerMonthGeometricsTariff, countScannerOneYearGeometricsTariff, countScannerTwoYearsGeometricsTariff,
                        countScannerMonthEzHardTariff, countScannerOneYearEzHardTariff, countScannerTwoYearsEzHardTariff), true);

        chargeMethods.informationOfDeactivatedAndReturnedScanners(countDeactivatedScannerMonthIOSXTariff, countScannerMonthIOSXChargeReturned, countScannerOneYearIOSXChargeReturned, countScannerTwoYearIOSXChargeReturned,
                countGeometricsMonthChargeReturnedScanner, countGeometricsOneYearChargeReturnedScanner, countGeometricsTwoYearChargeReturnedScanner,
                countEzHardMonthChargeReturnedScanner, countEzHardOneYearChargeReturnedScanner, countEzHardTwoYearChargeReturnedScanner);


//  SET PAID TILL AND ORDER DATE FOR IOSX TARIFF

        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartMonthIOSX, IOSXMonthTariffId);
        utilsForDB.setOrderDateByTariffId(userIdString, soloId, setTariffStartMonthIOSX, IOSXMonthTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearIOSXTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsIOSXTariffId);


//  SET PAID TILL AND ORDER DATE FOR GEOMETRICS TARIFF

        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartMonthGeometrics, geometricsMonthTariffId);
        utilsForDB.setOrderDateByTariffId(userIdString, soloId, setTariffStartMonthGeometrics, geometricsMonthTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearGeometricsTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsGeometricsTariffId);

//  SET PAID TILL AND ORDER DATE FOR EZ HARD TARIFF

        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartMonthGeometrics, monthEzHardTariffId);
        utilsForDB.setOrderDateByTariffId(userIdString, soloId, setTariffStartMonthEzHard, monthEzHardTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearEzHardTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsEzHardTariffId);

// charge by days ***************************************************************************
        String setTariffStartChargeByDaysMonthIOSX = chargeMethods.tariffStartForMonthToMonth(0, 10);
        String setTariffStartChargeByDaysMonthGeometrics = chargeMethods.tariffStartForMonthToMonth(0, 8);
        String setTariffStartChargeByDaysMonthEzHard = chargeMethods.tariffStartForMonthToMonth(0, 23);

        List<String> tempIdDeviceIOSXChargeByDays = utilsForDB.getIdChargeScannersByTariff(userIdString, soloId, IOSXMonthTariffId, countDevicesChargeByDaysIOSX);
        String idDeviceIOSXChargeByDays = String.join(", ", tempIdDeviceIOSXChargeByDays);

        List<String> tempIdDeviceGeometricsChargeByDays = utilsForDB.getIdChargeScannersByTariff(userIdString, soloId, geometricsMonthTariffId, countDevicesChargeByDaysGeometrics);
        String idDeviceGeometricsChargeByDays = String.join(", ", tempIdDeviceGeometricsChargeByDays);

        List<String> tempIdDeviceEzHardChargeByDays = utilsForDB.getIdChargeScannersByTariff(userIdString, soloId, monthEzHardTariffId, countDevicesChargeByDaysEzHard);
        String idDeviceEzHardChargeByDays = String.join(", ", tempIdDeviceEzHardChargeByDays);

        utilsForDB.setPaidTillAndTariffStartScannerById(setPaidTillForAllTariff, setTariffStartChargeByDaysMonthIOSX, idDeviceIOSXChargeByDays);
        utilsForDB.setPaidTillAndTariffStartScannerById(setPaidTillForAllTariff, setTariffStartChargeByDaysMonthGeometrics, idDeviceGeometricsChargeByDays);
        utilsForDB.setPaidTillAndTariffStartScannerById(setPaidTillForAllTariff, setTariffStartChargeByDaysMonthEzHard, idDeviceGeometricsChargeByDays);

        utilsForDB.delete_102_StatusByIdDevice(idDeviceIOSXChargeByDays);
        utilsForDB.delete_102_StatusByIdDevice(idDeviceGeometricsChargeByDays);
        utilsForDB.delete_102_StatusByIdDevice(idDeviceEzHardChargeByDays);

        utilsForDB.setOrderDateByDeviceId(setTariffStartChargeByDaysMonthIOSX, idDeviceIOSXChargeByDays);
        utilsForDB.setOrderDateByDeviceId(setTariffStartChargeByDaysMonthGeometrics, idDeviceGeometricsChargeByDays);
        utilsForDB.setOrderDateByDeviceId(setTariffStartChargeByDaysMonthEzHard, idDeviceEzHardChargeByDays);

        utilsForDB.setDateTimeEldHistoryByIdDevice(setTariffStartChargeByDaysMonthIOSX, idDeviceIOSXChargeByDays);
        utilsForDB.setDateTimeEldHistoryByIdDevice(setTariffStartChargeByDaysMonthGeometrics, idDeviceGeometricsChargeByDays);
        utilsForDB.setDateTimeEldHistoryByIdDevice(setTariffStartChargeByDaysMonthEzHard, idDeviceEzHardChargeByDays);

//  SET PAID TILL FOR USER FINANCES
        String paidTillForEzFinances = chargeMethods.paidTillForEzFinances();
        utilsForDB.setPaidTillEstimatedTillEzFinancesSolo(soloId, paidTillForEzFinances, paidTillForEzFinances);
        String timeRunCron = chargeMethods.runCronCheckDrivers();
        checkAC("DateTime dues are not correct", chargeMethods.checkDateTimeDue(userIdString, soloId, timeRunCron), true);

        double sumDeactivatedScannerMonthIOSXTariff = chargeMethods.sumDeactivatedScannerMonthToMonthTariff(userIdString, soloId, countDeactivatedScannerMonthIOSXTariff);
        double sumIOSXCharge = chargeMethods.sumCharge(
                (countScannerMonthIOSXTariff + countScannerMonthIOSXChargeReturned) - countDevicesChargeByDaysIOSX,
                countScannerOneYearIOSXTariff + countScannerOneYearIOSXChargeReturned,
                countScannerTwoYearsIOSXTariff + countScannerTwoYearIOSXChargeReturned, "IOSX");
        double sumGeometricsCharge = chargeMethods.sumCharge(
                (countScannerMonthGeometricsTariff + countGeometricsMonthChargeReturnedScanner) - countDevicesChargeByDaysGeometrics,
                countScannerOneYearGeometricsTariff + countGeometricsOneYearChargeReturnedScanner,
                countScannerTwoYearsGeometricsTariff + countGeometricsTwoYearChargeReturnedScanner, "Geometrics");
        double sumEzHardCharge = chargeMethods.sumCharge(
                (countScannerMonthEzHardTariff + countEzHardMonthChargeReturnedScanner) - countDevicesChargeByDaysEzHard,
                countScannerOneYearEzHardTariff + countEzHardOneYearChargeReturnedScanner,
                countScannerTwoYearsEzHardTariff + countEzHardTwoYearChargeReturnedScanner, "EzHard");
        double sumIOSXChargeByDays = chargeMethods.sumChargeMonthToMonthTariffByDays(setTariffStartChargeByDaysMonthIOSX, countDevicesChargeByDaysIOSX);
        double sumGeometricsChargeByDays = chargeMethods.sumChargeMonthToMonthTariffByDays(setTariffStartChargeByDaysMonthGeometrics, countDevicesChargeByDaysGeometrics);
        double sumEzHardChargeByDays = chargeMethods.sumChargeMonthToMonthTariffByDays(setTariffStartChargeByDaysMonthEzHard, countDevicesChargeByDaysEzHard);

        checkAC("Charge due is not correct",
                chargeMethods.compareDueCharge(userIdString, soloId, sumIOSXCharge + sumDeactivatedScannerMonthIOSXTariff + sumGeometricsCharge + sumIOSXChargeByDays + sumGeometricsChargeByDays + sumEzHardCharge + sumEzHardChargeByDays, timeRunCron), true);

        checkAC("PaidTill for month-to-month is not correct", chargeMethods.comparePaidTillMonthToMonth(userIdString, soloId, unionMonthTariffId), true);
        checkAC("PaidTill for one year is not correct", chargeMethods.comparePaidTillOneYear(userIdString, soloId, oneYearIOSXTariffId), true);
        checkAC("PaidTill for two years is not correct", chargeMethods.comparePaidTillTwoYears(userIdString, soloId, twoYearsIOSXTariffId), true);
        checkAC("EstimatedTill in eld_personal_finances is not correct", chargeMethods.compareEstimatedTillSolo(soloId), true);
        checkAC("PaidTill in eld_personal_finances is not correct", chargeMethods.comparePaidTillSolo(soloId), true);

    }

}
