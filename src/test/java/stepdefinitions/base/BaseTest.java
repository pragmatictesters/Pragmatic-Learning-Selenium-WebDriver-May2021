package stepdefinitions.base;

import bean.data.Context;
import helper.driver.DriverManager;
import org.openqa.selenium.WebDriver;

public class BaseTest {

  private Context context;

  //Enums
  public static final String LOGIN = "Login";
  public static final String LOGOUT = "Logout";

  public BaseTest(Context context) {
    this.context = context;
  }

  public WebDriver getDriver() {
    return DriverManager.getDriver();
  }
}
