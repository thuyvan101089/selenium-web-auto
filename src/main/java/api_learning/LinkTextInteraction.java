package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkTextInteraction {
    private static final String TARGET_URL = "https://the-internet.herokuapp.com/login";

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getDriver();
        driver.get(TARGET_URL);

        try {
            /*
            By powerByLinkTextSel = By.linkText("Elemental Selenium");
            WebElement powerByLinkTextEle = driver.findElement(powerByLinkTextSel);
            powerByLinkTextEle.click();
            */

            By powerByPartialLinkTextSel = By.partialLinkText("Elemental");
            WebElement powerByPartialLinkTextEle = driver.findElement(powerByPartialLinkTextSel);
            powerByPartialLinkTextEle.click();
            //Debug purpose only
            try{
                Thread.sleep(2000);
            }catch (Exception ignored){

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
