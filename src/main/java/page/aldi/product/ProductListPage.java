package page.aldi.product;

import static helper.WaitingTimes.THIRTY_SECONDS;
import static helper.enumhelper.time.TWO_THOUSAND_MILLISECOND;

import helper.CommonHelper;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.aldi.module.HeaderPage;

/**
 * Landing Page encapsulate ALDI Product list page using composition with other pages/modules
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */
public class ProductListPage extends HeaderPage {

  String btnAddToBucket = "(//A[text()='Add to basket'])[ProductNumber]";
  private static final String PRODUCT_NUMBER_REPLACE_STRING = "ProductNumber";

  @FindBy(className = "product-title")
  private List<WebElement> lblProductTitles;

  @FindBy(className = "product-price")
  private List<WebElement> lblProductPrices;

  @FindBy(xpath = "//IMG[@_ngcontent-dwa-c131='']")
  private WebElement imgDeliveringImage;

  @FindBy(css = "div.col-md-4:nth-child(1) > app-item-card:nth-child(1) > div:nth-child(1) > div")
  private WebElement product;

  @FindBy(css = ".ico-plus")
  private WebElement btnAddProductPlus;

  @FindBy(xpath = ".//app-item-card/div/div/div[4]/div[2]")
  private WebElement btnRemoveProduct;

  public ProductListPage(WebDriver driver, int waitTimeInSeconds) {
    super(driver, waitTimeInSeconds);
  }

  @Override
  public void waitUntilPageIsLoaded() {
    waitForCondition(webDriver, ExpectedConditions.visibilityOf(this.lblProductTitles.get(1)),
        THIRTY_SECONDS, DEFAULT_TIMEOUT_MSG + this.lblProductTitles.get(1));
  }

  @Override
  public String getPageRelativeUrl() {
    return "/product-list";
  }

  public void clickOnAddToBucket(String productNumber) {
    CommonHelper.sleep(TWO_THOUSAND_MILLISECOND);
    getWebDriver().findElement(By.xpath(btnAddToBucket
        .replace(PRODUCT_NUMBER_REPLACE_STRING, productNumber))).click();
  }

  public List<WebElement> getProductNames() {
    return this.lblProductTitles;
  }

  public List<WebElement> getProductPrices() {
    return this.lblProductPrices;
  }

  public void clickOnSpecificProduct() {
    this.product.click();
  }

  public void clickOnAddProductPlusButton() {
    this.btnAddProductPlus.click();
  }

  public void clickOnProductRemoveButton() {
    this.btnRemoveProduct.click();
  }
}
