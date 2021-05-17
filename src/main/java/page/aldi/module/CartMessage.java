package page.aldi.module;

import static helper.WaitingTimes.THIRTY_SECONDS;
import static helper.enumhelper.time.THOUSAND_MILLISECOND;

import helper.CommonHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * CartMessage encapsulate ALDI Cart Messages of the Product and product list pages using composition with other pages/modules
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */
public class CartMessage extends HeaderPage {
  @FindBy(className = "p-toast-detail")
  private WebElement lblAddProductMessage;

  public CartMessage(WebDriver driver, int waitTimeInSeconds) {
    super(driver, waitTimeInSeconds);
  }

  @Override
  public void waitUntilPageIsLoaded() {
    waitForCondition(webDriver, ExpectedConditions.visibilityOf(this.lblAddProductMessage),
        THIRTY_SECONDS, DEFAULT_TIMEOUT_MSG + this.lblAddProductMessage);
  }

  @Override
  public String getPageRelativeUrl() {
    return "/login";
  }

  public String getProductMessage(String itemName) {
    CommonHelper.sleep(THOUSAND_MILLISECOND);
    return lblAddProductMessage.getText();
  }
}
