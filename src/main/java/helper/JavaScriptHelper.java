package helper;

import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static helper.enumhelper.time.THOUSAND_MILLISECOND;

/**
 * All Javascript helper methods should be defined hire
 *
 * @author Dhananjaya Jayarathne
 */

public class JavaScriptHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(JavaScriptHelper.class);

    public String executeJsScript(WebDriver webdriver, String jsScript, int waitTimeSec) {
        String result;
        for (int i = -1; i < waitTimeSec; i++) {
            CommonHelper.sleep(THOUSAND_MILLISECOND);
            try {
                return result = ((JavascriptExecutor) webdriver).executeScript(jsScript).toString();
            } catch (Exception e) {
//         LOGGER.error("Can't get the result for this script [{}] ...! " + e.getMessage(), jsScript);
            }
        }

        return "";
    }

    public void setElementTextByJS(WebDriver webDriver, WebElement element, String setValue) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                JavascriptExecutor jse = (JavascriptExecutor) webDriver;
                jse.executeScript("arguments[0].value='" + setValue + "';", element);
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }

    public void clickElementByJS(WebDriver webDriver, WebElement element) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                JavascriptExecutor executor = (JavascriptExecutor) webDriver;
                executor.executeScript("arguments[0].click();", element);
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }

    public WebElement getElementByJS(WebDriver webDriver, WebElement element) {
        int attempts = 0;
        WebElement getElement = null;
        while (attempts < 2) {
            try {
                if (element.isDisplayed()) {
                    getElement = element;
                    break;
                }
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        return getElement;
    }

    public void scrollToElement(WebDriver webDriver, WebElement element) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public boolean isDisabled(WebDriver webDriver, WebElement webElement) {
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        return (Boolean) jse.executeScript("return arguments[0].hasAttribute(\"disabled\");", webElement);
    }

    public void disableOverlay(WebDriver webDriver) {
        ((JavascriptExecutor) webDriver).executeScript("document.getElementById('ID').style.display='block';");
    }
}
