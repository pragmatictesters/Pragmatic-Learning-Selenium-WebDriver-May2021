package helper.driver;

import helper.CommonHelper;
import helper.WebElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static helper.enumhelper.time.THOUSAND_MILLISECOND;

/**
 * All action methods.
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */
public class DriverActionHelper {

    /**
     * Move to an web element and click.
     */
    public void moveToAndClick(WebDriver driver, WebElement webElement) {
        new Actions(driver).moveToElement(webElement).perform();
        CommonHelper.sleep(THOUSAND_MILLISECOND);
        new WebElementHelper().click(webElement);
    }

    /**
     * Move to an web element, find web element and click.
     * To be able to remove sector we need to hover the mouse and remove button is shown.
     */
    public void moveToFindAndClick(WebDriver driver, WebElement parentWe, By childWeBy) {
        new Actions(driver).moveToElement(parentWe).perform();
        CommonHelper.sleep(THOUSAND_MILLISECOND);
        WebElement childWe = parentWe.findElement(By.cssSelector(" button"));
        new WebElementHelper().click(childWe);
    }


}
