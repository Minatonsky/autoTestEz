package pages;

import libs.UtilsForDB;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static libs.Utils.*;

public class FinancesPage extends ParentPage {

    public FinancesPage(WebDriver webDriver, UtilsForDB utilsForDB) {
        super(webDriver, "/dash/finances/", utilsForDB);
    }

    @FindBy(xpath = ".//*[@id='current_due']")
    private WebElement financeBalanceText;

    @FindBy(xpath = ".//button[@onclick='payInvoice()']")
    private WebElement payCurrentInvoiceButton;

    @FindBy(xpath = ".//li[@data-payment-id='4']")
    private WebElement currentCreditCard;

    @FindBy(xpath = ".//button[@onclick='btnPayNow(this)']")
    private WebElement payNowButton;

    @FindBy(xpath = ".//button[@aria-label='Close']")
    private WebElement closeSuccessPopUp;

    @FindBy(xpath = "//a[@href='/dash/finances/finances_refunds/']")
    private WebElement refundsPage;

    @FindBy(xpath = "//a[@href='/dash/finances/finances_orders/']")
    private WebElement ordersPage;

    @FindBy(xpath = "//a[@href='/dash/finances/finances_cards/']")
    private WebElement cardsPage;

    @FindBy(xpath = "//button[@data-tutorial='addCreditCard']")
    private WebElement addCard;

    @FindBy(id = "creditCardNumber")
    private WebElement cardInput;

    @FindBy(id="cvv")
    private WebElement cvvInput;

    @FindBy(id="expiryDateYY")
    private WebElement expiryDateYYInput;

    @FindBy(id="expiryDateMM")
    private WebElement expiryDateMMInput;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement createButton;

    @FindBy(xpath = ".//*[@id='authorizeAddCardConfirmModal']//button[@type='submit']")
    private WebElement createButtonConfirm;

    @FindBy(xpath = "//button[@data-val='1']")
    private WebElement primaryCard;

