package page.base;


import static page.base.BasePage.PAGE_LOAD_JS;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface Wait {

    default void forElementToBeDisplayed(WebDriver webDriver, int timeout, WebElement webElement,
                                         String webElementName) {
        ExpectedCondition<WebElement> condition = ExpectedConditions.visibilityOf(webElement);
        String timeoutMessage = webElementName + " wasn't displayed after " + Integer.toString(timeout) + " seconds.";
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        wait.withMessage(timeoutMessage);
        wait.until(condition);
    }

    default void waitForCondition(WebDriver webDriver, ExpectedCondition expectedCondition,
                                  int timeout, String failMessage) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        wait.withMessage(failMessage);
        wait.until(expectedCondition);
    }

    /**
     * Waiting for Page load with the java scripts
     *
     * @param webDriver            WebDriver instance
     * @param maxWaitTimeInMinutes maximum wait time in minutes
     * @param pollingTimeInSeconds interval time
     * @throws TimeoutException
     */
    //Todo review and delete
    default void waitUntilPageLoadComplete(WebDriver webDriver, int maxWaitTimeInMinutes,
                                           int pollingTimeInSeconds) {
        FluentWait<WebDriver> wait = fluentWait(webDriver, maxWaitTimeInMinutes, pollingTimeInSeconds,
                Exception.class);
        try {
            wait.until(new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver webDriver) {
                    return ((JavascriptExecutor) webDriver).executeScript(PAGE_LOAD_JS)
                            .toString().equals("complete");
                }
            });
        } catch (TimeoutException timeout) {
            throw new TimeoutException("Page has time out in " + timeout + "");
        }
    }

    default FluentWait<WebDriver> fluentWait(WebDriver webDriver, int maxWaitTimeInMinutes,
                                             int pollingTime, Class expClass) {
        return new FluentWait<WebDriver>(webDriver)
                .withTimeout(Duration.ofMinutes(maxWaitTimeInMinutes))
                .pollingEvery(Duration.ofMinutes(pollingTime))
                .ignoring(expClass);
    }

    default void synchronizedWait(WebDriver webDriver, int waitMilliSeconds) {
        try {
            synchronized (webDriver) {
                webDriver.wait(waitMilliSeconds);
            }
        } catch (InterruptedException e) {
        }
    }

    default void defaultPageLoadWait(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return ((JavascriptExecutor) webDriver).executeScript(
                        "return document.readyState"
                ).equals("complete");
            }
        });
    }
}
