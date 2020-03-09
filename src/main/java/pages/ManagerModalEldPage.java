package pages;

import io.qameta.allure.Step;
import libs.Database;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.Utils.waitABit;

public class ManagerModalEldPage extends ParentPage {

    @FindBy(id = "checkboxFleetOrSolo")
    private WebElement soloCheckBox;

    @FindBy(id = "solo_driver_select")
    private WebElement soloDriverSelect;

    @FindBy(id = "soloDriverList")
    private WebElement soloDriverList;

    @FindBy(id = "fleet_select")
    private WebElement usdotFleetInput;

    @FindBy(id = "fleetList")
    private WebElement fleetInFleetList;

    @FindBy(id = "user_select")
    private WebElement userSelectField;

    @FindBy(xpath = "//*[@id='userList']/li[1]")
    private WebElement userList;


    public ManagerModalEldPage(WebDriver webDriver, Database dBMySQL) {
        super(webDriver, "/dash/eld/", dBMySQL);
    }

/*
SELECT FLEET/SOLO IN ORDER
 */
    public void clickOnUsdotField(){
        actionsWithOurElements.clickOnElement(usdotFleetInput);
    }

    public void enterUsdotFleet(String usdotFleet){
        actionsWithOurElements.enterTextToElement(usdotFleetInput, usdotFleet);
    }

    public void clickOnFleetInFleetList(){
        actionsWithOurElements.clickOnElement(fleetInFleetList);
    }

    public void clickOnUserField(){
        actionsWithOurElements.clickOnElement(userSelectField);
    }

    public void selectUser(){
        actionsWithOurElements.clickOnElement(userList);
    }

    @Step
    public void selectFleetInOrder(String usdotFleet) throws InterruptedException {
        waitABit(2);
        clickOnUsdotField();
        waitABit(2);
        enterUsdotFleet(usdotFleet);
        waitABit(2);
        clickOnFleetInFleetList();
        waitABit(2);
        clickOnUserField();
        waitABit(2);
        selectUser();
    }


    public void clickSoloCheckBox(){
        actionsWithOurElements.clickOnElement(soloCheckBox);
    }

    public void clickSoloDriverField(){
        actionsWithOurElements.clickOnElement(soloDriverSelect);
    }

    public void enterSoloDriverEmail(String soloLogin){
        actionsWithOurElements.enterTextToElement(soloDriverSelect ,soloLogin);
    }

    public void clickSoloDriverList(){
        actionsWithOurElements.clickOnElement(soloDriverList);
    }

    @Step
    public void selectSoloDriverInOrder(String soloLogin){
        waitABit(2);
        clickSoloCheckBox();
        waitABit(2);
        clickSoloDriverField();
        waitABit(2);
        enterSoloDriverEmail(soloLogin);
        waitABit(2);
        clickSoloDriverList();

    }
}