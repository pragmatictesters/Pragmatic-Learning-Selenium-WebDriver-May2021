package page.aldi.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.aldi.module.CommonModule;
import page.aldi.module.HeaderPage;
import page.base.BasePage;

import static helper.WaitingTimes.THIRTY_SECONDS;

/**
 * Payment details encapsulate ALDI Payment details using composition with other pages/modules
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */
public class PaymentDetailsPage extends BasePage {

    public HeaderPage headerPage;
    public CommonModule commonModule;

    private static final String ERROR_MESSAGE = "Expected Element is not Displayed";

    @FindBy(className = ".waiticon-loading")
    private WebElement pageLoadGifElement;

    @FindBy(css = "#paymentMethodBlock > span > span.selection > span")
    private WebElement paymentOption;

    @FindBy(id = "cardNumber")
    private WebElement cardNumber;

    @FindBy(id = "cardholderName")
    private WebElement cardHolderName;

    @FindBy(id = "expiryMonth")
    private WebElement expireMonth;

    @FindBy(id = "expiryYear")
    private WebElement expireYear;

    @FindBy(css = "#cardCode_masked")
    private WebElement cardCode;

    @FindBy(css = "nextBtn")
    private WebElement continueButton;

    public PaymentDetailsPage(WebDriver driver, int waitTimeInSeconds) {
        super(driver, waitTimeInSeconds);
    }

    public void initModules(WebDriver driver) {
        headerPage = new HeaderPage(driver, 5);
        commonModule = new CommonModule(driver, 5);
    }

    @Override
    public void waitUntilPageIsLoaded() {
        try {
            waitForCondition(getWebDriver(), ExpectedConditions.invisibilityOfAllElements(
                    pageLoadGifElement), 15, ERROR_MESSAGE);
        } catch (Exception e) {
            waitForCondition(webDriver, ExpectedConditions.visibilityOf(this.paymentOption),
                    THIRTY_SECONDS, DEFAULT_TIMEOUT_MSG + this.paymentOption);
        }
    }

    @Override
    public String getPageRelativeUrl() {
        return "/checkout";
    }

    public void clickOnPaymentMethod(){
        try {
            waitForCondition(getWebDriver(), ExpectedConditions.invisibilityOfAllElements(
                    pageLoadGifElement), 15, ERROR_MESSAGE);
        } catch (Exception e) {
            paymentOption.click();
        }
    }

    public void enterCardNumber(String cardNumber){
        this.cardNumber.sendKeys(cardNumber);
    }

    public void enterCardHolderName(String cardHolderName){
        this.cardHolderName.sendKeys(cardHolderName);
    }

    public void clickOnExpireMonth(){
        this.expireMonth.click();
    }

    public void clickOnExpireYear(){
        this.expireYear.click();
    }

    public void enterCardCod(String cardCode){
        this.cardCode.sendKeys(cardCode);
    }

    public void clickOnContinueButton(){
        this.continueButton.click();
    }
}
