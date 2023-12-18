package models.pages;

import models.components.order.ComputerEssentialComponent;
import org.openqa.selenium.WebDriver;

public class ComputerItemDetailPage extends BasePage{
    public ComputerItemDetailPage(WebDriver driver) {
        super(driver);
    }
    public <T extends ComputerEssentialComponent>  T computerEssentialComp(Class<T> computerEssestialCompClass){
        return findComponent(computerEssestialCompClass);
    }
}
