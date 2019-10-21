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
        String setTariffStartMonth = chargePage.tariffStartForMonthToMonth(1);
        utilsForDB.setPaidTillAndTariffStartScannerForFleet(fleetId, setPaidTillForAllTariff, setTariffStartMonth, monthToMonthTariffId);
        utilsForDB.setOrderDateForMonthToMonth(fleetString, fleetId, setTariffStartMonth);
        utilsForDB.delete_102_Status(fleetString, fleetId, monthToMonthTariffId);
        String paidTillForEzFinances = chargePage.paidTillForEzFinances();
        utilsForDB.setPaidTillEstimatedTillEzFinancesFleet(fleetId, paidTillForEzFinances, paidTillForEzFinances);
        checkAC("DateTime dues are not correct", chargePage.checkDateTimeDueMonthToMonth(carrierIdString, fleetId, chargePage.runCronCheckFleet()), true);
        double sumDeactivatedScannerMonthToMonthTariff = chargePage.sumDeactivatedScannerMonthToMonthTariff(fleetString, fleetId, countDeactivatedScannerMonthToMonthTariff);
        checkAC("Charge due is not correct", chargePage.compareDueChargeMonthToMonthTariff(carrierIdString, fleetId, countScannerMonthToMonthTariff + countMonthToMonthChargeReturnedScanner, sumDeactivatedScannerMonthToMonthTariff), true);

        checkAC("PaidTill for month-to-month is not correct", chargePage.comparePaidTillMonthToMonth(fleetString, fleetId, monthToMonthTariffId), true);
        checkAC("EstimatedTill in ez_finance is not correct", chargePage.compareEstimatedTillFleet(fleetId), true);
        checkAC("PaidTill in ez_finance is not correct", chargePage.comparePaidTillFleet(fleetId), true);



    }
}
