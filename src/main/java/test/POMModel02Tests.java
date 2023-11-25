package test;

import driver.DriverFactory;
import models.pages.LoginPageModel01;
import models.pages.LoginPageModel02;
import org.openqa.selenium.WebDriver;

public class POMModel02Tests {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https");
        LoginPageModel02 loginPageModel02 = new LoginPageModel02(driver);
        loginPageModel02.inputUsername("vantest");
        loginPageModel02.inputPassword("ABcd@1234");
        loginPageModel02.clickOnLoginButton();
    }
}
