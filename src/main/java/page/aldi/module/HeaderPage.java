package page.aldi.module;

import static helper.WaitingTimes.THIRTY_SECONDS;
import static helper.enumhelper.time.TWO_THOUSAND_MILLISECOND;

import helper.CommonHelper;
import helper.WebElementHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.base.BasePage;

import java.util.List;

/**
 * Header module Page encapsulate ALDI Header page using composition with other pages/modules
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */
public class HeaderPage extends BasePage {

    @FindBy(xpath = "//SPAN[text()='Login']")
    private WebElement loginLink;

    @FindBy(xpath = "//SPAN[text()=' Logout']")
    private WebElement logOutLink;

    @FindBy(xpath = "//A[text()=' Special Buys ™']")
    private WebElement specialBysLink;

    @FindBy(xpath = "//*[@id=\"main_nav\"]/ul/li/div/div/div[1]/ul/li/a/span")
    private WebElement productLink;

    @FindBy(css = "div.flex-grow-1:nth-child(2)")
    private WebElement cartPrice;

    @FindBy(xpath = "//SMALL[text()='showing 1 item']")
    private WebElement cartItem;

    @FindBy(xpath = "item-wrapper")
    private List<WebElement> cartItemList;

    @FindBy(xpath = "//*[@id=\"header_search_input\"]")
    private WebElement searchBar;

    @FindBy(xpath = "//SPAN[@id='header_search_btn']")
    private WebElement searchButton;

    public HeaderPage(WebDriver driver, int waitTimeInSeconds) {
        super(driver, waitTimeInSeconds);
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitForCondition(webDriver, ExpectedConditions.visibilityOf(this.loginLink),
                THIRTY_SECONDS, DEFAULT_TIMEOUT_MSG + this.loginLink);
    }

    @Override
    public String getPageRelativeUrl() {
        return null;
    }

    public void navigateToProductList() {
        waitForCondition(webDriver, ExpectedConditions.visibilityOf(this.logOutLink),
                THIRTY_SECONDS, DEFAULT_TIMEOUT_MSG + this.logOutLink);
        this.specialBysLink.click();
        this.productLink.click();
    }

    public String getCartPrice() {
        CommonHelper.sleep(TWO_THOUSAND_MILLISECOND);
        return this.cartPrice.getText();
    }

    public void mouseHoverOnCart() {
        Actions actions = new Actions(getWebDriver());
        actions.clickAndHold(cartPrice).perform();
    }

    public void clickOnCart() {
        cartPrice.click();
    }

    public WebElement getCartItem() {
        return this.cartItem;
    }

    public List<WebElement> getCartItemList() {
        return cartItemList;
    }

    public void enterSearchText(String searchText) {
        this.searchBar.sendKeys(searchText);
    }

    public void clickOnSearchButton() {
        this.searchButton.click();
    }

    public void clickOnLoginLink() {
        loginLink.click();
    }


}
