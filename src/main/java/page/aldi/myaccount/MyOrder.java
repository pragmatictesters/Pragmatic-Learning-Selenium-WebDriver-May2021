package page.aldi.myaccount;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.aldi.module.CommonModule;
import page.aldi.module.HeaderPage;
import page.base.BasePage;

import java.util.List;

import static helper.WaitingTimes.THIRTY_SECONDS;

/**
 * My Order Page encapsulate ALDI My order page using composition with other pages/modules
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */
public class MyOrder extends HeaderPage {

  CommonModule commonModule;

  private String expandOrder = "./div/div/div[1]/h6[2]";

  @FindBy(className = "order-history-container mb-5")
  private WebElement orderHistoryContainer;

  @FindBy(className = "order-history-item")
  private List<WebElement> orderCardList;

  @FindBy(className = "order-history-details")
  private List<WebElement> orderDetails;


  public MyOrder(WebDriver driver, int waitTimeInSeconds) {
    super(driver, waitTimeInSeconds);
    initModules(driver);
  }

  public HeaderPage headerPage;

  public void initModules(WebDriver driver) {
    headerPage = new HeaderPage(driver, 5);
    commonModule = new CommonModule(driver, 5);
  }

  @Override
  public void waitUntilPageIsLoaded() {
    waitForCondition(webDriver, ExpectedConditions.visibilityOf(this.orderHistoryContainer),
            THIRTY_SECONDS, DEFAULT_TIMEOUT_MSG + this.orderHistoryContainer);
  }

  @Override
  public String getPageRelativeUrl() {
    return null;
  }

  public String getOrderNumber(String expectedOrderNumber) {
    return commonModule.getMessage(expectedOrderNumber);
  }

  public void clickOnSpecificOrder(String expectedOrderNumber) {
    webElementHelper.getListWebElementSimple(orderCardList, expectedOrderNumber)
            .findElement(By.xpath(expandOrder)).click();
  }

  public String getOrderDetails(String productName) {
   return webElementHelper.getListWebElementSimple(orderDetails, productName).getText();
  }
}
