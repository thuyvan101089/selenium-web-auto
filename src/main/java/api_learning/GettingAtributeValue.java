package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GettingAtributeValue {
    private static final String TARGET_URL = "https://the-internet.herokuapp.com/login";

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getDriver();
        driver.get(TARGET_URL);
        try {
            By powerByPartialLinkTextSel = By.partialLinkText("Elemental");
            WebElement powerByPartialLinkTextEle = driver.findElement(powerByPartialLinkTextSel);
            System.out.println(powerByPartialLinkTextEle.getAttribute("target"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
