package models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutOptionPage extends BasePage{
    private static final By checkOutAsGuestBtn = By.cssSelector("input[class*='checkout-as-guest-button']");
    public CheckOutOptionPage(WebDriver driver) {
        super(driver);
    }
    public void clickOnCheckOutAsGuest(){
        findElement(checkOutAsGuestBtn).click();
    }
}
