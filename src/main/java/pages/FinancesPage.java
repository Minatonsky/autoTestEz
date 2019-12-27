package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.Utils.waitABit;

public class FinancesPage extends ParentPage {

    public FinancesPage(WebDriver webDriver) {
        super(webDriver, "/dash/finances/");
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

    public String getFinanceBalance(){
        return financeBalanceText.getText();
    }

    public boolean compareBalance(String currentDue, String dueForLastOrder) {
        double tempBalance = Double.parseDouble(currentDue) - Double.parseDouble(dueForLastOrder);
        if (Integer.parseInt(currentDue) > 0) {
            return Double.parseDouble(getFinanceBalance().substring(1)) == tempBalance;
        } else if (Integer.parseInt(currentDue) == 0) {
            System.out.println(getFinanceBalance().substring(1));
            System.out.println(getFinanceBalance());
            return Double.parseDouble(getFinanceBalance().substring(1)) == 0;
        } else return false;
    }
    public boolean compareBalanceIfCanceled(String currentDue, String dueForLastOrder, String quantityOfDevices) {
        double tempBalance1 = Integer.parseInt(currentDue);
        double tempDueForLastOrder = Double.parseDouble(dueForLastOrder);
        double moduleTempDueForLastOrder = Math.abs(tempDueForLastOrder);
        if (Integer.parseInt(quantityOfDevices) > 0) {
            if (Integer.parseInt(currentDue) > 0) {
                return Double.parseDouble(getFinanceBalance().substring(1)) == tempBalance1;
            } else if (Integer.parseInt(currentDue) == 0) {
                return Double.parseDouble(getFinanceBalance().substring(1)) == moduleTempDueForLastOrder;
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

}