    public String getFinanceBalance(){
        return financeBalanceText.getText();
    }
    public boolean compareBalance(String currentDue, String priceOrder, String usersBalance) {
        double tempBalance = Double.parseDouble(currentDue) - Double.parseDouble(priceOrder);
        if (Integer.parseInt(currentDue) > 0) {
            System.out.println("currentDue " + currentDue);
            System.out.println("priceOrder " + priceOrder);
            System.out.println("tempBalance " + tempBalance);
            System.out.println("usersBalance " + Double.parseDouble(usersBalance));
            return Double.parseDouble(usersBalance)*(-1) == tempBalance;
        } else if (Integer.parseInt(currentDue) == 0) {
            System.out.println("currentDue " + currentDue);
            System.out.println("priceOrder " + priceOrder);
            System.out.println("tempBalance " + tempBalance);
            System.out.println("usersBalance " + Double.parseDouble(usersBalance));
            return Double.parseDouble(usersBalance) == 0;
        } else return false;
    }
    public boolean compareBalanceIfCanceled(String currentDue, String priceOrder, String quantityOfDevices, String usersBalance) {
        double tempCurrentDue = Double.parseDouble(currentDue);
        double tempPriceOrder = Double.parseDouble(priceOrder);

        if (Integer.parseInt(quantityOfDevices) > 0) {
            if (Integer.parseInt(currentDue) > 0) {
                logger.info("usersBalance " + Double.parseDouble(usersBalance));
                return Double.parseDouble(usersBalance)*(-1) == tempCurrentDue;
            } else if (Integer.parseInt(currentDue) == 0) {
                logger.info("# FinanceBalance = " + Double.parseDouble(usersBalance));
                return Double.parseDouble(usersBalance)*(-1) == tempPriceOrder;
            } else return false;
        } else return true;

    }
    public boolean comparePaidOrderStatus(String orderStatus) {
        logger.info("orderStatus = " + orderStatus);
        return orderStatus.equals("3");
    }
    public boolean compareCancelOrderStatus(String orderStatus) {
        return orderStatus.equals("2");
    }
    public boolean compareCompletedOrderStatus(String orderStatus) {
        return orderStatus.equals("1");
    }
    public boolean compareNewOrderStatus(String orderStatus, String currentDue, String quantityOfDevices, String quantityCameraCP) {
        if (Integer.parseInt(currentDue) > 0 & Integer.parseInt(quantityOfDevices) == 0 & Integer.parseInt(quantityCameraCP) == 0){
            return orderStatus.equals("3");
        } else return orderStatus.equals("0");
    }
    public void payCurrentInvoiceForOrderByManager(String currentDue, String quantityOfDevices, String quantityCameraCP){
        if (Integer.parseInt(currentDue) == 0 & Integer.parseInt(quantityOfDevices) == 0 & Integer.parseInt(quantityCameraCP) == 0) {
            actionsWithOurElements.clickOnElement(payCurrentInvoiceButton);
            waitABit(3);
            actionsWithOurElements.clickOnElement(currentCreditCard);
            waitABit(3);
            actionsWithOurElements.clickOnElement(payNowButton);
            waitABit(5);
            actionsWithOurElements.clickOnElement(closeSuccessPopUp);
            logger.info("closeSuccessPopUp");
            waitABit(2);
            logger.info("Current Invoice was paid");
        } else logger.info("Do not need pay invoice");
    }
    public void goToRefunds(){ waitABit(2);actionsWithOurElements.clickOnElement(refundsPage);}
    public void goToOrders(){waitABit(2);actionsWithOurElements.clickOnElement(ordersPage);}
    public void goToCards(){waitABit(2);actionsWithOurElements.clickOnElement(cardsPage);}
    public void clickOnAddCard(){actionsWithOurElements.clickOnElement(addCard);}
    public void enterNumberCard(String number){actionsWithOurElements.enterTextToElement(cardInput, number);}
    public void enterCvv(String cvv){actionsWithOurElements.enterTextToElement(cvvInput, cvv);}
    public void enterExpiryDateYY(String expiryDateYY){actionsWithOurElements.enterTextToElement(expiryDateYYInput, expiryDateYY);}
    public void enterExpiryDateMM(String expiryDateMM){actionsWithOurElements.enterTextToElement(expiryDateMMInput, expiryDateMM);}
    public void clickOnCreateButton(){actionsWithOurElements.clickOnElement(createButton);}
    public void clickOnCreateButtonConfirm(){actionsWithOurElements.clickOnElement(createButtonConfirm);}
    public void clickOnPrimaryCard(){actionsWithOurElements.clickOnElement(primaryCard);}
    public boolean compareBalanceIfCanceledManager(String currentDue, String priceOrder, String userBalance) {
        double tempCurrentDue = Double.parseDouble(currentDue);
        double tempPriceOrder = Double.parseDouble(priceOrder);

            if (Integer.parseInt(currentDue) > 0) {
                logger.info("usersBalance " + Double.parseDouble(userBalance));
                logger.info("CurrentDue " + tempCurrentDue);
                return Double.parseDouble(userBalance)*(-1) == tempCurrentDue;
            } else if (Integer.parseInt(currentDue) == 0) {
                logger.info("# FinanceBalance = " + Double.parseDouble(userBalance));
                logger.info("# tempPriceOrder = " + tempPriceOrder);
                return Double.parseDouble(userBalance)*(-1) == tempPriceOrder;
        } else return false;
    }

    public LocalDateTime getDateTimeLastDue(String fleetSolo, String fleetId) throws SQLException {
        List<ArrayList> ezDueList = utilsForDB.getLastDueDataList(fleetSolo, fleetId);
        Map<String, Object> ezDueMap = listArrayToMap(ezDueList);
        String dateTimeLD = ezDueMap.get("dateTime").toString();
        return getLocalDateTimeFromString(dateTimeLD);
    }
}
