package chargeTest;

import org.junit.Test;
import parentTest.ParentChargeTest;

import java.io.IOException;
import java.sql.SQLException;

public class ChargeMonthToMonthTariff extends ParentChargeTest {
    String IOSXMonthTariffId = "0";
    String geometricsMonthTariffId = "9";
    String carrierIdString = "carrierId";
    String fleetString = "fleet";
    String userIdString = "userId";

    String soloId = "3455";
    String fleetId = "531";
    String fleetUserId = "4379";

    String unionMonthTariffId = IOSXMonthTariffId + ", " + geometricsMonthTariffId;



    @Test
    public void chargeMonthToMonthFleet() throws SQLException, IOException, ClassNotFoundException {
        int countScannerMonthIOSXTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, IOSXMonthTariffId);
        int countScannerMonthGeometricsTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, geometricsMonthTariffId);
        int countDeactivatedScannerMonthIOSXTariff = utilsForDB.countDeactivatedChargeScannersMonthToMonth(fleetString, fleetId);
        int countMonthIOSXChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, IOSXMonthTariffId);
        int countMonthGeometricsChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, geometricsMonthTariffId);

//
//        List<String> idListScannerMonthIOSXTariff = utilsForDB.getIdChargeScannersByTariff(fleetString, fleetId, IOSXMonthTariffId);
//

        String setPaidTillForAllTariff = chargePage.paidTillForAllTariff();
        String setTariffStartMonthIOSX = chargePage.tariffStartForMonthToMonth(0, 6);
        String setDateTimeEldHistoryMonthIOSX = chargePage.dateTimeEldHistoryForMonthToMonth(0, 6);

        String setTariffStartMonthGeometrics = chargePage.tariffStartForMonthToMonth(0, 6);
        String setDateTimeEldHistoryMonthGeometrics = chargePage.dateTimeEldHistoryForMonthToMonth(0, 6);

        chargePage.informationOfScannersMonth(countScannerMonthIOSXTariff, countScannerMonthGeometricsTariff, countDeactivatedScannerMonthIOSXTariff, countMonthIOSXChargeReturnedScanner, countMonthGeometricsChargeReturnedScanner);

        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartMonthIOSX, IOSXMonthTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartMonthGeometrics, geometricsMonthTariffId);

        utilsForDB.delete_102_Status(fleetString, fleetId, IOSXMonthTariffId);
        utilsForDB.delete_102_Status(fleetString, fleetId, geometricsMonthTariffId);

        utilsForDB.setOrderDateByTariffId(fleetString, fleetId, setTariffStartMonthIOSX, IOSXMonthTariffId);
        utilsForDB.setOrderDateByTariffId(fleetString, fleetId, setTariffStartMonthGeometrics, geometricsMonthTariffId);

        utilsForDB.setDateTimeEldHistoryForMonthToMonth(fleetString, fleetId, setDateTimeEldHistoryMonthIOSX);
        utilsForDB.setDateTimeEldHistoryForMonthToMonth(fleetString, fleetId, setDateTimeEldHistoryMonthGeometrics);

        String paidTillForEzFinances = chargePage.paidTillForEzFinances();

        utilsForDB.setPaidTillEstimatedTillEzFinancesFleet(fleetId, paidTillForEzFinances, paidTillForEzFinances);

        String timeRunCron = chargePage.runCronCheckFleet();
        checkAC("* DateTime dues are not correct", chargePage.checkDateTimeDueMonthToMonth(carrierIdString, fleetId, timeRunCron), true);

        double sumDeactivatedScannerMonthToMonthTariff = chargePage.sumDeactivatedScannerMonthToMonthTariff(fleetString, fleetId, countDeactivatedScannerMonthIOSXTariff);

        checkAC("* Charge due is not correct", chargePage.compareDueChargeMonthToMonthTariff(setTariffStartMonthIOSX, carrierIdString, fleetId,
                countScannerMonthIOSXTariff + countMonthIOSXChargeReturnedScanner + countScannerMonthGeometricsTariff + countMonthGeometricsChargeReturnedScanner,
                sumDeactivatedScannerMonthToMonthTariff, timeRunCron), true);

        checkAC("* PaidTill for month-to-month is not correct", chargePage.comparePaidTillMonthToMonth(fleetString, fleetId, unionMonthTariffId), true);
        checkAC("* EstimatedTill in ez_finance is not correct", chargePage.compareEstimatedTillFleet(fleetId), true);
        checkAC("* PaidTill in ez_finance is not correct", chargePage.comparePaidTillFleet(fleetId), true);

    }

    @Test
    public void chargeMonthToMonthSolo() throws SQLException, IOException, ClassNotFoundException {

        int countScannerMonthIOSXTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, IOSXMonthTariffId);
        int countScannerMonthGeometricsTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, geometricsMonthTariffId);
        int countDeactivatedScannerMonthIOSXTariff = utilsForDB.countDeactivatedChargeScannersMonthToMonth(userIdString, soloId);
        int countMonthIOSXChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, IOSXMonthTariffId);
        int countMonthGeometricsChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, geometricsMonthTariffId);

        String setPaidTillForAllTariff = chargePage.paidTillForAllTariff();

        String setTariffStartMonthIOSX = chargePage.tariffStartForMonthToMonth(0, 5);
        String setDateTimeEldHistoryMonthIOSX = chargePage.dateTimeEldHistoryForMonthToMonth(0, 5);

        String setTariffStartMonthGeometrics = chargePage.tariffStartForMonthToMonth(0, 5);
        String setDateTimeEldHistoryMonthGeometrics = chargePage.dateTimeEldHistoryForMonthToMonth(0, 5);

        chargePage.informationOfScannersMonth(countScannerMonthIOSXTariff, countScannerMonthGeometricsTariff, countDeactivatedScannerMonthIOSXTariff, countMonthIOSXChargeReturnedScanner,
                countMonthGeometricsChargeReturnedScanner);

        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartMonthIOSX, IOSXMonthTariffId);
        utilsForDB.delete_102_Status(userIdString, soloId, IOSXMonthTariffId);
        utilsForDB.setOrderDateByTariffId(userIdString, soloId, setTariffStartMonthIOSX, IOSXMonthTariffId);
        utilsForDB.setDateTimeEldHistoryForMonthToMonth(userIdString, soloId, setDateTimeEldHistoryMonthIOSX);



        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartMonthGeometrics, geometricsMonthTariffId);
        utilsForDB.delete_102_Status(userIdString, soloId, geometricsMonthTariffId);
        utilsForDB.setOrderDateByTariffId(userIdString, soloId, setTariffStartMonthGeometrics, geometricsMonthTariffId);
        utilsForDB.setDateTimeEldHistoryForMonthToMonth(userIdString, soloId, setDateTimeEldHistoryMonthGeometrics);

        String paidTillForEzFinances = chargePage.paidTillForEzFinances();

        utilsForDB.setPaidTillEstimatedTillEzFinancesSolo(soloId, paidTillForEzFinances, paidTillForEzFinances);

        String timeRunCron = chargePage.runCronCheckDrivers();
        checkAC("* DateTime dues are not correct", chargePage.checkDateTimeDueMonthToMonth(userIdString, soloId, timeRunCron), true);

        double sumDeactivatedScannerMonthToMonthTariff = chargePage.sumDeactivatedScannerMonthToMonthTariff(userIdString, soloId, countDeactivatedScannerMonthIOSXTariff);

        checkAC("* Charge due is not correct", chargePage.compareDueChargeMonthToMonthTariff(setTariffStartMonthIOSX, userIdString, soloId,
                countScannerMonthIOSXTariff + countMonthIOSXChargeReturnedScanner + countScannerMonthGeometricsTariff + countMonthGeometricsChargeReturnedScanner,
                sumDeactivatedScannerMonthToMonthTariff, timeRunCron), true);

        checkAC("* PaidTill for month-to-month is not correct", chargePage.comparePaidTillMonthToMonth(userIdString, soloId, unionMonthTariffId), true);
        checkAC("* EstimatedTill in ez_finance is not correct", chargePage.compareEstimatedTillSolo(soloId), true);
        checkAC("* PaidTill in ez_finance is not correct", chargePage.comparePaidTillSolo(soloId), true);

    }
}
