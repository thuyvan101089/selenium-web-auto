package models.components.checkout;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import test_data.CreditCardType;

@ComponentCSSSelector("#opc-confirm_order")
public class ConfirmOrderComponent extends Component {

    public static final By confirmBtnSel = By.cssSelector("input[class*='confirm-order-next-step-button']");

    public ConfirmOrderComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }


    public void clickOnConfirmBtn() {
        findElement(confirmBtnSel).click();
        wait.until(ExpectedConditions.urlContains("/checkout/completed/"));
    }
}
