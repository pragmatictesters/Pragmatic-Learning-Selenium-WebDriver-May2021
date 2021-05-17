package stepdefinitions.base;

import bean.data.Context;
import helper.PropertyLoader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import page.aldi.checkout.CheckOutPage;
import page.aldi.landing.LandingPage;
import page.aldi.module.CommonModule;
import page.aldi.module.Login;
import page.aldi.module.Registration;
import page.aldi.product.ProductListPage;

public class BaseStepDef extends BaseTest {

    Login login;
    LandingPage landingPage;
    Registration registration;
    ProductListPage productListPage;
    CommonModule commonModule;
    CheckOutPage checkOutPage;

    public BaseStepDef(Context context) {
        super(context);
        login = new Login(getDriver(), 5);
        landingPage = new LandingPage(getDriver(), 5);
        registration = new Registration(getDriver(), 5);
        productListPage = new ProductListPage(getDriver(), 5);
        commonModule = new CommonModule(getDriver(), 5);
        checkOutPage = new CheckOutPage(getDriver(), 5);
    }

    @And("I navigate to the product List")
    public void i_navigate_to_the_product_list() {
        productListPage.navigateToProductList();
    }

    @When("I am click on {string} link")
    public void i_am_click_on_link(String string) {
        commonModule.clickOnLoginLink(string);
    }

    @When("I am click on {string} button")
    public void i_am_click_on_button(String string) {
        commonModule.clickOnButton(string);
    }


    @When("Verify the Page Header {string}")
    public void verify_the_page_header(String pageHeaderOrMessage) {
        // Write code here that turns the phrase above into concrete actions
        login.verifyThePageHeader(pageHeaderOrMessage);
    }

    @When("I am click on {string} Tab")
    public void i_am_click_on_tab(String tabText) {
        // Write code here that turns the phrase above into concrete actions
        login.clickOnTab(tabText);
    }

    @When("Verify the message contains {string}")
    public void verify_the_message_contains(String message) {
        // Write code here that turns the phrase above into concrete actions
        login.verifyThePageHeader(message);
    }

}
