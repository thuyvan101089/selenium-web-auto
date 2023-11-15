package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class FormInteractionMultipleMatching {
    private static final String TARGET_URL = "https://the-internet.herokuapp.com/login";
    private static final By LOGIN_INPUT_FIELD_SEL = By.tagName("input");
    private static final By LOGIN_BUTTON_SELECTOR = By.cssSelector("#login [type='submit']");

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getDriver();
        driver.get(TARGET_URL);

        try {
            List<WebElement> loginFieldEles = driver.findElements(LOGIN_INPUT_FIELD_SEL);
            if(!loginFieldEles.isEmpty()){
                final int USERNAME_INDEX = 0;
                final int PASSWORD_INDEX = 1;
                loginFieldEles.get(USERNAME_INDEX).sendKeys("van");
                loginFieldEles.get(PASSWORD_INDEX).sendKeys("van");
            }else {
                throw new NoSuchElementException("No log input field element found ");
            }
            driver.findElement(LOGIN_BUTTON_SELECTOR).click();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
