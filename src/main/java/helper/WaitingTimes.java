package helper;

/**
 * Interface for TIMEOUTS in milliseconds and seconds used for testing
 */

public interface WaitingTimes {

  /**
   * Seconds
   */
  public static final int TEN_SECONDS = 10;
  public static final int TWENTY_SECONDS = 20;
  public static final int FIFTEEN_SECONDS = 15;
  public static final int THIRTY_SECONDS = 30;
  public static final int VERY_SHORT_WAIT_SECONDS = 5;
  public static final int SHORT_WAIT_SECONDS = 10;
  public static final int MEDIUM_WAIT_SECONDS = 30;
  public static final int LONG_WAIT_SECONDS = 50;
  public static final int VERY_LONG_WAIT_SECONDS = 90;

  public static final int EXTRA_LONG_WAIT_SECONDS = 180;

  /**
   * Default
   */
  public static final int DEFAULT_WAIT = 15;
  public static final int DEFAULT_PAGE_LOAD_WAIT = 15;

  /**
   * sleep.
   */
  default void sleep(int sleepTimeMls) {
    CommonHelper.sleep(sleepTimeMls);
  }

}
