package listener;

import helper.driver.DriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StepLifeCycleListener implements StepLifecycleListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(StepLifeCycleListener.class);

    @Override
    public void beforeStepStop(final StepResult result) {
        if (result.getStatus() == Status.FAILED || result.getStatus() == Status.BROKEN) {
            LOGGER.info("beforeStepStop Allure Listener take screenshot if fail/broken ...!");
            takeScreenshot(DriverManager.getDriver());
        }
    }

    @Attachment(value = "Page_Screenshot_", type = "image/png")
    public byte[] takeScreenshot(WebDriver driver) {
        LOGGER.info("Taking screenshot ...!");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
