package models.components.checkout;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import test_data.CreditCardType;

@ComponentCSSSelector("#opc-payment_info")
public class PaymentInformationComponent extends Component {

    public static final By creditCardDropdownSel = By.id("CreditCardType");
    public static final By cardholderNameSel = By.id("CardholderName");
    public static final By cardNumberSel = By.id("CardNumber");
    public static final By cardExpireMonthDropdownSel = By.id("ExpireMonth");
    public static final By cardExpireYearDropdownSel = By.id("ExpireYear");
    public static final By CardCodeSel = By.id("CardCode");
    public static final By purchaseOrderNumberSel = By.id("PurchaseOrderNumber");
    public static final By continueBtnSel = By.cssSelector("input[class*='payment-info-next-step-button']");

    public PaymentInformationComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectCardType(CreditCardType creditCardType) {
        Assert.assertNotNull(creditCardType, "[ERROR]: Card Type cannot be Null");
        Select select = new Select(findElement(creditCardDropdownSel));
        switch (creditCardType) {
            case VISA:
                select.selectByVisibleText("Visa");
                break;
            case MASTER_CARD:
                select.selectByVisibleText("Master card");
                break;
            case DISCOVER:
                select.selectByVisibleText("Discover");
                break;
            case AMEX:
                select.selectByVisibleText("Amex");
                break;
        }

    }

    public void inputCardHolderName(String name) {
        findElement(cardholderNameSel).sendKeys(name);
    }

    public void inputCardNumber(String cardNumber) {
        findElement(cardNumberSel).sendKeys(cardNumber);
    }

    public void inputExpiredMonth(String month) {
        Select select = new Select(findElement(cardExpireMonthDropdownSel));
        select.selectByVisibleText(month);
    }

    public void inputExpiredYear(String year) {
        Select select = new Select(findElement(cardExpireYearDropdownSel));
        select.selectByVisibleText(year);
    }

    public void inputCardCode(String code) {
        findElement(CardCodeSel).sendKeys(code);
    }

    public void inputPurchaseOderNumber(String purchaseOrderNumber) {
        findElement(purchaseOrderNumberSel).sendKeys(purchaseOrderNumber);
    }


    public void clickOnContinueBtn() {
        findElement(continueBtnSel).click();
    }
}
