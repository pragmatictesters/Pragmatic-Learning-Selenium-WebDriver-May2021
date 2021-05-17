package stepdefinitions.aldi;

import static helper.enumhelper.time.FIVE_THOUSAND_MILLISECOND;
import static helper.enumhelper.time.TWO_THOUSAND_MILLISECOND;
import static org.assertj.core.api.Assertions.assertThat;

import bean.data.Context;
import helper.CommonHelper;
import helper.PropertyLoader;
import helper.rest.JSONReader;
import helper.rest.RestServiceRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import page.aldi.landing.LandingPage;
import page.aldi.module.Login;
import page.aldi.module.Registration;
import page.aldi.module.CommonModule;
import stepdefinitions.base.BaseTest;

public class LoginAndRegisterStepDef extends BaseTest {

    private Context context;

    Login login = new Login(getDriver(), 5);
    LandingPage landingPage = new LandingPage(getDriver(), 5);
    Registration registration = new Registration(getDriver(), 5);
    CommonModule commonModule = new CommonModule(getDriver(), 5);
    public String newEmailAddress;

    public LoginAndRegisterStepDef(Context context) {
        super(context);
        this.context = context;
    }

    @Given("I am on the landing page")
    public void i_am_on_the_landing_page() {
        login.navigateToPage(PropertyLoader.loadProperty(PropertyLoader.APPLICATION_URL));
    }

    @When("I enter {string} and enter {string}")
    public void i_enter_and_enter(String username, String password) {
        login.enterUserName(username);
        login.enterPassword(password);
    }

    @When("I click on {string} button")
    public void i_click_on_login_button(String string) {
        commonModule.clickOnButton(string);
    }

    @When("I am enter email address for register")
    public void i_am_enter_email_address_for_register() {
        registration.enterEmail(context.getBaseAldiUser().getEmail());
    }

    @When("I am enter password for register")
    public void i_am_enter_password_for_register() {
        registration.enterPassword(context.getBaseAldiUser().getPassword());
    }

    @Given("I am log in to the application with {string}  {string}")
    public void i_am_log_in_to_the_application(String userName, String password) {
        login.navigateToPage(PropertyLoader.loadProperty(PropertyLoader.APPLICATION_URL));
        commonModule.clickOnLoginLink(LOGIN);
        login.enterUserName(userName);
        login.enterPassword(password);
        commonModule.clickOnButton(LOGIN);
        assertThat(login.getLogoutLink().getText()).containsIgnoringCase(LOGOUT);
    }

    @Then("Verify logout link and user icon")
    public void verify_logout_link_and_user_icon() {
        assertThat(login.getLogoutLink().getText()).containsIgnoringCase(LOGOUT);
    }

    @Then("Verify {string} has shown")
    public void verify_has_shown(String message) {
        CommonHelper.sleep(TWO_THOUSAND_MILLISECOND);
        assertThat(commonModule.getMessage(message)).containsIgnoringCase(message);
    }


    @When("I enter email address and enter {string} for new User")
    public void i_enter_email_address_and_enter_for_new_User(String password) {
        landingPage.headerPage.clickOnLoginLink();
        login.enterUserName(newEmailAddress);
        login.enterPassword(password);
    }

    @Then("I should not be able to login until confirm email")
    public void i_should_not_be_able_to_login_until_confirm_email() {
        assertThat(login.getLoginErrorMessage()).containsIgnoringCase("Incorrect email or password");
    }

    @Then("Verify logout link")
    public void verify_logout_link() {
        CommonHelper.sleep(5000);
        assertThat(login.getLogoutLink()).isNotNull();
    }

    @When("I enter new email address and enter {string} and enter {string}")
    public void i_enter_new_email_address_and_enter_and_enter(String password, String confirmpassword) {
        // Write code here that turns the phrase above into concrete actions
        registration.enterEmail(newEmailAddress);
        registration.enterPassword(password);
        registration.enterConfirmPassword(confirmpassword);
    }

    @When("I Agree the conditions and subscribe")
    public void i_agree_the_conditions_and_subscribe() {
        // Write code here that turns the phrase above into concrete actions
        registration.clickOnUserCondition();
        registration.clickOnSubscribe();
    }

    @When("I get the Activate URL from mailinator and load it")
    public void i_get_the_activate_url_from_mailinator_and_load_it() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        CommonHelper.sleep(15000);
        String url = PropertyLoader.loadProperty(PropertyLoader.MAILINATOR_URL);
        String user = PropertyLoader.loadProperty(PropertyLoader.MAILINATOR_USERNAME);
        String pass = PropertyLoader.loadProperty(PropertyLoader.MAILINATOR_PASSWORD);
        RestServiceRunner runner = new RestServiceRunner("get", url, "null", "limit=1&token=1a419da10ab54d52b3605f78633bc292&private_domain=true&from=admin@aldi.com", "basicauth", user, pass);
        String json = runner.run();
        JSONObject jsonObj = new JSONObject(json.split("\\[")[1]);
        String emailID = jsonObj.getString("id");
        String parameters = "token=1a419da10ab54d52b3605f78633bc292&id=" + emailID + "&private_domain=true";
        RestServiceRunner runner1 = new RestServiceRunner("get", "https://api.mailinator.com/api/email?", "null", parameters, "basicauth", user, pass);
        String json1 = runner1.run();
        JSONParser parser = new JSONParser();
        Object jsonObject = parser.parse(json1);
        JSONReader jr = new JSONReader();
        String emailBody = jr.getNode("data.parts[0].body", jsonObject).get("value");
        String urlForActivate = emailBody.split("<p><a href=\"")[1];
        urlForActivate = urlForActivate.split("\"><img alt=\"Activate My account\"")[0];
        getDriver().get(urlForActivate);
    }

    @Then("I click on Register button")
    public void i_click_on_Register_button() {
        login.clickOnRegisterButton();
    }

    @Given("Generate Random String To Email")
    public void generate_random_string_to_email() {
        // Write code here that turns the phrase above into concrete actions
        String randomNumber = login.generateData("Int", 5);
    }

    @Given("Generate Random String To Email combine To {string}")
    public void generate_random_string_to_email_combine_to(String emailAddress) {
        // Write code here that turns the phrase above into concrete actions
        String randomNumber = login.generateData("Int", 5);
        String temp = emailAddress.split("@")[0];
        temp = temp + randomNumber + "@" + emailAddress.split("@")[1];
        newEmailAddress = temp;
    }

    @Then("Logout from the Application")
    public void logout_from_the_application() {
        login.clickOnLogoutButton();
        CommonHelper.sleep(5000);
        assertThat(login.getLogInLink()).isNotNull();
    }

}
