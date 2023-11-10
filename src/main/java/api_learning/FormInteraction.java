package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormInteraction {
    private static final String TARGET_URL = "https://the-internet.herokuapp.com/login";
    private static final By USERNAMESELECTOR = By.id("username");
    private static final By PASSWORDSELECTOR = By.cssSelector("#password");
    private static final By LOGIN_BUTTON_SELECTOR = By.cssSelector("#login [type='submit']");
    private static final String USER_STR = "tomsmith";
    private static final String PASSWORD_STR = "SuperSecretPassword!";

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getDriver();
        driver.get(TARGET_URL);

        try {
            WebElement usernameElement = driver.findElement(USERNAMESELECTOR);
            WebElement passwordElement = driver.findElement(PASSWORDSELECTOR);
            //Refresh page \\ Assumed that the action trigger the DOM reloaded
            driver.navigate().refresh();
            usernameElement = driver.findElement(USERNAMESELECTOR);
            passwordElement = driver.findElement(PASSWORDSELECTOR);
            WebElement loginButtonElement = driver.findElement(LOGIN_BUTTON_SELECTOR);

            usernameElement.sendKeys(USER_STR);
            passwordElement.sendKeys(PASSWORD_STR);
            loginButtonElement.click();
            // Navigate Back
            driver.navigate().back();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
