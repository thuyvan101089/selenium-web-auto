package test.order.computer;

import models.components.order.CheapComputerComponent;
import models.components.order.StandardComputerComponent;
import org.testng.annotations.Test;
import test.BaseTest;
import test_flows.computer.OrderComputerFlow;

public class BuyStandardComputerTest extends BaseTest {

    @Test
    public void testCheapComputerBuying(){
        driver.get("https://demowebshop.tricentis.com/build-your-own-computer");
        OrderComputerFlow orderComputerFlow = new OrderComputerFlow(driver, StandardComputerComponent.class);
        orderComputerFlow.buildCompSpecAndAddToCart();
    }
}
