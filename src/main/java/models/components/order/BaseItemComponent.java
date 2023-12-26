package models.components.order;

import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BaseItemComponent extends Component {
    public static final By barNotification = By.id("bar-notification");
    public static final By headerAddToCartLinkSel = By.cssSelector("#bar-notification a");
    public static final By productPriceSel = By.cssSelector(".product-price");
    public static final By quantitySel = By.cssSelector(".add-to-cart input[class^='qty-input']");

    public static final By addToCartButton = By.cssSelector("input[id^='add-to-cart-button']");

    public BaseItemComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void clickOnShoppingCartLink() {
        component.findElement(headerAddToCartLinkSel).click();
    }
    public double productPrice() {
        String productPriceStr = component.findElement(productPriceSel).getText().trim();
        return Double.parseDouble(productPriceStr);
    }
    public void setProductQuantity(int quantity) {
      component.findElement(quantitySel).clear();
      component.findElement(quantitySel).sendKeys(String.valueOf(quantity));

    }
    public void clickOnAddToCartBtn() {
        findElement(addToCartButton).click();
    }
    public void waitUntilItemAddedToCart(){
        String successfulMessage ="The product has been added to your ";
        wait.until(ExpectedConditions.textToBePresentInElementLocated(barNotification,successfulMessage));

    }
}
