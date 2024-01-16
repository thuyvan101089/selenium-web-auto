package models.pages;

import models.components.checkout.BillingAddressComponent;
import org.openqa.selenium.WebDriver;

public class CheckOutPage extends BasePage{

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }
    public BillingAddressComponent billingAddressComp(){
        return findComponent(BillingAddressComponent.class);
    }
}
