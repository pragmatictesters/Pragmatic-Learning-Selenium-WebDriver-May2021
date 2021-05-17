package page.aldi.module;

import static helper.WaitingTimes.THIRTY_SECONDS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.aldi.module.HeaderPage;

/**
 * Login Page encapsulate ALDI Registration page using composition with other pages/modules
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */
public class Registration extends HeaderPage {
    @FindBy(id = "emailAddress")
    private WebElement emailAddress;

    @FindBy(id = "userPass")
    private WebElement password;

    @FindBy(id = "userPassRepeat")
    private WebElement confirmPassword;

    @FindBy(xpath = "//SMALL[text()='Incorrect email or password']")
    private WebElement loginErrorMessage;

    @FindBy(id = "condition")
    private WebElement userConditions;

    @FindBy(xpath = "//BUTTON[text()='Register']")
    private WebElement btnRegister;

    @FindBy(id = "subscribe")
    private WebElement userSubscribe;

    public Registration(WebDriver driver, int waitTimeInSeconds) {
        super(driver, waitTimeInSeconds);
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitForCondition(webDriver, ExpectedConditions.visibilityOf(this.emailAddress),
                THIRTY_SECONDS, DEFAULT_TIMEOUT_MSG + this.emailAddress);
    }

    @Override
    public String getPageRelativeUrl() {
        return "/login";
    }

    public void enterEmail(String userName) {
        this.emailAddress.sendKeys(userName);
    }

    public void enterPassword(String password) {
        this.password.sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        this.confirmPassword.sendKeys(password);
    }

    public void clickOnUserCondition() {
        this.userConditions.click();
    }

    public void clickRegisterButton() {
        this.btnRegister.click();
    }

    public void clickOnSubscribe() {
        this.userSubscribe.click();
    }
}
