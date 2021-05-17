package page.base;

/**
 * Enforce POM page structure
 *
 * @author Dhananjaya Jayarathne
 */
public interface IPage {

    void waitUntilPageIsLoaded();

    String getPageRelativeUrl();

}
