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
 * Order Confirmation Page encapsulate ALDI confirmation-registered page using composition with other pages/modules
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */
public class OrderConfirmationPage extends BasePage {

    public HeaderPage headerPage;
    public CommonModule commonModule;

    private static final String ERROR_MESSAGE = "Expected Element is not Displayed";

    @FindBy(className = ".waiticon-loading")
    private WebElement pageLoadGifElement;

    @FindBy(css = "div.col-md-8:nth-child(1) > h4:nth-child(1)")
    private WebElement txtOrderConfirmationMessage;

    @FindBy(css = "div.col-md-8:nth-child(1) > h4:nth-child(2)")
    private WebElement txtOrderID;

    public OrderConfirmationPage(WebDriver driver, int waitTimeInSeconds) {
        super(driver, waitTimeInSeconds);
    }

    public void initModules(WebDriver driver) {
        headerPage = new HeaderPage(driver, 5);
        commonModule = new CommonModule(driver, 5);
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitForCondition(webDriver, ExpectedConditions.visibilityOf(this.txtOrderConfirmationMessage),
                THIRTY_SECONDS, DEFAULT_TIMEOUT_MSG + this.txtOrderConfirmationMessage);
    }

    @Override
    public String getPageRelativeUrl() {
        return "/confirmation-registered";
    }

    public String getOrderConfirmationMessage() {
        String message = null;
        try {
            waitForCondition(getWebDriver(), ExpectedConditions.invisibilityOfAllElements(
                    pageLoadGifElement), 15, ERROR_MESSAGE);
        } catch (Exception e) {
            message = txtOrderConfirmationMessage.getText();
        }
        return message;
    }

    public String getOrderId() {
        return txtOrderID.getText();
    }
}
