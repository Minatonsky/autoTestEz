package libs;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionsWithOurElements {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    WebDriverWait webDriverWait20;
    WebDriverWait webDriverWait40;

    public ActionsWithOurElements(WebDriver webDriver) {

        this.webDriver = webDriver;
        webDriverWait20 = new WebDriverWait(webDriver, 20);
        webDriverWait40 = new WebDriverWait(webDriver, 40);
    }

    public void enterTextToElement(WebElement webElement, String text){
        try{
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    public void clickOnElement(WebElement webElement){
        try{
            webDriverWait20.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    public boolean isElementEnable(WebElement webElement){
        try{
            webDriverWait20.until(ExpectedConditions.visibilityOf(webElement));
            boolean state = webElement.isEnabled();
            logger.info("Element is enabled - >" + state);
            return webElement.isEnabled();
        } catch (Exception e){
            logger.info("Element is enabled - > false");
            return false;
        }
    }

    public void setNeededStateToCheckBox(WebElement webElement, String neededState){
        if ("check".equals(neededState) || "uncheck".equals(neededState)){
            if (webElement.isSelected() && "check".equals(neededState)){
                logger.info("Checkbox is already checked");
            } else if (webElement.isSelected() && "uncheck".equals(neededState)){
                clickOnElement(webElement);
                logger.info("CheckBox unchecked");
            } else if (!webElement.isSelected() && "uncheck".equals(neededState)){
            logger.info("checkbox is already unchecked");
            } else if (!webElement.isSelected() && "check".equals(neededState)) {
            clickOnElement(webElement);
            logger.info("checkbox was checked");
            }

        }else {
            logger.error(String.format("%s - is not expected state", neededState));
            Assert.fail(String.format("%s - is not expected state", neededState));
        }
    }


    private void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element " + e);
        Assert.fail("Cannot work with element " + e);
    }

    public void selectValueInDropDown(WebElement dropDownElement, String value) {
        try {
            Select select = new Select(dropDownElement);
            select.selectByValue(value);
            logger.info(value + " was selected in Drop-down");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    public boolean isElementInOrder(String xPathLocator) {
        try {
            WebElement webElementInOrder =  webDriver.findElement(By.xpath(xPathLocator));
            webDriverWait20.until(ExpectedConditions.visibilityOf(webElementInOrder));
            if (webElementInOrder.isDisplayed()){
                logger.info("Element is displayed");
                return true;
            }else {
                return false;
            }
        } catch (Exception e){
            return false;
        }
    }

    public void clickOnElement(String xPathLocator) {
        try {
            WebElement webElement = webDriver.findElement(By.xpath(xPathLocator));
            clickOnElement(webElement);
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    public boolean isElementDisplay(WebElement webElement){
        try{
            boolean state = webElement.isDisplayed();
            logger.info("Element is display - > " + state );
            return state;
        }catch (Exception e){
            logger.info("Element is display - > false");
            return false;
        }
    }


}

