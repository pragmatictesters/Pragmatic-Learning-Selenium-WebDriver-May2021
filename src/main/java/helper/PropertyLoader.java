package helper;

import java.io.IOException;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PropertyLoader will load all the properties in the application.properties file
 *
 * @author Dhananjaya Jayarathne
 * @version 1.0
 * @since 1.0
 */
public class PropertyLoader {

  private static final Logger LOGGER = LoggerFactory.getLogger(PropertyLoader.class);

  private static Properties props = new Properties();

  private static final String PROP_FILE = "/application.properties";
  public static final String HUB_URL = "hub.url";
  public static final String BROWSER_NAME = "browser.name";
  public static final String MAILINATOR_URL = "mailinator.url";
  public static final String MAILINATOR_USERNAME = "mailinator.username";
  public static final String MAILINATOR_PASSWORD = "mailinator.password";
  public static final String MAILINATOR_TOKEN = "mailinator.token";
  public static final String APPLICATION_URL = "application.ural";

  static {
    try {
      props.load(PropertyLoader.class.getResourceAsStream(PROP_FILE));
      props.putAll(System.getProperties()); // Override properties with command line arguments

      generateDerivedProperties();

    } catch (IOException e) {
      throw new RuntimeException("Property file not found");
    }
  }

  private PropertyLoader() {
  }

  public static String loadProperty(String name) {
    return props.getProperty(name);
  }


  private static void generateDerivedProperties() {
    String browserName = props.getProperty(BROWSER_NAME);

    String applicationURL = props.getProperty(APPLICATION_URL);
    props.setProperty(APPLICATION_URL, applicationURL);
  }

  /**
   * Get property value from -D args .. if not found or exception  return empty ""
   *
   * @param key -D args key
   * @return key
   */
  public static String getProperty(String key) {
    String keyValue = "";
    LOGGER.info("System.getProperty [{}]", key);

    try {
      keyValue = System.getProperty(key);
      if (StringUtils.isBlank(keyValue)) {
        keyValue = "";
      }
    } catch (Exception e) {
      LOGGER.error("Can't get -D arg for KEY " + key + "\n" + e.getMessage());
    }

    LOGGER.info("System.getProperty key: " + key + " - Value: " + keyValue);

    return keyValue;
  }

  public static String getEnv(String key) {
    String keyValue = "";
    LOGGER.info("System.getEnv [{}]", key);

    try {
      keyValue = System.getenv(key);
      if (StringUtils.isBlank(keyValue)) {
        keyValue = "";
      }
    } catch (Exception e) {
      LOGGER.error("Can't get KEY - Sent ENVs " + key + "\n" + e.getMessage());
    }

    LOGGER.info("System.getEnv key: " + key + " - Value: " + keyValue);

    return keyValue;
  }
}