package stepdefinitions.aldi;

import bean.data.Context;
import helper.CommonHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.aldi.myaccount.MyDetails;
import stepdefinitions.base.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;


public class MyAccountStepDef extends BaseTest {

    private Context context;
    MyDetails myDetails = new MyDetails(getDriver(), 5);

    public MyAccountStepDef(Context context) {
        super(context);
        this.context = context;
    }

    @When("I click {string} box")
    public void i_click_box(String accountBoxText) {
        // Write code here that turns the phrase above into concrete actions
        CommonHelper.sleep(3000);
        myDetails.clickOnAccountBox(accountBoxText);
    }

    @When("Verify the {string} and password")
    public void verify_the_and_password(String emailAddress) {
        // Write code here that turns the phrase above into concrete actions
        assertThat(myDetails.getPasswordLabel()).isNotNull();
    }

    @When("I enter {string} and {string} and {string} and {string} and {string} and {string}")
    public void i_enter_and_and_and_and_and(String title, String dob, String firstName, String surname, String primaryPhoneNumber, String secondaryPhoneNumber) {
        // Write code here that turns the phrase above into concrete actions
        myDetails.fillPersonalData(title, dob, firstName, surname, primaryPhoneNumber, secondaryPhoneNumber);
    }

    @Then("Verify the Personal Data {string} and {string} and {string} and {string} and {string} and {string}")
    public void verify_the_personal_data_and_and_and_and_and(String title, String dob, String firstName, String surname, String primaryPhoneNumber, String secondaryPhoneNumber) {
        // Write code here that turns the phrase above into concrete actions
        myDetails.verifyThePersonalData(title, dob, firstName, surname, primaryPhoneNumber, secondaryPhoneNumber);
    }
}
