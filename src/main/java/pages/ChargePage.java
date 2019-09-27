package pages;

import libs.ActionsWithOurElements;
import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class ChargePage {
    ActionsWithOurElements actionsWithOurElements;
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    protected static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);


}
