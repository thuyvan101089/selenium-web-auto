package test;

import driver.DriverFactory;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;

public class ProductComponentsTest {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://demowebshop.tricentis.com/");

        try {
            HomePage homePage = new HomePage(driver);
            homePage.productItemComponents().forEach(productItemComponent -> {
                System.out.println(productItemComponent.productTitleEle().getText());
            });
        } catch (Exception ignored) {

        } finally {
            driver.quit();
        }
    }

}
