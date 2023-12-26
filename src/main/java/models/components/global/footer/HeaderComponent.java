package models.components.global.footer;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ComponentCSSSelector(value = ".header")
public class HeaderComponent extends Component {

    private static final By shoppingCartLinkSel = By.id("topcartlink");

    public HeaderComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void clickOnShoppingCartLink(){
        WebElement shoppingCartLinkEle = findElement(shoppingCartLinkSel);
        Actions actions = new Actions(driver);
        actions.moveToElement(shoppingCartLinkEle).click().build().perform();

    }
}