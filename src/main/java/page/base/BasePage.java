package page.base;

import helper.CommonHelper;
import helper.JavaScriptHelper;
import helper.WebElementHelper;
import helper.driver.AlertHelper;
import io.qameta.allure.Step;
import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page.aldi.module.CartMessage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * BasePage Encapsulate common page objects
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */
public abstract class BasePage implements IPage, Wait {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

    public static final String PAGE_LOAD_JS = "return document.readyState";
    public static int POLLING_TIME = 100;
    public String DEFAULT_TIMEOUT_MSG = " Timed out waiting for element ...! ";

    public WebDriver webDriver;
    public WebDriverWait wait;

    public CommonHelper commonHelper;
    public WebElementHelper webElementHelper;
    public JavaScriptHelper javaScriptHelper;
    public AlertHelper alertHelper;

    /**
     * The Constant CHAR_LIST.
     */
    private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyz";


    public BasePage(WebDriver driver, int waitTimeInSeconds) {
        this.webDriver = driver;
        wait = new WebDriverWait(driver, waitTimeInSeconds, POLLING_TIME);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, waitTimeInSeconds), this);
        init();
    }

    protected WebDriver getWebDriver() {
        return webDriver;
    }

    String lnkText = "//*[contains(text(),'idf_TextValue')]";
    private static final String LINK_TEXT_REPLACE_STRING = "idf_TextValue";

    String pageHeader = "//*[contains(text(),'idf_TextValue')]";
    private static final String PAGE_HEADER_REPLACE_STRING = "idf_TextValue";

    String messageBody = "//*[contains(text(),'idf_TextValue')]";
    private static final String MESSAGE_BODY_REPLACE_STRING = "idf_TextValue";

    String tabName = "//*[contains(text(),'idf_TextValue')]";
    private static final String TAB_TEXT_REPLACE_STRING = "idf_TextValue";

    String buttonName = "//*[contains(text(),'idf_TextValue')]";
    private static final String BUTTON_TEXT_REPLACE_STRING = "idf_TextValue";


    /**
     * Navigate To expect page base with provide url
     */
    @Step("Open URL {url}")
    public void navigateToPage(String url) {
        LOGGER.info("Open URL [{}]", url);
        webDriver.get(url);
    }

    public void init() {
        commonHelper = new CommonHelper();
        webElementHelper = new WebElementHelper();
        javaScriptHelper = new JavaScriptHelper();
        alertHelper = new AlertHelper();
    }

    public void clickOnLinktext(String linkText) {
        CommonHelper.sleep(2000);
        getWebDriver().findElement(By.xpath(lnkText
                .replace(LINK_TEXT_REPLACE_STRING, linkText))).click();
    }

    public void clickOnTab(String tabText) {
        getWebDriver().findElement(By.xpath(tabName
                .replace(TAB_TEXT_REPLACE_STRING, tabText))).click();
    }

    public void verifyThePageHeader(String pageHeaderOrMessage) {
//        waitForCondition(getWebDriver(), ExpectedConditions.visibilityOf(getWebDriver().findElement(By.xpath(pageHeader
//                .replace(PAGE_HEADER_REPLACE_STRING, pageHeaderOrMessage)))),10,"Page header has benn failed.");
        CommonHelper.sleep(5000);
        assertThat(getWebDriver().findElement(By.xpath(pageHeader
                .replace(PAGE_HEADER_REPLACE_STRING, pageHeaderOrMessage))).getText())
                .containsIgnoringCase(pageHeaderOrMessage);

    }

    public void clickOnButton(String buttonText) {
        getWebDriver().findElement(By.xpath(this.buttonName
                .replace(BUTTON_TEXT_REPLACE_STRING, buttonText))).click();
    }

    public void verifyMessage(String message) {
        assertThat(getWebDriver().findElement(By.xpath(messageBody
                .replace(MESSAGE_BODY_REPLACE_STRING, message))).getText())
                .containsIgnoringCase(message);

    }

    /**
     * This method generates random string.
     *
     * @param length set to length
     * @return String
     */
    private String generateRandomString(final int length) {

        StringBuilder randStr = new StringBuilder();
        for (int i = 0; i < length; i++) {
            // int number = getRandomNumber();
            Random randomGenerator = new Random(System.nanoTime());
            int number = randomGenerator.nextInt(CHAR_LIST.length());
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }

    /**
     * This method generates random numbers.
     *
     * @return int
     */
    private long getRandomNumber() {
        long randomInt;
        // int range = (int)System.nanoTime();
        // Random randomGenerator = new Random(System.nanoTime());

        randomInt = System.nanoTime();
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }

    /**
     * This method generates random string.
     *
     * @param dataType  set to dataType
     * @param reqLength set to length
     * @return String
     */

    public final String generateData(final String dataType, final int reqLength) {

        String random = "";
        String split = "";

        if ("String".equalsIgnoreCase(dataType)) {
            random = this.generateRandomString(reqLength);
        } else if ("int".equalsIgnoreCase(dataType)) {
            if (reqLength <= 0) {
                throw new IllegalArgumentException(
                        "Can not use minus values as length. Current: "
                                + reqLength);
            }
            split = String.valueOf(this.getRandomNumber());
            int splitLength = split.length();
            random = split.substring(splitLength - reqLength, splitLength);
        } else if ("Alphanumeric".equalsIgnoreCase(dataType)) {
            if (reqLength <= 0) {
                throw new IllegalArgumentException(
                        "Can not use minus values as length. Current: "
                                + reqLength);
            }
            split = String.valueOf(this.getRandomNumber());
            int splitLength = split.length();
            random =
                    this.generateRandomString(reqLength - 1)
                            + split.substring(splitLength - 1, splitLength);
        } else if (dataType.toLowerCase(Locale.getDefault()).startsWith("date")) {
            String format = "yyyy/MM/dd";
            boolean skipWeekend = false;
            String[] params = dataType.split("\\|");
            if (params.length > 2) {
                format = dataType.split("\\|")[1];
                skipWeekend = Boolean.parseBoolean(dataType.split("\\|")[2]);

            } else if (params.length > 1) {
                format = dataType.split("\\|")[1];
            }

            random = getDate(format, skipWeekend, reqLength);

        }
        return random;
    }

    /**
     * Gets the date.
     *
     * @param format      the format
     * @param skipWeekend the skip weekend
     * @param day         the day
     * @return the date
     */
    private String getDate(final String format, final boolean skipWeekend,
                           final int day) {

        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;

        if (skipWeekend) {
            final int five = 5;
            final int two = 2;
            final int seven = 7;
            int days = day;
            days = ((days / five) * two) + days;
            date = DateUtils.addDays(Calendar.getInstance().getTime(), days);
            Calendar myCal = new GregorianCalendar();
            myCal.setTime(date);
            if (myCal.get(Calendar.DAY_OF_WEEK) == seven) {
                date = DateUtils.addDays(date, 2);
            } else if (myCal.get(Calendar.DAY_OF_WEEK) == 1) {
                date = DateUtils.addDays(date, 1);
            }
        } else {
            date = DateUtils.addDays(Calendar.getInstance().getTime(), day);
        }
        return dateFormat.format(date);

    }

}



