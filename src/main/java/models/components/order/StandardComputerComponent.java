package models.components.order;

import models.components.ComponentCSSSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCSSSelector(".product-essential")
public class StandardComputerComponent extends ComputerEssentialComponent{
    public StandardComputerComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    @Override
    public String selectProcessorType(String type) {
        System.out.println("selectProcessorType:Standard");
        return null;
    }

    @Override
    public String selectRAMType(String type) {
        System.out.println("selectRAMType:Standard");
        return null;
    }
}
