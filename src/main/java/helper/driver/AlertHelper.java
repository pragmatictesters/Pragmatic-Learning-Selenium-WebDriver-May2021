package helper.driver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page.base.Wait;

/**
 * Deal with Alerts
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */
public class AlertHelper implements Wait {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlertHelper.class);


    public Alert getAlert(WebDriver driver, int timeoutSec) {
        waitForCondition(driver, ExpectedConditions.alertIsPresent(),
            timeoutSec, " Alert is not shown ...!");
        return driver.switchTo().alert();
    }

    public void acceptAlert(WebDriver webDriver, int waitTime) {
        AlertHelper alertHelper = new AlertHelper();
        alertHelper.getAlert(webDriver, waitTime).accept();
    }
}
