package test.order.computer;

import models.components.order.CheapComputerComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;
import test_data.CreditCardType;
import test_data.PaymentMethod;
import test_data.computer.ComputerData;
import test_data.DataObjectBuilder;
import test_flows.computer.OrderComputerFlow;

public class BuyCheapComputerTest extends BaseTest {

    @Test(dataProvider = "computerData")
    public void testCheapComputerBuying(ComputerData computerData) {
        System.out.println(computerData);
        driver.get("https://demowebshop.tricentis.com/build-your-cheap-own-computer");
        OrderComputerFlow<CheapComputerComponent> orderComputerFlow = new OrderComputerFlow(driver, CheapComputerComponent.class, computerData);
        orderComputerFlow.buildCompSpec();
        orderComputerFlow.addItemToCart();
        orderComputerFlow.verifyShoppingCartPage();
        orderComputerFlow.agreeTermsAndCheckout();
        orderComputerFlow.inputBillingAddress();
        orderComputerFlow.inputShippingAddress();
        orderComputerFlow.selectShippingMethod();
        orderComputerFlow.selectPaymentMethod(PaymentMethod.CREDIT_CARD);
        orderComputerFlow.inputPaymentInfo(CreditCardType.DISCOVER);
        orderComputerFlow.confirmOrder();
    }

    @DataProvider
    public ComputerData[] computerData() {
        String relativeFileLocation = "/src/main/java/test_data/computer/CheapComputerDataList.json";
        return DataObjectBuilder.buildDataObjectFrom(relativeFileLocation, ComputerData[].class);

    }
}
