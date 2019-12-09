package chargeTest;

import org.junit.Test;
import parentTest.ParentChargeTest;

import java.io.IOException;
import java.sql.SQLException;

public class ChargeFleetSoloTest extends ParentChargeTest{
    String monthIOSXTariffId = "0";
    String oneYearIOSXTariffId = "1";
    String twoYearsIOSXTariffId = "2";
    String monthGeometricsTariffId = "9";
    String oneYearGeometricsTariffId = "10";
    String twoYearsGeometricsTariffId = "11";

    String carrierIdString = "carrierId";
    String fleetString = "fleet";
    String userIdString = "userId";

    String soloId = "3455";
    String fleetId = "576";
    String fleetUserId = "3816";

    String unionMonthTariffId = monthIOSXTariffId + ", " + monthGeometricsTariffId;
    String unionOneYearTariffId = oneYearIOSXTariffId + ", " + oneYearGeometricsTariffId;
    String unionTwoYearsTariffId = twoYearsIOSXTariffId + ", " + twoYearsGeometricsTariffId;

    int countMonthForTariffStartMonthIOSX = 3;
    int countMonthForTariffStartMonthGeometrics = 3;

    @Test
    public void chargeFleetTest() throws SQLException, IOException, ClassNotFoundException{

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


        String setPaidTillForAllTariff = chargePage.paidTillForAllTariff();

        String setTariffStartMonthIOSX = chargePage.tariffStartForMonthToMonth(countMonthForTariffStartMonthIOSX, 0);
        String setTariffStartMonthGeometrics = chargePage.tariffStartForMonthToMonth(countMonthForTariffStartMonthGeometrics, 0);


        String setTariffStartOneYear = chargePage.tariffStartForOneYear(1);
        String setTariffStartTwoYears = chargePage.tariffStartForTwoYears(2);


        checkAC("* No all tariffs are presented on fleet",
                chargePage.checkIfTariffPresent(countScannerMonthIOSXTariff, countScannerOneYearIOSXTariff, countScannerTwoYearsIOSXTariff , countScannerMonthGeometricsTariff,
                countScannerOneYearGeometricsTariff, countScannerTwoYearsGeometricsTariff), true);

        chargePage.informationOfDeactivatedAndReturnedScanners(countDeactivatedScannerMonthIOSXTariff, countScannerMonthIOSXChargeReturned, countScannerOneYearIOSXChargeReturned,
                countScannerTwoYearIOSXChargeReturned, countGeometricsMonthChargeReturnedScanner, countGeometricsOneYearChargeReturnedScanner, countGeometricsTwoYearChargeReturnedScanner);

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

// charge by days ***************************************************************************
        String setTariffStartChargeByDaysMonthIOSX = chargePage.tariffStartForMonthToMonth(0, 6);
        String setTariffStartChargeByDaysMonthGeometrics = chargePage.tariffStartForMonthToMonth(0, 6);

        String idDeviceIOSXChargeByDays = utilsForDB.getIdChargeScannersByTariff(fleetString, fleetId, monthIOSXTariffId);
        String idDeviceGeometricsChargeByDays = utilsForDB.getIdChargeScannersByTariff(fleetString, fleetId, monthGeometricsTariffId);

        utilsForDB.setPaidTillAndTariffStartScannerById(setPaidTillForAllTariff, setTariffStartChargeByDaysMonthIOSX, idDeviceIOSXChargeByDays);
        utilsForDB.setPaidTillAndTariffStartScannerById(setPaidTillForAllTariff, setTariffStartChargeByDaysMonthGeometrics, idDeviceGeometricsChargeByDays);

        utilsForDB.delete_102_StatusByIdDevice(idDeviceIOSXChargeByDays);
        utilsForDB.delete_102_StatusByIdDevice(idDeviceGeometricsChargeByDays);

        utilsForDB.setOrderDateByDeviceId(setTariffStartChargeByDaysMonthIOSX, idDeviceIOSXChargeByDays);
        utilsForDB.setOrderDateByDeviceId(setTariffStartChargeByDaysMonthGeometrics, idDeviceGeometricsChargeByDays);

        utilsForDB.setDateTimeEldHistoryByIdDevice(setTariffStartChargeByDaysMonthIOSX, idDeviceIOSXChargeByDays);
        utilsForDB.setDateTimeEldHistoryByIdDevice(setTariffStartChargeByDaysMonthGeometrics, idDeviceGeometricsChargeByDays);

//  SET PAID TILL FOR USER FINANCES

//      ***************************
//        utilsForDB.setPaidTillAndTariffStartScannerForFleetByIdScanners(fleetId, "0", "1574251321", "19143, 19147");
//      *******************************************
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

        checkAC("Charge due is not correct",
                chargePage.compareDueCharge(carrierIdString, fleetId, sumIOSXCharge + sumDeactivatedScannerMonthIOSXTariff + sumGeometricsCharge, timeRunCron), true);

        checkAC("PaidTill for month-to-month is not correct", chargePage.comparePaidTillMonthToMonth(fleetString, fleetId, unionMonthTariffId), true);
        checkAC("PaidTill for one year is not correct", chargePage.comparePaidTillOneYear(fleetString, fleetId, unionOneYearTariffId), true);
        checkAC("PaidTill for two years is not correct", chargePage.comparePaidTillTwoYears(fleetString, fleetId, unionTwoYearsTariffId), true);
        checkAC("EstimatedTill in ez_finance is not correct", chargePage.compareEstimatedTillFleet(fleetId), true);
        checkAC("PaidTill in ez_finance is not correct", chargePage.comparePaidTillFleet(fleetId), true);

    }

