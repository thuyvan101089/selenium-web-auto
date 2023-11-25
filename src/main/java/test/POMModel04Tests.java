package test;

import driver.DriverFactory;
import models.pages.BasePage;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;

public class POMModel04Tests {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https");
        HomePage homepage = new HomePage(driver);
        homepage.footerComponent().doSomething();
    }
}
