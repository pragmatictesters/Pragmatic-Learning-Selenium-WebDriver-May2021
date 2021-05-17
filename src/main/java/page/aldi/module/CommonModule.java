package page.aldi.module;

import helper.CommonHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.base.BasePage;

import static helper.enumhelper.time.*;

public class CommonModule extends BasePage {

    private static final String REPLACE_STRING = "idf_TextValue";
    private static final String ERROR_MESSAGE = "Expected Element is not clickable";

    String element = "//*[contains(text(),'idf_TextValue')]";
    String btnElement = "//BUTTON[text()='idf_TextValue']";

    @FindBy(className = ".waiticon-loading")
    private WebElement pageLoadGifElement;

    public CommonModule(WebDriver driver, int waitTimeInSeconds) {
        super(driver, waitTimeInSeconds);
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitUntilPageLoadComplete(getWebDriver(), 1, 5);
    }

    @Override
    public String getPageRelativeUrl() {
        return null;
    }

    public void clickOnLoginLink(String replaceString) {
        javaScriptHelper.clickElementByJS(getWebDriver(), getWebDriver().findElement(
                By.xpath(element.replace(REPLACE_STRING, replaceString))));
    }

    public void clickOnLoginLinkWithWait(String replaceString) {
        try {
            waitForCondition(getWebDriver(), ExpectedConditions.invisibilityOfAllElements(
                    pageLoadGifElement), 15, ERROR_MESSAGE);
        } catch (Exception e) {
            defaultPageLoadWait(getWebDriver());
            javaScriptHelper.clickElementByJS(getWebDriver(), getWebDriver().findElement(
                    By.xpath(element.replace(REPLACE_STRING, replaceString))));
        }
    }

    public void clickOnLinkText(String linkText) {
        CommonHelper.sleep(TWO_THOUSAND_MILLISECOND);
        getWebDriver().findElement(By.xpath(element
                .replace(REPLACE_STRING, linkText))).click();
    }

    public void clickOnTab(String tabText) {
        getWebDriver().findElement(By.xpath(element
                .replace(REPLACE_STRING, tabText))).click();
    }

    public String getThePageHeader(String pageHeaderOrMessage) {
        CommonHelper.sleep(FIVE_THOUSAND_MILLISECOND);
        return getWebDriver().findElement(By.xpath(element
                .replace(REPLACE_STRING, pageHeaderOrMessage))).getText();
    }

    public void clickOnButton(String buttonText) {
        CommonHelper.sleep(TWO_THOUSAND_MILLISECOND);
        getWebDriver().findElement(By.xpath(this.btnElement
                .replace(REPLACE_STRING, buttonText))).click();
    }

    public String getMessage(String message) {
        return getWebDriver().findElement(By.xpath(element
                .replace(REPLACE_STRING, message))).getText();
    }
}
