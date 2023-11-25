package test;

import driver.DriverFactory;
import models.pages.LoginPageModel02;
import models.pages.LoginPageModel03;
import org.openqa.selenium.WebDriver;

public class POMModel03Tests {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https");
        LoginPageModel03 loginPageModel03 = new LoginPageModel03(driver);
        loginPageModel03
                .inputUsername("vantest")
                .inputPassword("ABcd@1234")
                .clickOnLoginButton();
    }
}
