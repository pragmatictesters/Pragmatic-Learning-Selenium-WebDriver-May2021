package page.aldi.landing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.aldi.module.HeaderPage;
import page.base.BasePage;

/**
 * Landing Page encapsulate ALDI landing page using composition with other pages/modules
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */
public class LandingPage extends HeaderPage {

  public LandingPage(WebDriver driver, int waitTimeInSeconds) {
    super(driver, waitTimeInSeconds);
    initModules(driver);
  }

  public HeaderPage headerPage;

  public void initModules(WebDriver driver) {
    headerPage = new HeaderPage(driver, 5);
  }

  @Override
  public void waitUntilPageIsLoaded() {

  }

  @Override
  public String getPageRelativeUrl() {
    return null;
  }

}
