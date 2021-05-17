package stepdefinitions.base;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 *
 */

@CucumberOptions(
    strict = true,
    features = {"src/test/resources/feature"},
    glue = {"stepdefinitions"},
    plugin = {"io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm",
        "listener.CustomCucumberListener"}
)
public class CucumberTestNgRunnerTest extends AbstractTestNGCucumberTests {

  @Override
  @DataProvider(parallel = true)
  public Object[][] scenarios() {
    return super.scenarios();
  }
}
