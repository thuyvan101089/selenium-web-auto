package models.components.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class ComputerEssentialComponent extends BaseItemComponent {
    private final static By ALLOPTIONSSELECTOR = By.cssSelector("option-list input");

    public ComputerEssentialComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public abstract String selectProcessorType(String type);

    public abstract String selectRAMType(String type);

    public String selectHDD (String type){
        return selectCompStr(type);
    }
    public String selectOs (String type){
        return selectCompStr(type);
    }
    public String selectSoftware (String type){
        return selectCompStr(type);
    }
    public void unselectDefaultOption() {
        component.findElements(ALLOPTIONSSELECTOR).forEach(option -> {
            if (option.getAttribute("checked") != null) {
                option.click();
            }
        });

    }

    protected String selectCompStr(String type) {
        String selectorStr = "//label[contains(text(),'+ type +')]";
        By optionSelector = By.xpath(selectorStr);
        WebElement optionElement = null;
        try {
            optionElement = component.findElement(optionSelector);
        } catch (Exception ignored) {

        }
        if (optionElement == null) {
            throw new RuntimeException("[ERROR] The option " + type + "is not existing");
        }
        return optionElement.getText().trim();

    }

}
