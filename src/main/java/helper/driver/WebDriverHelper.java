package helper.driver;

import static helper.driver.DriverManager.getDriver;

import java.util.ArrayList;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Set driver Action
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */
public class WebDriverHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverHelper.class);

    /**
     * move To next tab
     */
    public static void switchToWindow() {
        LOGGER.info("switchToWindow ...!");
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(tabs.size() - 1));
    }

    /*public static void switchToWindow(WebDriver driver, int timeoutSeconds,
                                      int expectedNumberOfWindows) {
        new WebElementHelper().waitFor(driver,
                ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows), timeoutSeconds);

        ArrayList<String> windows = new ArrayList<>(driver.getWindowHandles());
        // handle errors if cant switch ..  WebDriverException: unknown error: cannot activate web view
        if (null != windows && windows.size() > 0) {
            for (int i = 0; i < timeoutSeconds; i++) {
                try {
                    driver.switchTo().window(windows.get(windows.size() - 1));
                    break;
                } catch (WebDriverException we) {
                    if (i < timeoutSeconds - 1)
                        LOGGER.error("Can NOT switch to window/tab ...! " + we.getMessage());
                }
            }
        } else {
            throw new RuntimeException("Expected new window/tab not found ....!");
        }
    }*/

    public static void refreshThePage() {
        getDriver().navigate().refresh();
    }

    /**
     * Open a new tab.
     */
    public static void openNewTab(String pageUrl) {
        ((JavascriptExecutor) getDriver()).executeScript("window.open();");
        switchToWindow();
        getDriver().get(pageUrl);
    }

    /**
     * close existing tab and move to default tab
     */
    public static void closedAndSwitchDefaultTab() {
        getDriver().close();
        switchToWindow();
    }
}
