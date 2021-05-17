package stepdefinitions.aldi;

import bean.data.Context;
import helper.PropertyLoader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.aldi.checkout.CheckOutPage;
import page.aldi.checkout.OrderConfirmationPage;
import page.aldi.checkout.PaymentDetailsPage;
import page.aldi.landing.LandingPage;
import page.aldi.module.CommonModule;
import page.aldi.module.Login;
import page.aldi.myaccount.MyOrder;
import stepdefinitions.base.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class CartCheckoutStepDef extends BaseTest {

  Context context = new Context();
  Login login = new Login(getDriver(), 5);
  LandingPage landingPage = new LandingPage(getDriver(), 5);
  CommonModule commonModule = new CommonModule(getDriver(), 5);
  CheckOutPage checkOutPage = new CheckOutPage(getDriver(), 5);
  PaymentDetailsPage paymentDetailsPage = new PaymentDetailsPage(getDriver(), 5);
  OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(getDriver(), 5);
  MyOrder myOrder = new MyOrder(getDriver(), 5);

  public CartCheckoutStepDef(Context context) {
    super(context);
  }

  @When("I am click on {string} product")
  public void i_am_click_on_product(String string) {
    commonModule.clickOnLinkText(string);
  }

  @When("I am click on {string}")
  public void i_am_click_on_link_test(String string) {
    checkOutPage.clickOnChangeDeliveryDetails();
  }

  @When("I enter {string} and {string} and {string} and {string}")
  public void i_enter_and_and_and(String firstName, String surName, String mobile, String address) {
    checkOutPage.enterFirstName(firstName);
    checkOutPage.enterSurName(surName);
    checkOutPage.enterMobile(mobile);
    checkOutPage.enterAddress(address);
  }

  @And("Verify the Delivery fee and Total fee")
  public void verify_the_delivery_fee_and_total_fee() {
   Double expectedOutstandingToPay = Double.parseDouble(checkOutPage.getItemPrice().replace("$", ""))
           +Double.parseDouble(checkOutPage.getDeliveryFee().replace("$", ""));
    assertThat(Double.toString(expectedOutstandingToPay)).containsIgnoringCase(checkOutPage.getOutstandingToPay()
            .replace("$", ""));
    context.getOrder().setOutstandingPrice("$" + Double.toString(expectedOutstandingToPay));
    context.getOrder().setOrderPrice(checkOutPage.getItemPrice());
  }

  @When("I am select the {string} and {string} and {string} and {string} and {string} and {string}")
  public void i_am_select_the_and_and_and_and_and(String paymentOption, String cardHolderName, String cardNumber,
                                                  String expireMonth, String expireYear, String cardCode) {

    paymentDetailsPage.clickOnPaymentMethod();
    commonModule.clickOnLinkText(paymentOption);
    paymentDetailsPage.enterCardNumber(cardNumber);
    paymentDetailsPage.enterCardHolderName(cardHolderName);
    paymentDetailsPage.clickOnExpireMonth();
    commonModule.clickOnLinkText(expireMonth);
    paymentDetailsPage.clickOnExpireYear();
    commonModule.clickOnLinkText(expireYear);
    paymentDetailsPage.enterCardCod(cardCode);
  }

  @Then("Verify the {string} Page Header")
  public void verify_the_page_header(String string) {
    assertThat(orderConfirmationPage.getOrderConfirmationMessage()).containsIgnoringCase(string);
    context.getOrder().setOrderNumber(orderConfirmationPage.getOrderId().split(":")[1].trim());
  }

  @Then("I am navigating to {string}")
  public void i_am_navigating_to(String page) {
    orderConfirmationPage.navigateToPage(PropertyLoader.loadProperty(PropertyLoader.APPLICATION_URL) +"/" + page);
  }

  @Then("I click on {string} Box")
  public void i_click_on_box(String string) {
    myOrder.clickOnSpecificOrder(context.getOrder().getOrderNumber());
  }

  @Then("Verify the order details {string} and {string} and Cost")
  public void verify_the_order_details_and_and_cost_and(String productName, String quantity) {
    assertThat(myOrder.getOrderDetails(productName)).containsIgnoringCase(productName);
    assertThat(myOrder.getOrderDetails(productName)).containsIgnoringCase(quantity);
    assertThat(myOrder.getOrderDetails(productName)).containsIgnoringCase(context.getOrder().getOrderPrice());
  }
}
