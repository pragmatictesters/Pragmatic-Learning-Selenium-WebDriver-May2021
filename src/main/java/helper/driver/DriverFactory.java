package helper.driver;

import helper.PropertyLoader;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Set driver instance
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */

public class DriverFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverFactory.class);


    public static void setDriver(DriverManagerType driverManagerType) {
        LOGGER.info("Set Driver local no grid [{}]", driverManagerType);

        switch (driverManagerType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                DriverManager
                    .setDriver(new ChromeDriver(new OptionManager().getChromeOptions()));
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                DriverManager
                    .setDriver(new FirefoxDriver(new OptionManager().getFirefoxOptions()));
                break;
            case IEXPLORER:
                WebDriverManager.iedriver().setup();
                DriverManager.setDriver(new InternetExplorerDriver());
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                DriverManager.setDriver(new EdgeDriver());
                break;
            case CHROMIUM:
                WebDriverManager.chromiumdriver().setup();
                DriverManager.setDriver(new EdgeDriver());
                break;

            default:
                throw new InvalidArgumentException("Can't get browsers for " + driverManagerType);
        }

        LOGGER.info(DriverManager.getInfo());
    }

    /**
     * @param driverType
     */
    public static void setRemoteDriver(String driverType) throws MalformedURLException {
        LOGGER.info("Set Driver remote grid [{}]", driverType);

        switch (driverType) {
            case "REMOTE_CHROME":
                DesiredCapabilities remoteChromeCapabilities = DesiredCapabilities.chrome();
                remoteChromeCapabilities.setCapability(ChromeOptions.CAPABILITY, new OptionManager().getRemoteChromeOptions());
                DriverManager.setDriver(new RemoteWebDriver(
                    new URL(PropertyLoader.loadProperty(PropertyLoader.HUB_URL)),
                    remoteChromeCapabilities));
                break;
            default:
                throw new InvalidArgumentException("Can't get browsers for " + driverType);
        }

        LOGGER.info(DriverManager.getInfo());
    }


}
