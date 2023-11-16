package api_learning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class IframeInteraction {
    private static final String TARGET_URL = "https://the-internet.herokuapp.com/iframe";
    private static final By IFRAME_SEL = By.cssSelector("iframe[id^='mce']");

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get(TARGET_URL);
        try {
            WebElement iframeEle = driver.findElement(IFRAME_SEL);
            driver.switchTo().frame(iframeEle);
            WebElement inputFieldEle = driver.findElement(By.id("tinymce"));
            inputFieldEle.click();
            inputFieldEle.clear();
            inputFieldEle.sendKeys("Hello World!");
            Thread.sleep(1500);
            driver.switchTo().defaultContent();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }


}
