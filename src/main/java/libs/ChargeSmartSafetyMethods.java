package libs;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static libs.Prices.smartSafetyPrice;
import static libs.Utils.getLocalDateTimeUTC;


public class ChargeSmartSafetyMethods {
    Logger logger = Logger.getLogger(getClass());

    @Step
    public String buyServicesDateTime(){
        LocalDateTime datetime = getLocalDateTimeUTC().minusMonths(1).minusMinutes(5);
        String formatted = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(datetime);
        return formatted;
    }
    @Step
    public String paidTillServicesDateTime(){
        LocalDateTime datetime = getLocalDateTimeUTC().minusMinutes(5);
        String formatted = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(datetime);
        return formatted;
    }
    @Step
    public boolean compareSubscribedTill(LocalDateTime subscribedTill){
        LocalDateTime tempDateTime = getLocalDateTimeUTC().plusMonths(1);
        long diffMinutes = ChronoUnit.MINUTES.between(tempDateTime, subscribedTill);
        logger.info("diffMinutes " + diffMinutes);
        return diffMinutes < 2 && diffMinutes > (-2);
    }

    @Step
    public boolean compareBalance(double balanceBeforeTest, double balanceAfterTest){
        boolean temp = Math.round((balanceBeforeTest + balanceAfterTest) * 100.0) / 100.0 == smartSafetyPrice;
        return temp;
    }
}
