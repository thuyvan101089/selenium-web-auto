package models.components.order;

import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import support.ui.SelectEx;

import java.util.List;

@ComponentCSSSelector(".product-essential")
public class StandardComputerComponent extends ComputerEssentialComponent {
    private static final By PRODUCTATTRSELECTOR = By.cssSelector("select[name^='product_attribute']");
    public StandardComputerComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    @Override
    public String selectProcessorType(String type) {
        final int PROCESSOR_DROPDOWN_INDEX = 0;
        WebElement dropdownProcessorEle = component.findElements(PRODUCTATTRSELECTOR).get(PROCESSOR_DROPDOWN_INDEX);
        return selectOption(dropdownProcessorEle,type);
    }

    @Override
    public String selectRAMType(String type) {
        final int RAM_DROPDOWN_INDEX = 1;
        WebElement dropdownRAMEle = component.findElements(PRODUCTATTRSELECTOR).get(RAM_DROPDOWN_INDEX);
        return selectOption(dropdownRAMEle,type);
    }

    private String selectOption(WebElement dropdownEle, String type) {
        SelectEx selectEx = new SelectEx(dropdownEle);
        List<WebElement> allOptions = selectEx.getOptions();
        String fullStrOption = null;
        for (WebElement option : allOptions) {
            String currentOption = option.getText();
            String currentOptionWithoutSpaces = currentOption.trim().replace(" ", "");
            if (currentOptionWithoutSpaces.startsWith(type)) {
                fullStrOption = currentOption;
                break;
            }
        }
        if (fullStrOption == null) {
            throw new RuntimeException("[ERROR]: The option " + type + "is not existing");
        }
        selectEx.selectByVisibleText(fullStrOption);
        return fullStrOption;

    }
}
