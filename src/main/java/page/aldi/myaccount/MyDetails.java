package page.aldi.myaccount;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.aldi.module.HeaderPage;

import static org.assertj.core.api.Assertions.assertThat;

public class MyDetails extends HeaderPage {

    String ele_lblYourAccountBox = "//span[contains(text(),'idf_ItemName')]/../span[2]";
    private static final String ACCOUNT_BOX_REPLACE_STRING = "idf_ItemName";

    @FindBy(xpath = "//label[text()='Password']/../input[@id='password' and @value='*******']")
    private WebElement ele_lblPassword;

    @FindBy(xpath = "//label[text()='Email Address']/../input[@id='email']")
    private WebElement ele_lblEmailAddress;

    @FindBy(xpath = "//input[@formcontrolname='dob']")
    private WebElement tf_DOB;

    @FindBy(xpath = "//input[@id='firstName']")
    private WebElement tf_FirstName;

    @FindBy(xpath = "//input[@formcontrolname='lastName']")
    private WebElement tf_LastName;

    @FindBy(xpath = "//input[@formcontrolname='primaryPhone']")
    private WebElement tf_PrimaryPhone;

    @FindBy(xpath = "//input[@formcontrolname='secondaryPhone']")
    private WebElement tf_SecondaryPhone;

    @FindBy(xpath = "//button[@id='dropdownTitle']")
    private WebElement ele_lblTitle;

    String ele_lblTitleOption = "//a[text()='idf_Option']";
    private static final String OPTION_REPLACE_STRING = "idf_Option";

    public MyDetails(WebDriver driver, int waitTimeInSeconds) {
        super(driver, waitTimeInSeconds);
    }

    public void clickOnAccountBox(String accountBox) {
        getWebDriver().findElement(By.xpath(ele_lblYourAccountBox
                .replace(ACCOUNT_BOX_REPLACE_STRING, accountBox))).click();
    }

    public WebElement getPasswordLabel() {
        return ele_lblPassword;
    }

    public void fillPersonalData(String title, String dob, String firstName, String lastName, String primaryPhoneNumber, String secondaryPhoneNumber) {
        this.ele_lblTitle.click();
        getWebDriver().findElement(By.xpath(ele_lblTitleOption
                .replace(OPTION_REPLACE_STRING, title))).click();
        this.tf_DOB.clear();
        this.tf_DOB.sendKeys(dob);
        this.tf_FirstName.clear();
        this.tf_FirstName.sendKeys(firstName);
        this.tf_LastName.clear();
        this.tf_LastName.sendKeys(lastName);
        this.tf_PrimaryPhone.clear();
        this.tf_PrimaryPhone.sendKeys(primaryPhoneNumber);
        this.tf_SecondaryPhone.clear();
        this.tf_SecondaryPhone.sendKeys(secondaryPhoneNumber);
    }

    public void verifyThePersonalData(String title, String dob, String firstName, String lastName, String primaryPhoneNumber, String secondaryPhoneNumber) {
        String titleInSystem = this.ele_lblTitle.getText();
        String dobInSystem = this.tf_DOB.getText();
        String firstNameInSystem = this.tf_FirstName.getText();
        String lastNameInSystem = this.tf_LastName.getText();
        String primaryPhoneNumberInSystem = this.tf_PrimaryPhone.getText();
        String secondaryPhoneNumberInSystem = this.tf_SecondaryPhone.getText();
        assertThat(titleInSystem.equals(title));
//        assertThat(dobInSystem.equals(dob));
//        assertThat(firstNameInSystem.equals(firstName));
//        assertThat(lastNameInSystem.equals(lastName));
//        assertThat(primaryPhoneNumberInSystem.equals(primaryPhoneNumber));
//        assertThat(secondaryPhoneNumberInSystem.equals(secondaryPhoneNumber));
    }
}
