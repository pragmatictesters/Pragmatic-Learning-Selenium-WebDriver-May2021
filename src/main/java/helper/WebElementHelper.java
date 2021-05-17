package helper;

import static helper.driver.DriverManager.getDriver;
import static helper.enumhelper.time.THOUSAND_MILLISECOND;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Common method used for WEB element
 * e.g .. Click, select, is visible etc.
 *
 * @author Dhananjaya Jayarathne
 * @version 1.1
 * @since 1.0
 */

public class WebElementHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebElementHelper.class);
    private WebDriver webDriver;
    String CANT_CLICK_WE_ERROR = "Can't click on web Element ....!";



    /**
     * Get Fluent Wait with ignoring exception
     * @param driver
     * @param timeoutSec
     * @param pollSec
     * @return
     *
     */
    public FluentWait getFluentWait(
        WebDriver driver, int timeoutSec, int pollSec){
        return new FluentWait<WebDriver>(driver)
            .withTimeout(Duration.ofSeconds(30))
            .pollingEvery(Duration.ofSeconds(1))
            .ignoring(Exception.class);
    }

    public WebElement getListWebElementSimple(List<WebElement> listWe, String weText) {
        try {
            for (WebElement wef : listWe) {
                if (StringUtils.containsIgnoreCase(wef.getText(), weText))
                    return wef;
            }
        } catch (WebDriverException wde) {
            throw new WebDriverException("Can't find Web Element for [" + weText + "] ...! " + wde);
        }

        throw new RuntimeException("Cant get element from list for the text [" + weText + "] ...!");
    }

    public WebElement getListWebElement(List<WebElement> listWe, int index) {
        return listWe.get(index);
    }

    public List<String> getText(List<WebElement> webElementList) {
        List<String> suggestionsList = new ArrayList<String>();

        for (WebElement we : webElementList) {
            try {
                suggestionsList.add(we.getText());
            } catch (Exception e) {
//        LOGGER.error("Can't get text from web element [{}] ...! "+e.getMessage(), we);//TODO need to implement with selenium
            }
        }
        return suggestionsList;
    }

    public void selectByVisibleText(WebElement webElement, String selectByVisibleText) {
        try {
            Select dropDown = new Select(webElement);
            dropDown.selectByVisibleText(selectByVisibleText); //TODO re-implement
        } catch (WebDriverException e) {
            throw new RuntimeException("DropDown Not Found\n" + e);
        }
    }

    public void selectValueFromDropDown(WebDriver webDriver, WebElement dropDown,
                                        String optionsElements, String selectorString) {
        // Open the dropdown so the options are visible
        dropDown.click();
        // Get all of the options
        List<WebElement> options = webDriver.findElements(By.cssSelector(optionsElements));
        // Loop through the options and select the one that matches
        for (WebElement opt : options) {
            if (opt.getText().equals(selectorString)) {
                opt.click();
                return;
            }
        }
        throw new NoSuchElementException("Can't find " + selectorString + " in dropdown");
    }

    public void sendKey(WebElement we, String inputKey) {
//    LOGGER.info("Send key [{}] ...!", inputKey);
        try {
            we.sendKeys(inputKey);
        } catch (WebDriverException e) {
            throw new RuntimeException("Can't enter text on text field ...! \n" + e);
        }
    }

    public void cleanAndSendKeys(WebElement we, String inputKey) {
        try {
            we.clear();
            we.sendKeys(inputKey);
        } catch (WebDriverException e) {
            throw new RuntimeException("Can't enter text on text field ...! \n" + e);
        }
    }

    public void elementSelector(List<WebElement> wef) {
        for (WebElement ctrWe : wef) {
            if (null != ctrWe) {
                LOGGER.info("\t ......" + ctrWe.getText());
            } else {
                LOGGER.info("Can't get ctr from web element");
            }
        }
    }

    public String getWebElementText(WebElement webElement) {
        String weText = "";
        try {
            weText = webElement.getText();
        } catch (WebDriverException e) {
            LOGGER.warn("Can't Get web element text ...! \n" + e.getMessage());
        }

        return weText;
    }

    public <T> T waitFor(WebDriver webDriver, ExpectedCondition<T> condition, int timeout) {
        return new WebDriverWait(webDriver, timeout)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(NoSuchElementException.class)
                .until(condition);
    }

    public WebElement waitForElementPresent(
        final By locator, WebDriver driver, int timeoutSec, int pollSec){
        Wait<WebDriver> wait = getFluentWait(driver, timeoutSec, pollSec);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver arg0) {
                return arg0.findElement(locator);
            }
        });

        return foo;
    }

    public boolean waitForElement(final By locator, WebDriver driver, int timeoutSec, int pollSec){
        Wait<WebDriver> wait = getFluentWait(driver, timeoutSec, pollSec);

        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver arg0) {
                WebElement element = arg0.findElement(locator);
                if (null != element) {
                    return true;
                }
                return false;
            }
        };
        wait.until(function);

        return false;
    }

    public WebElement waitForElementObj(final By locator, WebDriver driver, int timeoutSec, int pollSec){
        Wait<WebDriver> wait = getFluentWait(driver, timeoutSec, pollSec);

        WebElement element = null;

        Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver arg0) {
                WebElement element = arg0.findElement(locator);
                if (element != null) {
                    LOGGER.info("Found the element ....!");
                }
                return element;
            }
        };
        return wait.until(function);
    }


    public WebElement waitForElementVisible(WebDriver webDriver, WebElement element, int timeOut) {
        return waitFor(webDriver, ExpectedConditions.visibilityOf(element), timeOut);
    }

    public WebElement waitForElementVisible(WebDriver webDriver, By location, int timeOut) {
        return waitFor(webDriver, ExpectedConditions.visibilityOf(webDriver.findElement(location)),
                timeOut);
    }

    public void click(WebElement element) {
//    LOGGER.info("Click on WebElement [{}] ...!", element);

        try {
            element.click();
        } catch (Exception e) {
            throw new RuntimeException(CANT_CLICK_WE_ERROR + " " + e.getMessage());
        }
    }

    /**
     * To avoid stale element exception we could use this or if we went to click many times
     *
     * @param driver
     * @param byLocator
     * @param clickNoOfTimes
     */
    public void click(WebDriver driver, By byLocator, int clickNoOfTimes) {
        for (int i = 1; i <= clickNoOfTimes; i++) {
            try {
                driver.findElement(byLocator).click();
                break;
            } catch (Exception e) {
                if (i == clickNoOfTimes) {
                    LOGGER.warn("Click try No[{}] ... " + e.getMessage(), i);
                }
            }
        }
    }

    /**
     * To avoid stale element exception we could use this or if we went to click many times
     *
     * @param element
     * @param clickNoOfTimes
     */
    public void click(WebElement element, int clickNoOfTimes) {
        for (int i = 1; i <= clickNoOfTimes; i++) {
            try {
                element.click();
                break;
            } catch (Exception e) {
                if (i == clickNoOfTimes) {
                    LOGGER.warn("Click try No[{}] ... " + e.getMessage(), i);
                }
            }
        }
    }

    /**
     * To avoid stale element exception we could use this or if we went to click many times
     *
     * @param elements
     */
    public void click(List<WebElement> elements) {
        if (elements != null && !elements.isEmpty()) {
            for (WebElement wef : elements) {
                wef.click();
            }
        }
        LOGGER.warn(CANT_CLICK_WE_ERROR);
    }

    /**
     * Common method to use as element waits and click
     *
     * @param webDriver
     * @param element
     * @param waitTime
     */
    public void waitForElementToBeClickable(WebDriver webDriver, WebElement element, int waitTime) {
        waitFor(webDriver, ExpectedConditions.elementToBeClickable(element), waitTime);
    }


    /**
     * Common method to use as element waits and click
     *
     * @param webDriver
     * @param element
     * @param waitTime
     */
    public void waitForElementToBeClickableClearAndInput(WebDriver webDriver, WebElement element, int waitTime, String inputString) {
        waitFor(webDriver, ExpectedConditions.elementToBeClickable(element), waitTime);
        try {
            element.clear();
            element.sendKeys(inputString);
        } catch (WebDriverException e) {
            throw new RuntimeException("Can't enter text on text field ...! \n" + e);
        }
    }


    /**
     * Common method to use as element waits for click
     *
     * @param webDriver
     * @param element
     * @param waitTime
     */
    public void waitForElementToBeClickableAndClick(WebDriver webDriver, WebElement element,
                                                    int waitTime) {
        waitFor(webDriver, ExpectedConditions.elementToBeClickable(element), waitTime);
        try {
            element.click();
        } catch (Exception e) {
            throw new RuntimeException(CANT_CLICK_WE_ERROR + " " + e.getMessage());
        }
    }

    public Boolean elementExist(By locator) {
        if (getDriver().findElements(locator).size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean elementExist(By locator, int waitTimeSec) {
        boolean isFound = false;
        while (waitTimeSec >= 0){
            waitTimeSec --;
            CommonHelper.sleep(THOUSAND_MILLISECOND);
            try {
                isFound = (getDriver().findElements(locator).size() > 0);
            } catch (WebDriverException we){
                LOGGER.warn("waitTimeSec left ["+ waitTimeSec +"]; " + we.getMessage());
            }
        }
        return isFound;
    }
}
