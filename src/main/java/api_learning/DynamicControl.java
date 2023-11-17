package api_learning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitForElementEnable;

import java.time.Duration;

public class DynamicControl {
    private static final String TARGET_URL = "https://the-internet.herokuapp.com/dynamic_controls";
    private static final By CHECKBOX_FORM_SEL = By.id("checkbox-example");
    private static final By INPUT_FORM_SEL = By.id("input-example");
    private static final By CHECKBOX_INPUT_SEL = By.cssSelector("#checkbox-example input");
    private static final By INPUT_SEL = By.cssSelector("#input-example input");
    private static final By BUTTON_SEL = By.tagName("button");


    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get(TARGET_URL);
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            // Locate 2 parent elements
            WebElement checkboxFormEle = driver.findElement(CHECKBOX_FORM_SEL);
            WebElement inputFormEle = driver.findElement(INPUT_FORM_SEL);
            // Checkbox form interaction
            WebElement checkboxInputEle = checkboxFormEle.findElement(CHECKBOX_INPUT_SEL);
            WebElement removeButtonEle = checkboxFormEle.findElement(BUTTON_SEL);
            System.out.println("Before| Is checkbox selected " + checkboxInputEle.isSelected());
            checkboxInputEle.click();
            System.out.println("Before| Is checkbox selected " + checkboxInputEle.isSelected());

            removeButtonEle.click();
            webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(CHECKBOX_INPUT_SEL));
        // Interact with input form
            WebElement enableButtonEle = inputFormEle.findElement(BUTTON_SEL);
            WebElement inputTextEle = inputFormEle.findElement(INPUT_SEL);
            System.out.println("Before| Is text field enable "+inputTextEle.isEnabled());
            enableButtonEle.click();
            webDriverWait.until(new WaitForElementEnable(INPUT_SEL));
            System.out.println("Before| Is text field enable "+inputTextEle.isEnabled());
            inputTextEle.sendKeys("Van Test");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

