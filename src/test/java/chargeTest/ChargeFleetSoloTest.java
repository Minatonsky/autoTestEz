package chargeTest;

import org.junit.Test;
import parentTest.ParentChargeTest;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class ChargeFleetSoloTest extends ParentChargeTest{
    String monthToMonthTariffId = "0";
    String oneYearTariffId = "1";
    String twoYearsTariffId = "2";
    String carrierIdString = "carrierId";
    String fleetString = "fleet";
    String userIdString = "userId";

    String soloId = "3455";
    String fleetId = "570";

    public ChargeFleetSoloTest() throws IOException {
    }

    @Test
    public  void chargeFleetTest() throws SQLException, IOException, ClassNotFoundException, ParseException {

        int countScannerMonthToMonthTariff = utilsForDB.countActiveScannersWithTariffForFleet(fleetId, monthToMonthTariffId);
        int countScannerOneYearTariff = utilsForDB.countActiveScannersWithTariffForFleet(fleetId, oneYearTariffId);
        int countScannerTwoYearsTariff = utilsForDB.countActiveScannersWithTariffForFleet(fleetId, twoYearsTariffId);

        String setPaidTillForAllTariff = chargePage.paidTillForAllTariff();
        String setTariffStartMonth = chargePage.tariffStartForMonthToMonth();
        String setTariffStartOneYear = chargePage.tariffStartForOneYear();
        String setTariffStartTwoYears = chargePage.tariffStartForTwoYears();


        checkAC("No all tariffs are presented in eld scanners", chargePage.checkIfTariffPresent(countScannerMonthToMonthTariff, countScannerOneYearTariff, countScannerTwoYearsTariff), true);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartMonth, monthToMonthTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsTariffId);

        String paidTillForEzFinances = chargePage.paidTillForEzFinances();
        utilsForDB.setPaidTillEstimatedTillEzFinancesFleet(fleetId, paidTillForEzFinances, paidTillForEzFinances);

        checkAC("DateTime dues are not correct", chargePage.checkDateTimeDue(carrierIdString, fleetId, chargePage.runCronCheckFleet()), true);

        checkAC("Charge due is not correct", chargePage.compareDueChargeFleet(fleetId, countScannerMonthToMonthTariff, countScannerOneYearTariff, countScannerTwoYearsTariff), true);

        checkAC("PaidTill for month-to-month is not correct", chargePage.comparePaidTillMonthToMonthForFleet(fleetId, monthToMonthTariffId), true);
        checkAC("PaidTill for one year is not correct", chargePage.comparePaidTillOneYearForFleet(fleetId, oneYearTariffId), true);
        checkAC("PaidTill for two years is not correct", chargePage.comparePaidTillTwoYearsForFleet(fleetId, twoYearsTariffId), true);

    }

    @Test
    public  void chargeSoloTest() throws SQLException, IOException, ClassNotFoundException, ParseException {

        int countScannerMonthToMonthTariff = utilsForDB.countActiveScannersWithTariffForSolo(soloId, monthToMonthTariffId);
        int countScannerOneYearTariff = utilsForDB.countActiveScannersWithTariffForSolo(soloId, oneYearTariffId);
        int countScannerTwoYearsTariff = utilsForDB.countActiveScannersWithTariffForSolo(soloId, twoYearsTariffId);

        String setPaidTillForAllTariff = chargePage.paidTillForAllTariff();
        String setTariffStartMonth = chargePage.tariffStartForMonthToMonth();
        String setTariffStartOneYear = chargePage.tariffStartForOneYear();
        String setTariffStartTwoYears = chargePage.tariffStartForTwoYears();

        checkAC("No all tariffs are presented in eld scanners", chargePage.checkIfTariffPresent(countScannerMonthToMonthTariff, countScannerOneYearTariff, countScannerTwoYearsTariff), true);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartMonth, monthToMonthTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartOneYear, oneYearTariffId);
        utilsForDB.setPaidTillAndTariffStartScannerForSolo(soloId, setPaidTillForAllTariff, setTariffStartTwoYears, twoYearsTariffId);

        String paidTillForEzFinances = chargePage.paidTillForEzFinances();
        utilsForDB.setPaidTillEstimatedTillEzFinancesSolo(soloId, paidTillForEzFinances, paidTillForEzFinances);

        checkAC("DateTime dues are not correct", chargePage.checkDateTimeDue(userIdString, soloId, chargePage.runCronCheckDrivers()), true);
        checkAC("Charge due is not correct", chargePage.compareDueChargeSolo(soloId, countScannerMonthToMonthTariff, countScannerOneYearTariff, countScannerTwoYearsTariff), true);

        checkAC("PaidTill for month-to-month is not correct", chargePage.comparePaidTillMonthToMonthForSolo(soloId, monthToMonthTariffId), true);
        checkAC("PaidTill for one year is not correct", chargePage.comparePaidTillOneYearForSolo(soloId, oneYearTariffId), true);
        checkAC("PaidTill for two years is not correct", chargePage.comparePaidTillTwoYearsForSolo(soloId, twoYearsTariffId), true);

    }


}
