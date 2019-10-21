package chargeTest;

import org.junit.Test;
import parentTest.ParentChargeTest;

import java.io.IOException;
import java.sql.SQLException;

public class ChargeMonthToMonthTariff extends ParentChargeTest {
    String monthToMonthTariffId = "0";
    String carrierIdString = "carrierId";
    String fleetString = "fleet";
    String userIdString = "userId";

    String soloId = "3455";
    String fleetId = "518";
    String fleetUserId = "3816";


    @Test
    public void chargeMonthToMonthFleet() throws SQLException, IOException, ClassNotFoundException {
        int countScannerMonthToMonthTariff = utilsForDB.countChargeScannersWithTariff(fleetString, fleetId, monthToMonthTariffId);
        int countDeactivatedScannerMonthToMonthTariff = utilsForDB.countDeactivatedChargeScannersMonthToMonth(fleetString, fleetId);
        int countMonthToMonthChargeReturnedScanner = utilsForDB.countChargeReturnedScanner(userIdString, fleetUserId, monthToMonthTariffId);
        System.out.println("countScannerMonthToMonthTariff " + countScannerMonthToMonthTariff);
        System.out.println("countDeactivatedScannerMonthToMonthTariff " + countDeactivatedScannerMonthToMonthTariff);
        System.out.println("countMonthToMonthChargeReturnedScanner " + countMonthToMonthChargeReturnedScanner);

        String setPaidTillForAllTariff = chargePage.paidTillForAllTariff();
        String setTariffStartMonth = chargePage.tariffStartForMonthToMonth(12);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartMonth, monthToMonthTariffId);
        utilsForDB.delete_102_Status(fleetString, fleetId, monthToMonthTariffId);
        utilsForDB.setOrderDateForMonthToMonth(fleetString, fleetId, setTariffStartMonth);
        utilsForDB.setDateTimeEldHistoryForMonthToMonth(fleetString, fleetId, setTariffStartMonth);
        String paidTillForEzFinances = chargePage.paidTillForEzFinances();
        utilsForDB.setPaidTillEstimatedTillEzFinancesFleet(fleetId, paidTillForEzFinances, paidTillForEzFinances);
        checkAC("DateTime dues are not correct", chargePage.checkDateTimeDueMonthToMonth(carrierIdString, fleetId, chargePage.runCronCheckFleet()), true);
        double sumDeactivatedScannerMonthToMonthTariff = chargePage.sumDeactivatedScannerMonthToMonthTariff(fleetString, fleetId, countDeactivatedScannerMonthToMonthTariff);
        System.out.println("countScannerMonthToMonthTariff = " + countScannerMonthToMonthTariff);
        System.out.println("countMonthToMonthChargeReturnedScanner = " + countMonthToMonthChargeReturnedScanner);
        checkAC("Charge due is not correct", chargePage.compareDueChargeMonthToMonthTariff(carrierIdString, fleetId, countScannerMonthToMonthTariff + countMonthToMonthChargeReturnedScanner, sumDeactivatedScannerMonthToMonthTariff), true);

        checkAC("PaidTill for month-to-month is not correct", chargePage.comparePaidTillMonthToMonth(fleetString, fleetId, monthToMonthTariffId), true);
        checkAC("EstimatedTill in ez_finance is not correct", chargePage.compareEstimatedTillFleet(fleetId), true);
        checkAC("PaidTill in ez_finance is not correct", chargePage.comparePaidTillFleet(fleetId), true);

    }

    @Test
    public void chargeMonthToMonthSolo() throws SQLException, IOException, ClassNotFoundException {
        int countScannerMonthToMonthTariff = utilsForDB.countChargeScannersWithTariff(userIdString, soloId, monthToMonthTariffId);
        int countDeactivatedScannerMonthToMonthTariff = utilsForDB.countDeactivatedChargeScannersMonthToMonth(userIdString, soloId);
        int countMonthToMonthChargeReturnedScanner = utilsForDB.countChargeReturnedScanner(userIdString, soloId, monthToMonthTariffId);
        System.out.println("countScannerMonthToMonthTariff " + countScannerMonthToMonthTariff);
        System.out.println("countDeactivatedScannerMonthToMonthTariff " + countDeactivatedScannerMonthToMonthTariff);
        System.out.println("countMonthToMonthChargeReturnedScanner " + countMonthToMonthChargeReturnedScanner);

        String setPaidTillForAllTariff = chargePage.paidTillForAllTariff();
        String setTariffStartMonth = chargePage.tariffStartForMonthToMonth(12);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartMonth, monthToMonthTariffId);
        utilsForDB.delete_102_Status(userIdString, soloId, monthToMonthTariffId);
        utilsForDB.setOrderDateForMonthToMonth(userIdString, soloId, setTariffStartMonth);
        utilsForDB.setDateTimeEldHistoryForMonthToMonth(userIdString, soloId, setTariffStartMonth);
        String paidTillForEzFinances = chargePage.paidTillForEzFinances();
        utilsForDB.setPaidTillEstimatedTillEzFinancesSolo(soloId, paidTillForEzFinances, paidTillForEzFinances);
        checkAC("DateTime dues are not correct", chargePage.checkDateTimeDueMonthToMonth(userIdString, soloId, chargePage.runCronCheckDrivers()), true);
        double sumDeactivatedScannerMonthToMonthTariff = chargePage.sumDeactivatedScannerMonthToMonthTariff(userIdString, soloId, countDeactivatedScannerMonthToMonthTariff);
        System.out.println("countScannerMonthToMonthTariff = " + countScannerMonthToMonthTariff);
        System.out.println("countMonthToMonthChargeReturnedScanner = " + countMonthToMonthChargeReturnedScanner);
        checkAC("Charge due is not correct", chargePage.compareDueChargeMonthToMonthTariff(userIdString, soloId, countScannerMonthToMonthTariff + countMonthToMonthChargeReturnedScanner, sumDeactivatedScannerMonthToMonthTariff), true);

        checkAC("PaidTill for month-to-month is not correct", chargePage.comparePaidTillMonthToMonth(userIdString, soloId, monthToMonthTariffId), true);
        checkAC("EstimatedTill in ez_finance is not correct", chargePage.compareEstimatedTillSolo(soloId), true);
        checkAC("PaidTill in ez_finance is not correct", chargePage.comparePaidTillSolo(soloId), true);

    }
}
