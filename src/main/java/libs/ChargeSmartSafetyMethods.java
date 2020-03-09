package libs;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

}
