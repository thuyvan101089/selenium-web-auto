package test.order.computer;

import models.components.order.CheapComputerComponent;
import org.testng.annotations.Test;
import test.BaseTest;
import test_flows.computer.OrderComputerFlow;

public class BuyCheapComputerTest extends BaseTest {

    @Test
    public void testCheapComputerBuying(){
        driver.get("https://demowebshop.tricentis.com/build-your-cheap-own-computer");
        OrderComputerFlow orderComputerFlow = new OrderComputerFlow(driver, CheapComputerComponent.class);
        orderComputerFlow.buildCompSpecAndAddToCart();
    }
}
