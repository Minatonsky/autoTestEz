package chargeTest;

import org.junit.Test;
import parentTest.ParentTestWithoutWebDriver;

import java.io.IOException;
import java.sql.SQLException;

import static libs.DataForTests.*;

public class ChargeMonthToMonthTariff extends ParentTestWithoutWebDriver {

    String soloId = "3455";
    String fleetId = "518";
    int countDays = 21;

    String unionMonthTariffId = IOSXMonthTariffId + ", " + geometricsMonthTariffId;

    @Test
    public void chargeMonthToMonthFleet() throws SQLException, IOException, ClassNotFoundException {
        int countScannerMonthIOSXTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, IOSXMonthTariffId);
        int countMonthIOSXChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, IOSXMonthTariffId);
        int countDeactivatedScannerMonthIOSXTariff = utilsForDB.countDeactivatedChargeScannersMonthToMonth(fleetString, fleetId);

        int countScannerMonthGeometricsTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, geometricsMonthTariffId);
        int countMonthGeometricsChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, geometricsMonthTariffId);

        int countScannerMonthEzHardTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, ezHardMonthTariffId);
        int countMonthEzHardChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, ezHardMonthTariffId);

//
//        List<String> idListScannerMonthIOSXTariff = utilsForDB.getIdChargeScannersByTariff(fleetString, fleetId, IOSXMonthTariffId);
//

        String setPaidTillForAllTariff = chargeMethods.paidTillForAllTariff();

        String setTariffStartMonthIOSX = chargeMethods.tariffStartForMonthToMonth(0, countDays);
        String setDateTimeEldHistoryMonthIOSX = chargeMethods.dateTimeEldHistoryForMonthToMonth(0, countDays);

        String setTariffStartMonthGeometrics = chargeMethods.tariffStartForMonthToMonth(0, countDays);
        String setDateTimeEldHistoryMonthGeometrics = chargeMethods.dateTimeEldHistoryForMonthToMonth(0, countDays);

        String setTariffStartMonthEzHard = chargeMethods.tariffStartForMonthToMonth(0, countDays);
        String setDateTimeEldHistoryMonthEzHard = chargeMethods.dateTimeEldHistoryForMonthToMonth(0, countDays);

        chargeMethods.informationOfScannersMonth(
                countScannerMonthIOSXTariff, countDeactivatedScannerMonthIOSXTariff, countMonthIOSXChargeReturnedScanner,
                countScannerMonthGeometricsTariff, countMonthGeometricsChargeReturnedScanner,
                countScannerMonthEzHardTariff, countMonthEzHardChargeReturnedScanner
                );

        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartMonthIOSX, IOSXMonthTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartMonthGeometrics, geometricsMonthTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartMonthEzHard, ezHardMonthTariffId);

        utilsForDB.delete_102_Status(fleetString, fleetId, IOSXMonthTariffId);
        utilsForDB.delete_102_Status(fleetString, fleetId, geometricsMonthTariffId);
        utilsForDB.delete_102_Status(fleetString, fleetId, ezHardMonthTariffId);

        utilsForDB.setOrderDateByTariffId(fleetString, fleetId, setTariffStartMonthIOSX, IOSXMonthTariffId);
        utilsForDB.setOrderDateByTariffId(fleetString, fleetId, setTariffStartMonthGeometrics, geometricsMonthTariffId);
        utilsForDB.setOrderDateByTariffId(fleetString, fleetId, setTariffStartMonthEzHard, ezHardMonthTariffId);

        utilsForDB.setDateTimeEldHistoryForMonthToMonth(fleetString, fleetId, setDateTimeEldHistoryMonthIOSX);
        utilsForDB.setDateTimeEldHistoryForMonthToMonth(fleetString, fleetId, setDateTimeEldHistoryMonthGeometrics);
        utilsForDB.setDateTimeEldHistoryForMonthToMonth(fleetString, fleetId, setDateTimeEldHistoryMonthEzHard);

        String paidTillForEzFinances = chargeMethods.paidTillForEzFinances();

        utilsForDB.setPaidTillEstimatedTillEzFinancesFleet(fleetId, paidTillForEzFinances, paidTillForEzFinances);

        String timeRunCron = chargeMethods.runCronCheckFleet();
        checkAC("* DateTime dues are not correct", chargeMethods.checkDateTimeDueMonthToMonth(carrierIdString, fleetId, timeRunCron), true);

        double sumDeactivatedScannerMonthToMonthTariff = chargeMethods.sumDeactivatedScannerMonthToMonthTariff(fleetString, fleetId, countDeactivatedScannerMonthIOSXTariff);

        checkAC("* Charge due is not correct", chargeMethods.compareDueChargeMonthToMonthTariff(setTariffStartMonthIOSX, carrierIdString, fleetId,
                countScannerMonthIOSXTariff + countMonthIOSXChargeReturnedScanner + countScannerMonthGeometricsTariff + countMonthGeometricsChargeReturnedScanner +
                        countScannerMonthEzHardTariff + countMonthEzHardChargeReturnedScanner,
                sumDeactivatedScannerMonthToMonthTariff, timeRunCron), true);

        checkAC("* PaidTill for month-to-month is not correct", chargeMethods.comparePaidTillMonthToMonth(fleetString, fleetId, unionMonthTariffId), true);
        checkAC("* EstimatedTill in ez_finance is not correct", chargeMethods.compareEstimatedTillFleet(fleetId), true);
        checkAC("* PaidTill in ez_finance is not correct", chargeMethods.comparePaidTillFleet(fleetId), true);

    }

    @Test
    public void chargeMonthToMonthSolo() throws SQLException, IOException, ClassNotFoundException {

        int countScannerMonthIOSXTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, IOSXMonthTariffId);
        int countDeactivatedScannerMonthIOSXTariff = utilsForDB.countDeactivatedChargeScannersMonthToMonth(userIdString, soloId);
        int countMonthIOSXChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, IOSXMonthTariffId);

        int countScannerMonthGeometricsTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, geometricsMonthTariffId);
        int countMonthGeometricsChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, geometricsMonthTariffId);

        int countScannerMonthEzHardTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, ezHardMonthTariffId);
        int countMonthEzHardChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, ezHardMonthTariffId);

        String setPaidTillForAllTariff = chargeMethods.paidTillForAllTariff();

        String setTariffStartMonthIOSX = chargeMethods.tariffStartForMonthToMonth(0, countDays);
        String setDateTimeEldHistoryMonthIOSX = chargeMethods.dateTimeEldHistoryForMonthToMonth(0, countDays);

        String setTariffStartMonthGeometrics = chargeMethods.tariffStartForMonthToMonth(0, countDays);
        String setDateTimeEldHistoryMonthGeometrics = chargeMethods.dateTimeEldHistoryForMonthToMonth(0, countDays);

        String setTariffStartMonthEzHard = chargeMethods.tariffStartForMonthToMonth(0, countDays);
        String setDateTimeEldHistoryMonthEzHard = chargeMethods.dateTimeEldHistoryForMonthToMonth(0, countDays);

        chargeMethods.informationOfScannersMonth(
                countScannerMonthIOSXTariff, countDeactivatedScannerMonthIOSXTariff, countMonthIOSXChargeReturnedScanner,
                countScannerMonthGeometricsTariff, countMonthGeometricsChargeReturnedScanner,
                countScannerMonthEzHardTariff, countMonthEzHardChargeReturnedScanner
        );

        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartMonthIOSX, IOSXMonthTariffId);
        utilsForDB.delete_102_Status(userIdString, soloId, IOSXMonthTariffId);
        utilsForDB.setOrderDateByTariffId(userIdString, soloId, setTariffStartMonthIOSX, IOSXMonthTariffId);
        utilsForDB.setDateTimeEldHistoryForMonthToMonth(userIdString, soloId, setDateTimeEldHistoryMonthIOSX);

        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartMonthGeometrics, geometricsMonthTariffId);
        utilsForDB.delete_102_Status(userIdString, soloId, geometricsMonthTariffId);
        utilsForDB.setOrderDateByTariffId(userIdString, soloId, setTariffStartMonthGeometrics, geometricsMonthTariffId);
        utilsForDB.setDateTimeEldHistoryForMonthToMonth(userIdString, soloId, setDateTimeEldHistoryMonthGeometrics);

        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartMonthEzHard, ezHardMonthTariffId);
        utilsForDB.delete_102_Status(userIdString, soloId, ezHardMonthTariffId);
        utilsForDB.setOrderDateByTariffId(userIdString, soloId, setTariffStartMonthEzHard, ezHardMonthTariffId);
        utilsForDB.setDateTimeEldHistoryForMonthToMonth(userIdString, soloId, setDateTimeEldHistoryMonthEzHard);

        String paidTillForEzFinances = chargeMethods.paidTillForEzFinances();

        utilsForDB.setPaidTillEstimatedTillEzFinancesSolo(soloId, paidTillForEzFinances, paidTillForEzFinances);

        String timeRunCron = chargeMethods.runCronCheckDrivers();
        checkAC("* DateTime dues are not correct", chargeMethods.checkDateTimeDueMonthToMonth(userIdString, soloId, timeRunCron), true);

        double sumDeactivatedScannerMonthToMonthTariff = chargeMethods.sumDeactivatedScannerMonthToMonthTariff(userIdString, soloId, countDeactivatedScannerMonthIOSXTariff);

        checkAC("* Charge due is not correct", chargeMethods.compareDueChargeMonthToMonthTariff(setTariffStartMonthIOSX, userIdString, soloId,
                countScannerMonthIOSXTariff + countMonthIOSXChargeReturnedScanner + countScannerMonthGeometricsTariff + countMonthGeometricsChargeReturnedScanner +
                        countScannerMonthEzHardTariff + countMonthEzHardChargeReturnedScanner,
                sumDeactivatedScannerMonthToMonthTariff, timeRunCron), true);

        checkAC("* PaidTill for month-to-month is not correct", chargeMethods.comparePaidTillMonthToMonth(userIdString, soloId, unionMonthTariffId), true);
        checkAC("* EstimatedTill in ez_finance is not correct", chargeMethods.compareEstimatedTillSolo(soloId), true);
        checkAC("* PaidTill in ez_finance is not correct", chargeMethods.comparePaidTillSolo(soloId), true);

    }

}
