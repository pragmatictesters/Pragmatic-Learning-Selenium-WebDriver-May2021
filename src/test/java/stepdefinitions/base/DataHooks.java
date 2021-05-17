package stepdefinitions.base;

import bean.data.BaseAldiUser;
import bean.data.Context;
import bean.data.Products;
import data.TestDataManager;
import io.cucumber.java.Before;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This contains the data hooks for all test cases
 */
public class DataHooks {

  private static final Logger LOGGER = LoggerFactory.getLogger(DataHooks.class);

  private Context context;
  private TestDataManager testDataManager = new TestDataManager();

  public DataHooks(Context context) {
    this.context = context;
  }

  @Before(value = "@TA-0001", order = 3)
  public void loadDataTA0001() {
    context.setBaseAldiUser(getRandomUser());
  }

  @Before(value = "@TA-0002", order = 3)
  public void loadDataTA0002() {
    context.setBaseAldiUser(getDefaultUser());
  }

  @Before(value = "@TA-0003", order = 3)
  public void loadDataTA0003() {
    Products products = new Products();
    products.setProductAddSuccessMsg(" one item has been added to your basket");
    context.setProducts(products);
    context.setBaseAldiUser(getTestUser());
  }

  @Before(value = "@TA-0004", order = 3)
  public void loadDataTA0004() {
    Products products = new Products();
    products.setProductAddSuccessMsg(" has been added to your basket");
    context.setProducts(products);
    context.setBaseAldiUser(getTestUser());
  }

  @Before(value = "@TA-0005", order = 3)
  public void loadDataTA0005() {
    Products products = new Products();
    products.setProductAddSuccessMsg(" one item has been added to your basket");
    context.setProducts(products);
    context.setBaseAldiUser(getTestUser());
  }

  @Before(value = "@TA-0006", order = 3)
  public void loadDataTA0006() {
    Products products = new Products();
    products.setProductAddSuccessMsg(" one item has been added to your basket");
    products.setProductRemoveMessage(" has been removed from your basket");
    context.setProducts(products);
    context.setBaseAldiUser(getTestUser());
  }

  @Before(value = "@TA-0007", order = 3)
  public void loadDataTA0007() {
    Products products = new Products();
    products.setProductAddSuccessMsg(" one item has been added to your basket");
    products.setProductRemoveMessage(" one item has been removed from your basket");
    context.setProducts(products);
    context.setBaseAldiUser(getTestUser());
  }

  @Before(value = "@TA-0008", order = 3)
  public void loadDataTA0008() {
    Products products = new Products();
    products.setProductAddSuccessMsg(" has been added to your basket");
    products.setProductRemoveMessage(" has been removed from your basket");
    context.setProducts(products);
    context.setBaseAldiUser(getTestUser());
  }

  @Before(value = "@TA-0009", order = 3)
  public void loadDataTA0009() {
    Products products = new Products();
    products.setProductAddSuccessMsg(" has been added to your basket");
    products.setProductRemoveMessage(" one item has been removed from your shopping cart");
    context.setProducts(products);
    context.setBaseAldiUser(getTestUser());
  }

  @Before(value = "@TA-0010", order = 3)
  public void loadDataTA0010() {
    Products products = new Products();
    products.setProductAddSuccessMsg(" one item has been added to your basket");
    context.setProducts(products);
    context.setBaseAldiUser(getTestUser());
  }

  public BaseAldiUser getDefaultUser() {
    return createUser("aldi.test.user@gmail.com", "Qazwsx@123");
  }

  public BaseAldiUser getRandomUser() {
    return createUser("aldi.user" + new Date().getTime() + "@gmail.com", "Qazwsx@123");
  }

  public BaseAldiUser getTestUser() {
    return createUser("jhone@aldi.com", "123456");
  }

  /****************************************
   *               Helpers                *
   ****************************************/

  public BaseAldiUser createUser(String email, String password) {
    BaseAldiUser user = new BaseAldiUser(email, password);
    return user;
  }
}
