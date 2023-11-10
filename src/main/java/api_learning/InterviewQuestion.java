package api_learning;

import dev.failsafe.internal.util.Assert;
import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InterviewQuestion {
    private static final String TARGET_URL = "https://the-internet.herokuapp.com/login";
    private static final By USERNAMESELECTOR = By.id("username");

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getDriver();

         /*  Cach 1:
        driver.get(TARGET_URL);
        Exception exception = null;
        try {
            WebElement usernameElement = driver.findElement(USERNAMESELECTOR);
        } catch (NoSuchElementException e) {
            exception = e;
        }
        if (exception == null) {
         Assert.fail("The element ABC is not on the page");
        }*/

        // Cach 2
        List<WebElement> usernameElements = driver.findElements(USERNAMESELECTOR);
        if (!usernameElements.isEmpty()) {
            // Assert.fail("The element is not on the page");
        }

        /*
         * Not existing on the page
         */

    }
}
