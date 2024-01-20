package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverFactory {

    public static WebDriver getDriver(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(chromeOptions);
        // Global waiting strategy for whole session when finding an element/elements
        // Interval time: 500ms
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15L));
        return driver;
    }
}
