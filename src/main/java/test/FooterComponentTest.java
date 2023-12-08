package test;

import driver.DriverFactory;
import models.components.global.footer.CustomerServiceColumnComponent;
import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.FooterComponent;
import models.components.global.footer.InformationColumnComponent;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FooterComponentTest {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://demowebshop.tricentis.com/");

        try {
            HomePage homePage = new HomePage(driver);
            FooterComponent footerComponent = homePage.footerComp();
            InformationColumnComponent informationColumnComponent = footerComponent.informationColumnComp();
            CustomerServiceColumnComponent customerServiceColumnComponent = footerComponent.customerServiceColumnComp();
            testFooterComponent(informationColumnComponent);
            testFooterComponent(customerServiceColumnComponent);
        } catch (Exception ignored) {

        } finally {
            driver.quit();
        }
    }

    private static void testFooterComponent(FooterColumnComponent footerColumnComponent) {
        System.out.println(footerColumnComponent.headerEle().getText());
        for (WebElement linkEle : footerColumnComponent.linkEles()) {
            System.out.println(linkEle.getText() + ":" + linkEle.getAttribute("href"));

        }
        System.out.println("---");
    }
}
