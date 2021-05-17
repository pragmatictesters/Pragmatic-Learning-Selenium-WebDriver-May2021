package page.aldi.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.aldi.module.CommonModule;
import page.aldi.module.HeaderPage;
import page.base.BasePage;

import static helper.WaitingTimes.THIRTY_SECONDS;

/**
 * Your Basket Page encapsulate ALDI Basket page using composition with other pages/modules
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */
public class CheckOutPage extends BasePage {

    public HeaderPage headerPage;
    public CommonModule commonModule;

    private static final String ERROR_MESSAGE = "Expected Element is not Displayed";

    @FindBy(xpath = "//*[contains(text(),'change delivery details')]")
    private WebElement lnkChangeDeliveryDetails;

    @FindBy(className = ".waiticon-loading")
    private WebElement pageLoadGifElement;

    @FindBy(id = "fName")
    private WebElement txtFirstName;

    @FindBy(id = "sName")
    private WebElement txtSurName;

    @FindBy(id = "mobile")
    private WebElement txtMobile;

    @FindBy(id = "address")
    private WebElement txtAddress;

    @FindBy(css = "div.cart-sidebar-heading:nth-child(2) > span:nth-child(2)")
    private WebElement deliveryFee;

    @FindBy(css = "div.cart-sidebar-heading:nth-child(1) > span:nth-child(2)")
    private WebElement itemPrice;

    @FindBy(id = "checkout_cart_value")
    private WebElement outstandingToPay;

    public CheckOutPage(WebDriver driver, int waitTimeInSeconds) {
        super(driver, waitTimeInSeconds);
    }

    public void initModules(WebDriver driver) {
        headerPage = new HeaderPage(driver, 5);
        commonModule = new CommonModule(driver, 5);
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitForCondition(webDriver, ExpectedConditions.visibilityOf(this.lnkChangeDeliveryDetails),
                THIRTY_SECONDS, DEFAULT_TIMEOUT_MSG + this.lnkChangeDeliveryDetails);
    }

    @Override
    public String getPageRelativeUrl() {
        return "/checkout";
    }

    public void clickOnChangeDeliveryDetails() {
        try {
            waitForCondition(getWebDriver(), ExpectedConditions.invisibilityOfAllElements(
                    pageLoadGifElement), 15, ERROR_MESSAGE);
        } catch (Exception e) {
            lnkChangeDeliveryDetails.click();
        }
    }

    public void enterFirstName(String firstName) {
        this.txtFirstName.sendKeys(firstName);
    }

    public void enterSurName(String surName) {
        this.txtSurName.sendKeys(surName);
    }

    public void enterMobile(String mobile) {
        this.txtMobile.sendKeys(mobile);
    }

    public void enterAddress(String address) {
        this.txtAddress.sendKeys(address);
    }

    public String getItemPrice() {
        String itemPrice = null;
        try {
            waitForCondition(getWebDriver(), ExpectedConditions.invisibilityOfAllElements(
                    pageLoadGifElement), 15, ERROR_MESSAGE);
        } catch (Exception e) {
            itemPrice = this.itemPrice.getText();
        }
        return itemPrice;
    }

    public String getDeliveryFee() {
        return this.deliveryFee.getText();
    }

    public String getOutstandingToPay() {
        return this.outstandingToPay.getText();
    }
}
