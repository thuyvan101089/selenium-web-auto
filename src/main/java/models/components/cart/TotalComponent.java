package models.components.cart;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ComponentCSSSelector(".cart-footer .totals")
public class TotalComponent extends Component {
    private static final By priceTableRowSel = By.cssSelector("table tr");
    private static final By priceTypeRowSel = By.cssSelector(".cart-total-left");
    private static final By priceValueRowSel = By.cssSelector(".cart-total-right");

    public TotalComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public Map<String, Double> priceCategories() {
        Map<String, Double> priceCategories = new HashMap<>();
        List<WebElement> priceTableRowEles = findElements(priceTableRowSel);
        Assert.assertFalse(priceTableRowEles.isEmpty(), "ERROR: Price Table Row is empty");

        for (WebElement priceTableRowEle : priceTableRowEles) {
            WebElement priceTypeRowEle = priceTableRowEle.findElement(priceTypeRowSel);
            WebElement priceValueRowEle = priceTableRowEle.findElement(priceValueRowSel);
            String priceType = priceTypeRowEle.getText().replace(":", "").trim();
            Double priceValue = Double.valueOf(priceValueRowEle.getText().trim());
            priceCategories.put(priceType, priceValue);
        }

        return priceCategories;
    }

}
