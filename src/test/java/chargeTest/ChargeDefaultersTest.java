package chargeTest;

import org.junit.Test;
import parentTest.ParentChargeTest;

import java.io.IOException;
import java.sql.SQLException;

public class ChargeDefaultersTest extends ParentChargeTest {
    String monthToMonthTariffId = "0";
    String oneYearTariffId = "1";
    String twoYearsTariffId = "2";
    String carrierIdString = "carrierId";
    String fleetString = "fleet";
    String userIdString = "userId";

    String soloId = "3824";
    String fleetId = "582";
    String fleetUserId = "3816";

    int countMonthForTariffStartMonthToMonth = 2;
    String currentDue = "0";


    @Test
    public void chargeDefaultersFleetTest() throws SQLException, IOException, ClassNotFoundException {
        utilsForDB.setCurrentDueForFleet(currentDue, fleetId);
        utilsForDB.setCurrentCard_0_Fleet(fleetId);
        int countScannerMonthToMonthTariff = utilsForDB.countChargeScannersWithTariff(fleetString, fleetId, monthToMonthTariffId);
        int countDeactivatedScannerMonthToMonthTariff = utilsForDB.countDeactivatedChargeScannersMonthToMonth(fleetString, fleetId);
        int countScannerOneYearTariff = utilsForDB.countChargeScannersWithTariff(fleetString, fleetId, oneYearTariffId);
        int countScannerTwoYearsTariff = utilsForDB.countChargeScannersWithTariff(fleetString, fleetId, twoYearsTariffId);

        int countMonthToMonthChargeReturnedScanner = utilsForDB.countChargeReturnedScanner(userIdString, fleetUserId, monthToMonthTariffId);
        int countOneYearChargeReturnedScanner = utilsForDB.countChargeReturnedScanner(userIdString, fleetUserId, oneYearTariffId);
        int countTwoYearChargeReturnedScanner = utilsForDB.countChargeReturnedScanner(userIdString, fleetUserId, twoYearsTariffId);

        String setPaidTillForAllTariff = chargePage.paidTillForAllTariff();
        String setTariffStartMonth = chargePage.tariffStartForMonthToMonth(countMonthForTariffStartMonthToMonth);
        String setTariffStartOneYear = chargePage.tariffStartForOneYear();
        String setTariffStartTwoYears = chargePage.tariffStartForTwoYears();


        checkAC("No all tariffs are presented in eld scanners", chargePage.checkIfTariffPresent(countScannerMonthToMonthTariff, countScannerOneYearTariff, countScannerTwoYearsTariff), true);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartMonth, monthToMonthTariffId);
        utilsForDB.setOrderDateForMonthToMonth(fleetString, fleetId, setTariffStartMonth);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsTariffId);

        String paidTillForEzFinances = chargePage.paidTillForEzFinances();
        utilsForDB.setPaidTillEstimatedTillEzFinancesFleet(fleetId, paidTillForEzFinances, paidTillForEzFinances);

        checkAC("DateTime dues are not correct", chargePage.checkDateTimeDue(carrierIdString, fleetId, chargePage.runCronCheckFleet()), true);
        double sumDeactivatedScannerMonthToMonthTariff = chargePage.sumDeactivatedScannerMonthToMonthTariff(carrierIdString, fleetId, countDeactivatedScannerMonthToMonthTariff);
        double sumCharge = chargePage.sumCharge(countScannerMonthToMonthTariff + countMonthToMonthChargeReturnedScanner, countScannerOneYearTariff + countOneYearChargeReturnedScanner, countScannerTwoYearsTariff + countTwoYearChargeReturnedScanner, sumDeactivatedScannerMonthToMonthTariff);

        checkAC("Charge due is not correct", chargePage.compareCurrentDueFleetDefaulters(fleetId, sumCharge), true);
        checkAC("Fleet is not in defaulters", utilsForDB.checkFleetInDefaulters(fleetId), true);
        chargePage.set_10_dayDefaulterFleet(fleetId);
        chargePage.runCronCheckFleet();

        chargePage.set_14_dayDefaulterFleet(fleetId);
        chargePage.runCronCheckFleet();
        chargePage.set_52_dayDefaulterFleet(fleetId);
        chargePage.runCronCheckFleet();

    }
    @Test
    public void makeFleetDefaulter() throws SQLException, IOException, ClassNotFoundException {
        utilsForDB.setCurrentDueForFleet(currentDue, fleetId);
        utilsForDB.setCurrentCard_0_Fleet(fleetId);
        String setPaidTillForAllTariff = chargePage.paidTillForAllTariff();
        String setTariffStartMonth = chargePage.tariffStartForMonthToMonth(countMonthForTariffStartMonthToMonth);
        String setTariffStartOneYear = chargePage.tariffStartForOneYear();
        String setTariffStartTwoYears = chargePage.tariffStartForTwoYears();
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartMonth, monthToMonthTariffId);
        utilsForDB.setOrderDateForMonthToMonth(fleetString, fleetId, setTariffStartMonth);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsTariffId);
        String paidTillForEzFinances = chargePage.paidTillForEzFinances();
        utilsForDB.setPaidTillEstimatedTillEzFinancesFleet(fleetId, paidTillForEzFinances, paidTillForEzFinances);

        chargePage.runCronCheckFleet();
        chargePage.set_10_dayDefaulterFleet(fleetId);
        chargePage.runCronCheckFleet();
        chargePage.set_14_dayDefaulterFleet(fleetId);
        chargePage.runCronCheckFleet();
        chargePage.set_52_dayDefaulterFleet(fleetId);
        chargePage.runCronCheckFleet();
    }
    @Test
    public void makeSoloDefaulter() throws SQLException, IOException, ClassNotFoundException {
        utilsForDB.setCurrentDueForSolo(currentDue, soloId);
        utilsForDB.setCurrentCard_0_Solo(soloId);
        String setPaidTillForAllTariff = chargePage.paidTillForAllTariff();
        String setTariffStartMonth = chargePage.tariffStartForMonthToMonth(countMonthForTariffStartMonthToMonth);
        String setTariffStartOneYear = chargePage.tariffStartForOneYear();
        String setTariffStartTwoYears = chargePage.tariffStartForTwoYears();
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartMonth, monthToMonthTariffId);
        utilsForDB.setOrderDateForMonthToMonth(userIdString, soloId, setTariffStartMonth);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsTariffId);
        String paidTillForEzFinances = chargePage.paidTillForEzFinances();
        utilsForDB.setPaidTillEstimatedTillEzFinancesSolo(soloId, paidTillForEzFinances, paidTillForEzFinances);

        chargePage.runCronCheckDrivers();
        chargePage.set_10_dayDefaulterSolo(soloId);
        chargePage.runCronCheckDrivers();
        chargePage.set_14_dayDefaulterSolo(soloId);
        chargePage.runCronCheckDrivers();
        chargePage.set_52_dayDefaulterSolo(soloId);
        chargePage.runCronCheckDrivers();
    }
}
