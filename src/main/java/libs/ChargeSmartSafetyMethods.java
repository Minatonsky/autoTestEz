package libs;

import io.qameta.allure.Step;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static libs.Prices.smartSafetyPrice;


public class ChargeSmartSafetyMethods {

    @Step
    public String buyServicesDateTime(){
        LocalDateTime tariffStart = LocalDateTime.parse(LocalDateTime.now().minusMonths(1).minusMinutes(10).toString());
        String tempDateTime = tariffStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return tempDateTime;
    }
    @Step
    public String paidTillServicesDateTime(){
        LocalDateTime tariffStart = LocalDateTime.parse(LocalDateTime.now().minusMinutes(10).toString());
        String tempDateTime = tariffStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return tempDateTime;
    }
    @Step
    public boolean compareSubscribedTill(LocalDateTime subscribedTill){
        LocalDateTime tariffStart = LocalDateTime.parse(LocalDateTime.now().plusMonths(1).toString());
        long diffHours = ChronoUnit.HOURS.between(tariffStart, subscribedTill);
        return diffHours < 2 && diffHours > (-2);
    }

    @Step
    public boolean compareBalance(double balanceBeforeTest, double balanceAfterTest){
        boolean temp = (balanceBeforeTest + balanceAfterTest) == smartSafetyPrice;
        return temp;
    }
}