    @Test
    public  void chargeSoloTest() throws SQLException, IOException, ClassNotFoundException{

//  COUNT IOSX TARIFF
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



        String setPaidTillForAllTariff = chargePage.paidTillForAllTariff();
        String setTariffStartMonthIOSX = chargePage.tariffStartForMonthToMonth(countMonthForTariffStartMonthIOSX, 5);
        String setTariffStartMonthGeometrics = chargePage.tariffStartForMonthToMonth(countMonthForTariffStartMonthGeometrics, 5);
        String setTariffStartOneYear = chargePage.tariffStartForOneYear(1);
        String setTariffStartTwoYears = chargePage.tariffStartForTwoYears(2);

        checkAC("No all tariffs are presented in eld scanners",
                chargePage.checkIfTariffPresent(countScannerMonthIOSXTariff, countScannerOneYearIOSXTariff, countScannerTwoYearsIOSXTariff , countScannerMonthGeometricsTariff,
                countScannerOneYearGeometricsTariff, countScannerTwoYearsGeometricsTariff), true);

        chargePage.informationOfDeactivatedAndReturnedScanners(countDeactivatedScannerMonthIOSXTariff, countScannerMonthIOSXChargeReturned, countScannerOneYearIOSXChargeReturned,
                countScannerTwoYearIOSXChargeReturned, countGeometricsMonthChargeReturnedScanner, countGeometricsOneYearChargeReturnedScanner, countGeometricsTwoYearChargeReturnedScanner);


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

        checkAC("Charge due is not correct",
                chargePage.compareDueCharge(userIdString, soloId, sumIOSXCharge + sumDeactivatedScannerMonthIOSXTariff + sumGeometricsCharge, timeRunCron), true);

        checkAC("PaidTill for month-to-month is not correct", chargePage.comparePaidTillMonthToMonth(userIdString, soloId, unionMonthTariffId), true);
        checkAC("PaidTill for one year is not correct", chargePage.comparePaidTillOneYear(userIdString, soloId, oneYearIOSXTariffId), true);
        checkAC("PaidTill for two years is not correct", chargePage.comparePaidTillTwoYears(userIdString, soloId, twoYearsIOSXTariffId), true);
        checkAC("EstimatedTill in eld_personal_finances is not correct", chargePage.compareEstimatedTillSolo(soloId), true);
        checkAC("PaidTill in eld_personal_finances is not correct", chargePage.comparePaidTillSolo(soloId), true);
//        checkAC("Charge was after test", chargePage.checkDateTimeDue(userIdString, soloId, chargePage.runCronCheckDrivers()), false);
//        checkAC("Charge was after test", chargePage.checkDateTimeDue(userIdString, soloId, chargePage.runCronCheckFleet()), false);

    }

}
