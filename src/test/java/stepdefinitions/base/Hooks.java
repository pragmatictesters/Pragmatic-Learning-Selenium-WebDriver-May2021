package stepdefinitions.base;

import helper.PropertyLoader;
import helper.driver.DriverFactory;
import helper.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.qameta.allure.Attachment;
import java.net.MalformedURLException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

  private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);

  // Run before every scenario.
  @Before(order = 1)
  public void setUp() throws MalformedURLException {
    LOGGER.info("Cucumber hook - setup test properties, utils, driver for all scenarios ...!");

    if (PropertyLoader.loadProperty(PropertyLoader.BROWSER_NAME).equals("REMOTE_CHROME")) {
      DriverFactory.setRemoteDriver("REMOTE_CHROME");
    } else {
      DriverFactory.setDriver(DriverManagerType.CHROME);
    }
  }

  @After
  public void tearDown(Scenario scenario) {
    LOGGER.info("Cucumber hook - Quit and remove driver ...!");
    DriverManager.quit();
  }

  @AfterStep
  public void afterStepActions(Scenario scenario) {
    if (scenario.isFailed()) {
      scenario.attach(takeScreenshot(DriverManager.getDriver()), "image/png", "fail");
    }
  }

  @Attachment(value = "Page_Screenshot_", type = "image/png")
  public byte[] takeScreenshot(WebDriver driver) {
    LOGGER.info("Taking screenshot ...!");
    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
  }

}
