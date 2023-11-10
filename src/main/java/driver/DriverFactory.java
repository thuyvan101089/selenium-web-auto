package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DriverFactory {

    public static WebDriver getDriver(){
        WebDriver driver = new ChromeDriver();
        // Global waiting strategy for whole session when finding an element/elements
        // Interval time: 500ms
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15L));
        return driver;
    }
}
