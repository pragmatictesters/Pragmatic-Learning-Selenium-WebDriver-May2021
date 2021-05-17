package helper.driver;

import helper.PropertyLoader;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manage thread safe driver
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */

public class DriverManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverManager.class);

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(RemoteWebDriver driver) {
        if (PropertyLoader.loadProperty(PropertyLoader.BROWSER_NAME).equals("REMOTE_CHROME")) {
            driver.setFileDetector(new LocalFileDetector());
        }
        DriverManager.driver.set(driver);
    }

    public static void quit() {
        try {
            DriverManager.driver.get().quit();
            DriverManager.driver.remove();
        } catch (Exception wde) {
            LOGGER.error("Can not quit driver ...! " + wde.getMessage());
        }
    }

    public static String getInfo() {
        Capabilities cap = ((RemoteWebDriver) driver.get()).getCapabilities();
        String browserName = cap.getBrowserName();
        String platform = cap.getPlatform().toString();
        String version = cap.getVersion();
        return String.format("browser: %s v: %s platform: %s", browserName, version, platform);
    }
}