package test_flows.computer;

import models.components.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailPage;
import org.openqa.selenium.WebDriver;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {

    private Class<T> computerEssentialCompClass;
    private WebDriver driver;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialCompClass) {
        this.computerEssentialCompClass = computerEssentialCompClass;
        this.driver = driver;
    }

    public void buildCompSpecAndAddToCart() {
        // Other steps...

        ComputerEssentialComponent computerEssentialComp = new ComputerItemDetailPage(driver).computerEssentialComp(computerEssentialCompClass);
        computerEssentialComp.selectProcessorType("processor");
        computerEssentialComp.selectRAMType("ram");

        // The rest steps
    }
}