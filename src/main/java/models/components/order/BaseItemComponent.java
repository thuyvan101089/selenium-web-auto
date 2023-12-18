package models.components.order;

import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseItemComponent extends Component {
    public static final By productPriceSel = By.cssSelector(".product-price");
    public static final By addToCartButton = By.cssSelector("input[id^='add-to-cart-button']");

    public BaseItemComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public double productPrice() {
        String productPriceStr = component.findElement(productPriceSel).getText().trim();
        return Double.parseDouble(productPriceStr);
    }

    public void clickOnAddToCartBtn() {
        findElement(addToCartButton).click();
    }
}
