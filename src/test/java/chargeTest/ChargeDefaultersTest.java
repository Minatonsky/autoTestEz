package chargeTest;

import org.junit.Test;
import parentTest.ParentChargeTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ChargeDefaultersTest extends ParentChargeTest {
    String monthToMonthTariffId = "0";
    String oneYearTariffId = "1";
    String twoYearsTariffId = "2";
    String carrierIdString = "carrierId";
    String fleetString = "fleet";
    String userIdString = "userId";

    String soloId = "3854";
    String fleetId = "582";
    String fleetUserId = "3816";

    int countMonthForTariffStartMonthToMonth = 2;
    String currentDue = "0";


    @Test
    public void chargeDefaultersFleetTest() throws SQLException, IOException, ClassNotFoundException {
        utilsForDB.setCurrentDueForFleet(currentDue, fleetId);
        utilsForDB.setCurrentCard_0_Fleet(fleetId);
        int countScannerMonthToMonthTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, monthToMonthTariffId);
        int countDeactivatedScannerMonthToMonthTariff = utilsForDB.countDeactivatedChargeScannersMonthToMonth(fleetString, fleetId);
        int countScannerOneYearTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, oneYearTariffId);
        int countScannerTwoYearsTariff = utilsForDB.countChargeScannersByTariff(fleetString, fleetId, twoYearsTariffId);
        System.out.println("countScannerTwoYearsTariff = " + countScannerTwoYearsTariff);

        int countMonthToMonthChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, monthToMonthTariffId);
        int countOneYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, oneYearTariffId);
        int countTwoYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(fleetString, fleetId, twoYearsTariffId);
        System.out.println("countTwoYearChargeReturnedScanner = " + countTwoYearChargeReturnedScanner);

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
        double sumDeactivatedScannerMonthToMonthTariff = chargePage.sumDeactivatedScannerMonthToMonthTariff(carrierIdString, fleetId, countDeactivatedScannerMonthToMonthTariff);
        double sumCharge = chargePage.sumCharge(countScannerMonthToMonthTariff + countMonthToMonthChargeReturnedScanner, countScannerOneYearTariff + countOneYearChargeReturnedScanner, countScannerTwoYearsTariff + countTwoYearChargeReturnedScanner, sumDeactivatedScannerMonthToMonthTariff);

        checkAC("Charge due is not correct", chargePage.compareCurrentDueFleetDefaulters(fleetId, sumCharge), true);
        checkAC("Fleet is not in defaulters", utilsForDB.checkFleetInDefaulters(fleetId), true);

        chargePage.setDaysDefaulterFleet(fleetId, 10);
        chargePage.runCronCheckFleet();
        List<String> listOfActiveDevices =  utilsForDB.getIdScannersByStatus(fleetString, fleetId, "4");
        checkAC("Late Fee is not correct", chargePage.checkLateFeeFleet(fleetId, sumCharge, fleetString), true);

        chargePage.setDaysDefaulterFleet(fleetId, 15);
        chargePage.runCronCheckFleet();
        checkAC("Devices does not have status Not Paid", chargePage.checkStatusesActiveDevices(listOfActiveDevices, "8"), true);
        checkAC("Fleet is not deactivated", utilsForDB.checkFleetIsDeactivated(fleetId), true);

        chargePage.setDaysDefaulterFleet(fleetId, 52);
        chargePage.runCronCheckFleet();
        chargePage.checkProratedAndNotReturnedFee(listOfActiveDevices);
//        chargePage.checkFleetIsBan();

    }
    @Test
    public void makeFleetDefaulter() throws SQLException, IOException, ClassNotFoundException {
        utilsForDB.setCurrentDueForFleet(currentDue, fleetId);
        utilsForDB.setCurrentCard_0_Fleet(fleetId);
        String setPaidTillForAllTariff = chargePage.paidTillForAllTariff();
        String setTariffStartMonth = chargePage.tariffStartForMonthToMonth(countMonthForTariffStartMonthToMonth);
        String setTariffStartOneYear = chargePage.tariffStartForOneYear(1);
        String setTariffStartTwoYears = chargePage.tariffStartForTwoYears(2);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartMonth, monthToMonthTariffId);
        utilsForDB.setOrderDateForMonthToMonth(fleetString, fleetId, setTariffStartMonth);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsTariffId);
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
        String setTariffStartMonth = chargePage.tariffStartForMonthToMonth(countMonthForTariffStartMonthToMonth);
        String setTariffStartOneYear = chargePage.tariffStartForOneYear(1);
        String setTariffStartTwoYears = chargePage.tariffStartForTwoYears(2);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartMonth, monthToMonthTariffId);
        utilsForDB.setOrderDateForMonthToMonth(userIdString, soloId, setTariffStartMonth);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsTariffId);
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
//        chargePage.setDaysDefaulterSolo(soloId, 52);
//        chargePage.runCronCheckDrivers();
    }
}
