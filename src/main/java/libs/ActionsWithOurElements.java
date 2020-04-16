package libs;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionsWithOurElements {
    WebDriver webDriver;
    Actions action;
    Logger logger = Logger.getLogger(getClass());
    WebDriverWait webDriverWait20;
    WebDriverWait webDriverWait40;

    public ActionsWithOurElements(WebDriver webDriver) {

        this.webDriver = webDriver;
        webDriverWait20 = new WebDriverWait(webDriver, 20);
        webDriverWait40 = new WebDriverWait(webDriver, 40);
        action = new Actions(webDriver);
    }

    public void enterTextToElement(WebElement webElement, String text){
        try{
            webDriverWait20.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    public void clearElement(WebElement webElement){
        try{
            webDriverWait20.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            logger.info("WebElement was cleared");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    public void clearAndEnterTextToElement(WebElement webElement, String text){
        try{
            webDriverWait20.until(ExpectedConditions.visibilityOf(webElement));
            webElement.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
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

    public void submitOnElement(WebElement webElement){
        try{
            webDriverWait20.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.submit();
            logger.info("Element was clicked");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    public void doubleClickElement(WebElement webElement){
        try{
            webDriverWait20.until(ExpectedConditions.elementToBeClickable(webElement));
            action.doubleClick(webElement).perform();
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
        webDriverWait20.until(ExpectedConditions.elementToBeClickable(webElement));
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
            webDriverWait20.until(ExpectedConditions.elementToBeClickable(dropDownElement));
            Select select = new Select(dropDownElement);
            select.selectByValue(value);
            logger.info(value + " was selected in Drop-down");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }


    public void clickOnElement(String xPathLocator) {
        try {
            WebElement webElement = webDriver.findElement(By.xpath(xPathLocator));
            webDriverWait20.until(ExpectedConditions.elementToBeClickable(webElement));
            clickOnElement(webElement);
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    public void enterTextToElement(String xPathLocator, String text){
        try{
            WebElement webElement = webDriver.findElement(By.xpath(xPathLocator));
            webDriverWait20.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element");
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

    public void moveToElement(WebElement webElement){
        try {
            Actions builder = new Actions(webDriver);
            webDriverWait20.until(ExpectedConditions.visibilityOf(webElement));
            builder.build().perform();
            logger.info("Mouse over the element");

        }catch (Exception e){
            logger.info("Can not move to element");
        }
    }

    public void clickJsOnElement(WebElement webElement) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            executor.executeScript("arguments[0].click();", webElement);
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    public void enterTextOnElementJs(String xpath, String text){
        try {
            WebElement wb = webDriver.findElement(By.xpath(xpath));
            JavascriptExecutor jse = (JavascriptExecutor)webDriver;
            jse.executeScript("arguments[0].value=" + text + ";", wb);
            logger.info( text + " was input to element");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    public void sliderMove(String xPathLocator, int value){
        try {
            WebElement slider = webDriver.findElement(By.xpath(xPathLocator));
            int width = slider.getSize().getWidth();
            Actions act = new Actions(webDriver);
            act.moveToElement(slider, ((width * value) / 100), 0).click();
            act.build().perform();
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    public void scrollByVisibleElement(WebElement webElement){
        try {
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            executor.executeScript("arguments[0].scrollIntoView();", webElement);
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    public void clickOnBlankArea(){
        try{
            webDriver.findElement(By.xpath("//body")).click();
            logger.info("Element was clicked");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }

    }

    public void addFileByJs(WebElement webElement, String path){
        try{
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1])", webElement, "0");
            webElement.sendKeys(path);
        } catch (Exception e){
            printErrorAndStopTest(e);
        }

    }

}

