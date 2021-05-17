package page.aldi.product;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.aldi.module.HeaderPage;

/**
 * Product page Page encapsulate ALDI Product page using composition with other pages/modules
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */
public class ProductPage extends HeaderPage {

  @FindBy(xpath = "//A[text()='Add to basket']")
  private WebElement btnAddToBasket;

  @FindBy(css = ".product-title")
  private List<WebElement> lblProductName;

  @FindBy(xpath = "//*[@id=\"product_details_remove_btn\"]|//*[@id=\"product_details_decrease_btn\"]")
  private WebElement btnRemoveProduct;

  @FindBy(css = ".ico-plus")
  private WebElement btnAddProductPlus;

  public ProductPage(WebDriver driver, int waitTimeInSeconds) {
    super(driver, waitTimeInSeconds);
  }

  public void clickOnAddBasketButton() {
    btnAddToBasket.click();
  }

  public String getProductName(int productNumber) {
    return lblProductName.get(productNumber).getText();
  }

  public void clickOnProductRemoveButton() {
    this.btnRemoveProduct.click();
  }

  public void clickOnAddProductPlusButton() {
    this.btnAddProductPlus.click();
  }
}
