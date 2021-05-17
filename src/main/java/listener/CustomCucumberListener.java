package listener;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Dhananjaya Jayarathne
 * This class is responsible for injecting the master data set once per test suite.
 * This will be invoked once per test run.
 *
 *
 *
 */
public class CustomCucumberListener implements ConcurrentEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomCucumberListener.class);

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestRunStarted.class, setup);
        eventPublisher.registerHandlerFor(TestRunFinished.class, teardown);
    }

    private EventHandler<TestRunStarted> setup = event -> {
        beforeAll();
    };

    private void beforeAll() {
        LOGGER.info(" Running before test suite starts....");
        try {

        } catch (Exception e) {
            LOGGER.error("Error injecting master data....", e);
            System.exit(1);

        }

    }

    private EventHandler<TestRunFinished> teardown = event -> {
        afterAll();
    };

    private void afterAll() {
        LOGGER.info(" Exit Test suite execution...");
    }
}
