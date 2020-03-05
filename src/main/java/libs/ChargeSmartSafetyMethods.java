package libs;

import io.qameta.allure.Step;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static libs.Prices.smartSafetyPrice;


public class ChargeSmartSafetyMethods {

    @Step
    public String buyServicesDateTime(){
        Instant instant = Instant.now();
        LocalDateTime datetime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC).minusMonths(1).minusMinutes(5);
        String formatted = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(datetime);
        return formatted;
    }
    @Step
    public String paidTillServicesDateTime(){
        Instant instant = Instant.now();
        LocalDateTime datetime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC).minusMinutes(5);
        String formatted = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(datetime);
        return formatted;
    }
    @Step
    public boolean compareSubscribedTill(LocalDateTime subscribedTill){
        LocalDateTime tariffStart = LocalDateTime.parse(LocalDateTime.now().plusMonths(1).toString());
        long diffHours = ChronoUnit.HOURS.between(tariffStart, subscribedTill);
        return diffHours < 2 && diffHours > (-2);
    }

    @Step
    public boolean compareBalance(double balanceBeforeTest, double balanceAfterTest){
        boolean temp = Math.round((balanceBeforeTest + balanceAfterTest) * 100.0) / 100.0 == smartSafetyPrice;
        return temp;
    }
}
