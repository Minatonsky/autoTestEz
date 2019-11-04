package chargeTest;

import org.junit.Test;
import parentTest.ParentChargeTest;

import java.io.IOException;
import java.sql.SQLException;

public class ChargeFleetSoloTest extends ParentChargeTest{
    String monthToMonthTariffId = "0";
    String oneYearTariffId = "1";
    String twoYearsTariffId = "2";
    String carrierIdString = "carrierId";
    String fleetString = "fleet";
    String userIdString = "userId";

    String soloId = "3455";
    String fleetId = "518";
    String fleetUserId = "3816";

    int countMonthForTariffStartMonthToMonth = 2;

    @Test
    public void chargeFleetTest() throws SQLException, IOException, ClassNotFoundException{

        int countScannerMonthToMonthTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, monthToMonthTariffId);
        int countDeactivatedScannerMonthToMonthTariff = utilsForDB.countDeactivatedChargeScannersMonthToMonth(fleetString, fleetId);
        int countScannerOneYearTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, oneYearTariffId);
        int countScannerTwoYearsTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, twoYearsTariffId);

        int countMonthToMonthChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, fleetUserId, monthToMonthTariffId);
        int countOneYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, fleetUserId, oneYearTariffId);
        int countTwoYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, fleetUserId, twoYearsTariffId);

        String setPaidTillForAllTariff = chargePage.paidTillForAllTariff();
        String setTariffStartMonth = chargePage.tariffStartForMonthToMonth(countMonthForTariffStartMonthToMonth);
        String setTariffStartOneYear = chargePage.tariffStartForOneYear(1);
        String setTariffStartTwoYears = chargePage.tariffStartForTwoYears(2);


        checkAC("No all tariffs are presented in eld scanners", chargePage.checkIfTariffPresent(countScannerMonthToMonthTariff, countScannerOneYearTariff, countScannerTwoYearsTariff), true);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartMonth, monthToMonthTariffId);
        utilsForDB.setOrderDateForMonthToMonth(fleetString, fleetId, setTariffStartMonth);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsTariffId);

        String paidTillForEzFinances = chargePage.paidTillForEzFinances();
        utilsForDB.setPaidTillEstimatedTillEzFinancesFleet(fleetId, paidTillForEzFinances, paidTillForEzFinances);

        checkAC("DateTime dues are not correct", chargePage.checkDateTimeDue(carrierIdString, fleetId, chargePage.runCronCheckFleet()), true);
        double sumDeactivatedScannerMonthToMonthTariff = chargePage.sumDeactivatedScannerMonthToMonthTariff(fleetString, fleetId, countDeactivatedScannerMonthToMonthTariff);
        double sumCharge = chargePage.sumCharge(countScannerMonthToMonthTariff + countMonthToMonthChargeReturnedScanner, countScannerOneYearTariff + countOneYearChargeReturnedScanner, countScannerTwoYearsTariff + countTwoYearChargeReturnedScanner, sumDeactivatedScannerMonthToMonthTariff);
        checkAC("Charge due is not correct", chargePage.compareDueCharge(carrierIdString, fleetId, sumCharge), true);

        checkAC("PaidTill for month-to-month is not correct", chargePage.comparePaidTillMonthToMonth(fleetString, fleetId, monthToMonthTariffId), true);
        checkAC("PaidTill for one year is not correct", chargePage.comparePaidTillOneYear(fleetString, fleetId, oneYearTariffId), true);
        checkAC("PaidTill for two years is not correct", chargePage.comparePaidTillTwoYears(fleetString, fleetId, twoYearsTariffId), true);
        checkAC("EstimatedTill in ez_finance is not correct", chargePage.compareEstimatedTillFleet(fleetId), true);
        checkAC("PaidTill in ez_finance is not correct", chargePage.comparePaidTillFleet(fleetId), true);
        checkAC("Charge was after test", chargePage.checkDateTimeDue(carrierIdString, fleetId, chargePage.runCronCheckDrivers()), false);
        checkAC("Charge was after test", chargePage.checkDateTimeDue(carrierIdString, fleetId, chargePage.runCronCheckFleet()), false);

    }

    @Test
    public  void chargeSoloTest() throws SQLException, IOException, ClassNotFoundException{

        int countScannerMonthToMonthTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, monthToMonthTariffId);
        int countDeactivatedScannerMonthToMonthTariff = utilsForDB.countDeactivatedChargeScannersMonthToMonth(userIdString, soloId);
        int countScannerOneYearTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, oneYearTariffId);
        int countScannerTwoYearsTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, twoYearsTariffId);

        int countMonthToMonthChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, monthToMonthTariffId);
        int countOneYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, oneYearTariffId);
        int countTwoYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, twoYearsTariffId);

        String setPaidTillForAllTariff = chargePage.paidTillForAllTariff();
        String setTariffStartMonth = chargePage.tariffStartForMonthToMonth(countMonthForTariffStartMonthToMonth);
        String setTariffStartOneYear = chargePage.tariffStartForOneYear(1);
        String setTariffStartTwoYears = chargePage.tariffStartForTwoYears(2);

        checkAC("No all tariffs are presented in eld scanners", chargePage.checkIfTariffPresent(countScannerMonthToMonthTariff, countScannerOneYearTariff, countScannerTwoYearsTariff), true);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartMonth, monthToMonthTariffId);
        utilsForDB.setOrderDateForMonthToMonth(userIdString, soloId, setTariffStartMonth);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsTariffId);

        String paidTillForEzFinances = chargePage.paidTillForEzFinances();
        utilsForDB.setPaidTillEstimatedTillEzFinancesSolo(soloId, paidTillForEzFinances, paidTillForEzFinances);

        checkAC("DateTime dues are not correct", chargePage.checkDateTimeDue(userIdString, soloId, chargePage.runCronCheckDrivers()), true);
        double sumDeactivatedScannerMonthToMonthTariff = chargePage.sumDeactivatedScannerMonthToMonthTariff(userIdString, soloId, countDeactivatedScannerMonthToMonthTariff);
        double sumCharge = chargePage.sumCharge(countScannerMonthToMonthTariff + countMonthToMonthChargeReturnedScanner, countScannerOneYearTariff + countOneYearChargeReturnedScanner, countScannerTwoYearsTariff + countTwoYearChargeReturnedScanner, sumDeactivatedScannerMonthToMonthTariff);
        checkAC("Charge due is not correct", chargePage.compareDueCharge(userIdString, soloId, sumCharge), true);

        checkAC("PaidTill for month-to-month is not correct", chargePage.comparePaidTillMonthToMonth(userIdString, soloId, monthToMonthTariffId), true);
        checkAC("PaidTill for one year is not correct", chargePage.comparePaidTillOneYear(userIdString, soloId, oneYearTariffId), true);
        checkAC("PaidTill for two years is not correct", chargePage.comparePaidTillTwoYears(userIdString, soloId, twoYearsTariffId), true);
        checkAC("EstimatedTill in eld_personal_finances is not correct", chargePage.compareEstimatedTillSolo(soloId), true);
        checkAC("PaidTill in eld_personal_finances is not correct", chargePage.comparePaidTillSolo(soloId), true);
        checkAC("Charge was after test", chargePage.checkDateTimeDue(userIdString, soloId, chargePage.runCronCheckDrivers()), false);
        checkAC("Charge was after test", chargePage.checkDateTimeDue(userIdString, soloId, chargePage.runCronCheckFleet()), false);

    }

}
