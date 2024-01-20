package models.pages;

import models.components.checkout.*;
import org.openqa.selenium.WebDriver;

public class CheckOutPage extends BasePage {

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public BillingAddressComponent billingAddressComp() {
        return findComponent(BillingAddressComponent.class);
    }

    public ShippingAddressComponent shippingAddressComp() {
        return findComponent(ShippingAddressComponent.class);
    }

    public ShippingMethodComponent shippingMethodComp() {
        return findComponent(ShippingMethodComponent.class);
    }

    public PaymentMethodComponent paymentMethodComp() {
        return findComponent(PaymentMethodComponent.class);
    }

    public PaymentInformationComponent paymentInfomationComp() {
        return findComponent(PaymentInformationComponent.class);
    }

    public ConfirmOrderComponent confirmOrderComp() {
        return findComponent(ConfirmOrderComponent.class);
    }

}
