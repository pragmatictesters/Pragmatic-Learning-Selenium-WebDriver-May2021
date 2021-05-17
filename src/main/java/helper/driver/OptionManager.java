package helper.driver;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Set driver Options
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */

public class OptionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(OptionManager.class);

    private ChromeOptions chromeOptions;
    private ChromeOptions remoteChromeOptions;
    private FirefoxOptions firefoxOptions;
    private static boolean isHeadless = false;

    public OptionManager() {
        setIsHeadless();
        setChromeOptions();
        setFirefoxOptions();
        setRemoteChromeOptions();
    }

    public void setChromeOptions() {
        LOGGER.info("setChromeOptions ...!");
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--window-size=1920,1080");
        if (isHeadless)
            chromeOptions.addArguments("--headless");
    }

    public void setFirefoxOptions() {
        LOGGER.info("setFirefoxOptions ...!");
        firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--incognito");

        if (isHeadless)
            firefoxOptions.addArguments("--headless");
    }

    public ChromeOptions getChromeOptions() {
        return chromeOptions;
    }

    public FirefoxOptions getFirefoxOptions() {
        return firefoxOptions;
    }

    /**
     * Set headless option from command line arguments
     */
    public static void setIsHeadless() {
        LOGGER.info("setIsHeadless ...!");
        String isHeadlessStr = System.getProperty("headless");
        LOGGER.info("is Headless browser [{}] ...!", isHeadlessStr);

        if (!StringUtils.isBlank(isHeadlessStr) && StringUtils.equals(isHeadlessStr, "true"))
            isHeadless = true;
    }

    /**
     * setting chrome options for the grid run.
     */
    public void setRemoteChromeOptions() {
        LOGGER.info("setRemoteChromeOptions ...!");
        remoteChromeOptions = new ChromeOptions();
        remoteChromeOptions.addArguments("--incognito");
        remoteChromeOptions.addArguments("--no-sandbox");
        remoteChromeOptions.addArguments("--disable-dev-shm-usage");
        remoteChromeOptions.addArguments("--window-size=1920,1080");
        remoteChromeOptions.addArguments("--headless");
    }


    public ChromeOptions getRemoteChromeOptions() {
        return remoteChromeOptions;
    }


}
