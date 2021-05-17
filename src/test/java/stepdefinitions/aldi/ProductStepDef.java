package stepdefinitions.aldi;

import static helper.enumhelper.time.TWO_THOUSAND_MILLISECOND;
import static org.assertj.core.api.Assertions.assertThat;

import bean.data.Context;
import helper.CommonHelper;
import helper.WebElementHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.text.DecimalFormat;

import page.aldi.checkout.YourBasketPage;
import page.aldi.module.CartMessage;
import page.aldi.module.HeaderPage;
import page.aldi.product.ProductListPage;
import page.aldi.product.ProductPage;
import stepdefinitions.base.BaseTest;

public class ProductStepDef extends BaseTest {
  Context context;

  HeaderPage headerPage;
  ProductListPage productListPage;
  ProductPage productPage;
  CartMessage cartMessage;
  YourBasketPage yourBasketPage;

  public ProductStepDef(Context context) {
    super(context);
    this.context = context;
    headerPage = new HeaderPage(getDriver(), 5);
    productListPage = new ProductListPage(getDriver(), 5);
    productPage = new ProductPage(getDriver(), 5);
    cartMessage = new CartMessage(getDriver(), 5);
    yourBasketPage = new YourBasketPage(getDriver(), 5);
  }

  @When("I click on add to basket button on first product")
  public void i_click_on_add_to_basket_button_on_first_product() {
    context.getProducts().setProductName(productListPage.getProductNames().get(0).getText());
    context.getProducts().setProductPrice(productListPage.getProductPrices().get(0).getText());
    productListPage.clickOnAddToBucket("1");
  }

  @Then("Product added message shown with the product Name")
  public void product_added_message_shown_with_the_product_name() {
    assertThat(context.getProducts().getProductName()
            + context.getProducts().getProductAddSuccessMsg())
        .containsIgnoringCase(cartMessage.getProductMessage(
        context.getProducts().getProductName()));
  }

  @Then("Cart price increased by product price")
  public void cart_price_increased_by_product_price() {
    assertThat(context.getProducts().getProductPrice())
        .containsIgnoringCase(productListPage.getCartPrice());
  }

  @Then("product is there in the cart")
  public void product_is_there_in_the_cart() {
    productListPage.mouseHoverOnCart();
    assertThat(productListPage.getCartItem()).isNotNull();
  }

  @Given("I navigate to a product")
  public void i_navigate_to_a_product() {
    context.getProducts().setProductPrice(productListPage.getProductPrices().get(0).getText());
    productListPage.clickOnSpecificProduct();
  }

  @When("I click on add to basket button in product page")
  public void i_click_on_add_to_basket_button_in_product_page() {
    context.getProducts().setProductName(productPage.getProductName(0));
    productPage.clickOnAddBasketButton();
  }

  @When("click on plus button {string} times")
  public void click_on_plus_button_times(String productCount) {
    double productPrice = 0;
    int productCountInt = Integer.parseInt(productCount);
    for (int i = productCountInt ; i > 0; i--) {
      productListPage.clickOnAddProductPlusButton();
      productPrice = Double.parseDouble(productListPage.getCartPrice()
          .replace("$", ""));
    }
    context.getProducts().setProductPrice("$" + String.valueOf(productPrice));
  }

  @When("I click on minus button in the product in product list page")
  public void i_click_on_minus_button_in_the_product_in_product_list_page() {
    productListPage.clickOnProductRemoveButton();
    context.getProducts().setCartPrice(productListPage.getCartPrice());
  }

  @Then("I can see item removed pop up message")
  public void i_can_see_item_removed_pop_up_message() {
    assertThat(context.getProducts().getProductName()
        + context.getProducts().getProductRemoveMessage())
        .containsIgnoringCase(cartMessage.getProductMessage(
            context.getProducts().getProductName()));
  }

  @Then("I can see cart is empty")
  public void i_can_see_cart_is_empty() {
    assertThat(context.getProducts().getCartPrice())
        .containsIgnoringCase(productListPage.getCartPrice());
  }

  @When("I click on minus button {string} in the product in product list page")
  public void i_click_on_minus_button_in_the_product_in_product_list_page(
      String removeButtonHitCount) {
    CommonHelper.sleep(TWO_THOUSAND_MILLISECOND);
    for (int i = Integer.parseInt(removeButtonHitCount); i > 0; i--) {
      productListPage.clickOnProductRemoveButton();
    }
    context.getProducts().setCartPrice(productListPage.getCartPrice());
  }

  @When("I click on minus button {string} times in the product in product page")
  public void i_click_on_minus_button_times_in_the_product_in_product_page(
      String removeButtonHitCount) {
    CommonHelper.sleep(TWO_THOUSAND_MILLISECOND);
    for (int i = Integer.parseInt(removeButtonHitCount); i > 0; i--) {
      productPage.clickOnProductRemoveButton();
    }
    context.getProducts().setCartPrice(headerPage.getCartPrice());
  }

  @When("click on plus button {string} times in product page")
  public void click_on_plus_button_times_in_product_page(String productCount) {
    double cartPrice = Double.parseDouble(context.getProducts().getCartPrice()
        .replace("$", ""));
    int productCountInt = Integer.parseInt(productCount);
    for (int i = productCountInt ; i > 0; i--) {
      productPage.clickOnAddProductPlusButton();
      cartPrice = Double.parseDouble(productPage.getCartPrice()
          .replace("$", ""));
    }
    context.getProducts().setCartPrice("$" + String.valueOf(cartPrice));
  }

  @When("I click on add to basket button on first {string} products")
  public void i_click_on_add_to_basket_button_on_first_products(String productCount) {
    double productPrice = 0;
    DecimalFormat df = new DecimalFormat("#.####");
   int productCountInt = Integer.parseInt(productCount);
    for (int i = productCountInt ; i > 0; i--) {
      context.getProducts().setProductName(productPage.getProductName(i));
      productListPage.clickOnAddToBucket(Integer.toString(i));
      productPrice = productPrice + Double.parseDouble(
          productListPage.getProductPrices().get(i - 1).getText()
          .replace("$", ""));
    }
    context.getProducts().setProductPrice("$" + String.valueOf(df.format(productPrice)));
  }

  @Then("Verify the Products are available")
  public void verify_the_product_is_available() {
    assertThat(productListPage.getCartItemList()).isNotEmpty();
  }

  @When("I search for {string} link")
  public void i_search_for_link(String productName) {
    headerPage.enterSearchText(productName);
    headerPage.clickOnSearchButton();
  }

  @When("I navigate to order checkout")
  public void i_navigate_to_order_checkout() {
    headerPage.mouseHoverOnCart();
    headerPage.clickOnCart();
    yourBasketPage.clickOnProceedWithCheckout();
  }
}
