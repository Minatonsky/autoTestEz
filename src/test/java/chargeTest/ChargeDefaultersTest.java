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
    String fleetId = "581";
    String fleetUserId = "3816";

    int countMonthForTariffStartMonthToMonth = 3;
    int countYearOneYearSubscr = 1;
    int countYearTwoYearSubscr = 2;
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
        String setTariffStartOneYear = chargePage.tariffStartForOneYear(countYearOneYearSubscr);
        String setTariffStartTwoYears = chargePage.tariffStartForTwoYears(countYearTwoYearSubscr);


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
        List<String> listOfStatusDevices =  utilsForDB.getIdScannersByStatus(fleetString, fleetId, "4");
        String stringOfStatusesDevices = String.join(",", listOfStatusDevices);
        checkAC("Late Fee is not correct", chargePage.checkLateFeeFleet(fleetId, sumCharge, fleetString), true);

        chargePage.setDaysDefaulterFleet(fleetId, 15);
        chargePage.runCronCheckFleet();
        checkAC("Devices does not have status Not Paid", chargePage.checkStatusesDevices(listOfStatusDevices, "8"), true);
        checkAC("Fleet is not deactivated", utilsForDB.checkFleetIsDeactivated(fleetId), true);
        String currentDueWithLateFee = utilsForDB.getCurrentDueEzFinancesFleet(fleetId);
        chargePage.setDaysDefaulterFleet(fleetId, 52);
        chargePage.runCronCheckFleet();
        String currentDueWithLateReturnedProratedFee = utilsForDB.getCurrentDueEzFinancesFleet(fleetId);
        int countDevicesAfter_12Month = chargePage.countScannersByTariffStart(listOfStatusDevices);
        checkAC("ProratedAndNotReturnedFee is not correct", chargePage.checkProratedAndNotReturnedFee(listOfStatusDevices, currentDueWithLateFee, currentDueWithLateReturnedProratedFee, countDevicesAfter_12Month), true);
        checkAC("Devices does not have status Not disconnected", chargePage.checkStatusesDevices(listOfStatusDevices, "12"), true);
        checkAC("Fleet is not Banned", utilsForDB.checkFleetIsBanned(fleetId), true);
        utilsForDB.setCurrentCard(carrierIdString, fleetId);
        utilsForDB.setUnbanFleet(fleetId);
        utilsForDB.set_0_DeactivatedFleet(fleetId);
        utilsForDB.setStatusesForDevices(stringOfStatusesDevices, "8");
        utilsForDB.setCurrentDueForFleet("0", fleetId);
        chargePage.runCronCheckFleet();

    }
    @Test
    public void chargeDefaultersSoloTest() throws SQLException, IOException, ClassNotFoundException {
        utilsForDB.setCurrentDueForSolo(currentDue, soloId);
        utilsForDB.setCurrentCard_0_Solo(soloId);
        int countScannerMonthToMonthTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, monthToMonthTariffId);
        int countDeactivatedScannerMonthToMonthTariff = utilsForDB.countDeactivatedChargeScannersMonthToMonth(userIdString, soloId);
        int countScannerOneYearTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, oneYearTariffId);
        int countScannerTwoYearsTariff = utilsForDB.countChargeScannersByTariff(userIdString, soloId, twoYearsTariffId);
        System.out.println("countScannerTwoYearsTariff = " + countScannerTwoYearsTariff);

        int countMonthToMonthChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, monthToMonthTariffId);
        int countOneYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, oneYearTariffId);
        int countTwoYearChargeReturnedScanner = utilsForDB.countChargeReturnedScannerByTariff(userIdString, soloId, twoYearsTariffId);
        System.out.println("countTwoYearChargeReturnedScanner = " + countTwoYearChargeReturnedScanner);

        String setPaidTillForAllTariff = chargePage.paidTillForAllTariff();
        String setTariffStartMonth = chargePage.tariffStartForMonthToMonth(countMonthForTariffStartMonthToMonth);
        String setTariffStartOneYear = chargePage.tariffStartForOneYear(countYearOneYearSubscr);
        String setTariffStartTwoYears = chargePage.tariffStartForTwoYears(countYearTwoYearSubscr);


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

        checkAC("Charge due is not correct", chargePage.compareCurrentDueSoloDefaulters(soloId, sumCharge), true);
        checkAC("Fleet is not in defaulters", utilsForDB.checkSoloInDefaulters(soloId), true);

        chargePage.setDaysDefaulterSolo(soloId, 10);
        chargePage.runCronCheckDrivers();
        List<String> listOfStatusDevices =  utilsForDB.getIdScannersByStatus(userIdString, soloId, "4");
        String stringOfStatusesDevices = String.join(",", listOfStatusDevices);
        checkAC("Late Fee is not correct", chargePage.checkLateFeeSolo(soloId, sumCharge, userIdString), true);

        chargePage.setDaysDefaulterSolo(soloId, 15);
        chargePage.runCronCheckDrivers();

        checkAC("Devices does not have status Not Paid", chargePage.checkStatusesDevices(listOfStatusDevices, "8"), true);
        checkAC("Fleet is not deactivated", utilsForDB.checkSoloIsDeactivated(soloId), true);
        String currentDueWithLateFee = utilsForDB.getCurrentDueEzFinancesSolo(soloId);
        chargePage.setDaysDefaulterSolo(soloId, 52);
        chargePage.runCronCheckDrivers();
        String currentDueWithLateReturnedProratedFee = utilsForDB.getCurrentDueEzFinancesSolo(soloId);
        int countDevicesAfter_12Month = chargePage.countScannersByTariffStart(listOfStatusDevices);
        checkAC("ProratedAndNotReturnedFee is not correct", chargePage.checkProratedAndNotReturnedFee(listOfStatusDevices, currentDueWithLateFee, currentDueWithLateReturnedProratedFee, countDevicesAfter_12Month), true);
        checkAC("Devices does not have status Not disconnected", chargePage.checkStatusesDevices(listOfStatusDevices, "12"), true);
        checkAC("Fleet is not Banned", utilsForDB.checkSoloIsBanned(soloId), true);
        utilsForDB.setCurrentCard(userIdString, soloId);
        utilsForDB.setUnbanSolo(soloId);
        utilsForDB.set_0_DeactivatedSolo(soloId);
        utilsForDB.setStatusesForDevices(stringOfStatusesDevices, "8");
        utilsForDB.setCurrentDueForSolo("0", soloId);
        chargePage.runCronCheckDrivers();

    }
    @Test
    public void makeFleetDefaulter() throws SQLException, IOException, ClassNotFoundException {
        utilsForDB.setCurrentDueForFleet(currentDue, fleetId);
        utilsForDB.setCurrentCard_0_Fleet(fleetId);
        String setPaidTillForAllTariff = chargePage.paidTillForAllTariff();
        String setTariffStartMonth = chargePage.tariffStartForMonthToMonth(countMonthForTariffStartMonthToMonth);
        String setTariffStartOneYear = chargePage.tariffStartForOneYear(2);
        String setTariffStartTwoYears = chargePage.tariffStartForTwoYears(1);
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
        chargePage.setDaysDefaulterSolo(soloId, 52);
        chargePage.runCronCheckDrivers();
    }
}
