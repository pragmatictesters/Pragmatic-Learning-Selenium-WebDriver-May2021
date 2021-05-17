package page.aldi.module;

import static helper.WaitingTimes.THIRTY_SECONDS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Login Page encapsulate ALDI login page using composition with other pages/modules
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */
public class Login extends HeaderPage {
    @FindBy(id = "login_email")
    private WebElement userName;

    @FindBy(id = "login_password")
    private WebElement password;

    @FindBy(xpath = "//BUTTON[text()='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//SMALL[text()='Incorrect email or password']")
    private WebElement loginErrorMessage;

    @FindBy(xpath = "//H3[text()='Register']")
    private WebElement registerModule;

    @FindBy(xpath = "//*[contains(text(),'Logout')]")
    private WebElement logoutButton;

    @FindBy(xpath = "//span[text()='Login']")
    private WebElement loginLink;

    @FindBy(xpath = "//button[text()='Register']")
    private WebElement registerButton;

    public Login(WebDriver driver, int waitTimeInSeconds) {
        super(driver, waitTimeInSeconds);
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitForCondition(webDriver, ExpectedConditions.visibilityOf(this.userName),
                THIRTY_SECONDS, DEFAULT_TIMEOUT_MSG + this.userName);
    }

    @Override
    public String getPageRelativeUrl() {
        return "/login";
    }

    public void enterUserName(String userName) {
        this.userName.sendKeys(userName);
    }

    public void enterPassword(String password) {
        this.password.sendKeys(password);
    }

    public String getLoginErrorMessage() {
        return this.loginErrorMessage.getText();
    }

    public void clickOnRegister() {
        registerModule.click();
    }

    public WebElement getLogoutLink() {
        return logoutButton;
    }

    public WebElement getLogInLink() {
        return loginLink;
    }

    public void clickOnRegisterButton() {
        this.registerButton.click();
    }

    public void clickOnLogoutButton() {
        this.logoutButton.click();
    }

}
