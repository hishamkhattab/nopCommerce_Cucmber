package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

    private final WebDriver driver;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
    }


    /**
     * Method to wait until an element is visible on the page
     *
     * @param element          web element that is waited to be visible
     * @param timeOutInSeconds time to wait in seconds
     */
    public void waitForElement(WebElement element, long timeOutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));

    }
}
