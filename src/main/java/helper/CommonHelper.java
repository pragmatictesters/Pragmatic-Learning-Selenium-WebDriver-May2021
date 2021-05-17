package helper;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.naming.Context;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Use this class to define general utils that don't fail on any other specialised utility class
 */
public class CommonHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonHelper.class);

    // "zero hour offset" also known as "Zulu time" (UTC)
    public static final String DATE_FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static final void sleep(int sleepTimeMillisecond) {
        try {
            Thread.sleep(sleepTimeMillisecond);
        } catch (InterruptedException e) {
        }

    }
}
