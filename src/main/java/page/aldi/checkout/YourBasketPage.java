package page.aldi.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.aldi.module.HeaderPage;

import java.util.List;

import static helper.WaitingTimes.THIRTY_SECONDS;

/**
 * Your Basket Page encapsulate ALDI Basket page using composition with other pages/modules
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */
public class YourBasketPage extends HeaderPage {

  @FindBy(css = "button.cursor-pointer:nth-child(6)")
  private WebElement btnProceedWithCheckOut;

  public YourBasketPage(WebDriver driver, int waitTimeInSeconds) {
    super(driver, waitTimeInSeconds);
    initModules(driver);
  }

  public HeaderPage headerPage;

  public void initModules(WebDriver driver) {
    headerPage = new HeaderPage(driver, 5);
  }

  @Override
  public void waitUntilPageIsLoaded() {
    waitForCondition(webDriver, ExpectedConditions.visibilityOf(this.btnProceedWithCheckOut),
            THIRTY_SECONDS, DEFAULT_TIMEOUT_MSG + this.btnProceedWithCheckOut);
  }

  @Override
  public String getPageRelativeUrl() {
    return null;
  }

  public void clickOnProceedWithCheckout() {
    this.btnProceedWithCheckOut.click();
  }
}
