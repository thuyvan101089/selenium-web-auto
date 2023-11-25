package test;

import driver.DriverFactory;
import models.pages.LoginPageModel01;
import org.openqa.selenium.WebDriver;

public class POMModel01Tests {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https");
        LoginPageModel01 loginPageModel01 = new LoginPageModel01(driver);
        loginPageModel01.username().sendKeys("vantest");
        loginPageModel01.password().sendKeys("ABcd@1234");
        loginPageModel01.loginbutton().click();
    }
}
