package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitForElementEnable;

import java.time.Duration;

public class ExplicitWait {
    private static final String TARGET_URL = "https://the-internet.herokuapp.com/login";


    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getDriver();
        driver.get(TARGET_URL);
        try {
            /*
             * Implicit wait: apply globally for the whole session when finding elements
             * Interval time = 500 miliseconds
             * Explicit wait: apply locally | for a specific element
             * Interval: 500 miliseconds
             * */

            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            // This result show TimeoutException when condition is not matched
            //  webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("No element")));

            // This result show NoSuchElementException when condition is not matched
            // webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("No element"))));

            // Trigger action and check the element disappear
            /*  try{
                webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("No element"));
            }catch (TimeoutException timeoutException){
                System.out.println("The element is still visible");
            }*/
            // Using customize explicit wait
            webDriverWait.until(new WaitForElementEnable(By.id("sth")));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
