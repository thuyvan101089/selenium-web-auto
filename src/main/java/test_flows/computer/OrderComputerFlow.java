package test_flows.computer;

import models.components.cart.CartItemRowComponent;
import models.components.cart.TotalComponent;
import models.components.checkout.BillingAddressComponent;
import models.components.order.ComputerEssentialComponent;
import models.pages.CheckOutOptionPage;
import models.pages.CheckOutPage;
import models.pages.ComputerItemDetailPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test_data.DataObjectBuilder;
import test_data.computer.ComputerData;
import test_data.user.UserDataObject;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {

    private Class<T> computerEssentialCompClass;
    private WebDriver driver;
    private ComputerData computerData;
    private double itemTotalPrice;
    private int quantity;
    private UserDataObject defaultCheckOutUser;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialCompClass, ComputerData computerData) {
        this.computerEssentialCompClass = computerEssentialCompClass;
        this.driver = driver;
        this.computerData = computerData;
        this.quantity = 1;
    }

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialCompClass, ComputerData computerData, int quantity) {
        this.computerEssentialCompClass = computerEssentialCompClass;
        this.driver = driver;
        this.computerData = computerData;
        this.quantity = quantity;
    }


    public void buildCompSpec() {

        ComputerEssentialComponent computerEssentialComp = new ComputerItemDetailPage(driver).computerEssentialComp(computerEssentialCompClass);
        computerEssentialComp.unselectDefaultOption();
        String processorFullStr = computerEssentialComp.selectProcessorType(this.computerData.getProcessor());
        double processorAdditionalPrice = extractAdditionalPrice(processorFullStr);
        String ramFullStr = computerEssentialComp.selectRAMType(this.computerData.getRam());
        double ramAdditionalPrice = extractAdditionalPrice(ramFullStr);
        String hddFullStr = computerEssentialComp.selectHDD(this.computerData.getHdd());
        double hddAdditionalPrice = extractAdditionalPrice(hddFullStr);
        String softwareFullStr = computerEssentialComp.selectSoftware(this.computerData.getSoftware());
        double softwareAdditionalPrice = extractAdditionalPrice(softwareFullStr);
        double osAdditionalPrice = 0;
        String osDataOption = this.computerData.getOs();
        if (osDataOption != null) {
            String osFullStr = computerEssentialComp.selectOs(osDataOption);
            osAdditionalPrice = extractAdditionalPrice(osFullStr);
        }

        double additionalPrice = processorAdditionalPrice + ramAdditionalPrice + hddAdditionalPrice + softwareAdditionalPrice + osAdditionalPrice;
        computerEssentialComp.setProductQuantity(this.quantity);
        this.itemTotalPrice = (computerEssentialComp.productPrice() + additionalPrice) * this.quantity;
        System.out.println(itemTotalPrice);
    }

    public void addItemToCart() {
        ComputerItemDetailPage computerItemDetailPage = new ComputerItemDetailPage(driver);
        ComputerEssentialComponent computerEssentialComp = computerItemDetailPage.computerEssentialComp(computerEssentialCompClass);
        computerEssentialComp.clickOnAddToCartBtn();
        computerEssentialComp.waitUntilItemAddedToCart();
        computerItemDetailPage.headerComp().clickOnShoppingCartLink();

    }

    public void verifyShoppingCartPage() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        List<CartItemRowComponent> cartItemRowComps = shoppingCartPage.cartItemRowComps();
        // Verify shopping cart detail
        Assert.assertFalse(cartItemRowComps.isEmpty(), "[ERROR]: There is no items in shopping cart");
        double curentSubTotal = 0;
        double currentTotalUnitPrice = 0;
        for (CartItemRowComponent cartItemRowComp : cartItemRowComps) {
            curentSubTotal = curentSubTotal + cartItemRowComp.subTotal();
            currentTotalUnitPrice = currentTotalUnitPrice + (cartItemRowComp.itemQuantity() * cartItemRowComp.unitPrice());

        }
        Assert.assertEquals(currentTotalUnitPrice, curentSubTotal, "[ERROR] Shopping cart subtotal is incorrect! ");

        // Verify checkout price

        TotalComponent totalComponent = shoppingCartPage.totalComponent();
        Map<String, Double> priceCategories = totalComponent.priceCategories();
        Assert.assertFalse(priceCategories.keySet().isEmpty(), "[ERROR]: Checkout price is empty");

        double checkoutSubTotal = 0;
        double checkoutTotal = 0;
        double checkoutOtherFee = 0;

        for (String priceType : priceCategories.keySet()) {
            System.out.printf("%s : %f\n", priceType, priceCategories.get(priceType));
            double priceValue = priceCategories.get(priceType);
            if (priceType.startsWith("Sub-Total")) {
                checkoutSubTotal = priceValue;
            } else if (priceType.startsWith("Total")) {
                checkoutTotal = priceValue;
            } else {
                checkoutOtherFee = checkoutOtherFee + priceValue;
            }
        }

        Assert.assertEquals(checkoutSubTotal, curentSubTotal, "[ERROR]: Checkout SubTotal is incorrect");
        Assert.assertEquals(checkoutTotal, (checkoutSubTotal + checkoutOtherFee), "[ERROR]: Checkout Total is incorrect");
    }

    public void agreeTermsAndCheckout(){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        TotalComponent totalComponent= shoppingCartPage.totalComponent();
        totalComponent.agreeTermService();
        totalComponent.clickOnCheckoutButton();
        // Exception case/. Normally, do not use one flow method for more than one page
        new CheckOutOptionPage(driver).clickOnCheckOutAsGuest();

    }

    public void inputBillingAddress(){
        String defaultCheckOutUserDataLoc = "/src/main/java/test_data/user/CheckOutDefaultUser.json";
        this.defaultCheckOutUser = DataObjectBuilder.buildDataObjectFrom(defaultCheckOutUserDataLoc, UserDataObject.class);
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        BillingAddressComponent billingAddressComp = checkOutPage.billingAddressComp();
        billingAddressComp.selectInputNewAddress();
        billingAddressComp.inputFirstName(defaultCheckOutUser.getFirstName());
        billingAddressComp.inputLastName(defaultCheckOutUser.getLastName());
        billingAddressComp.inputEmail(defaultCheckOutUser.getEmail());
        billingAddressComp.selectCountry(defaultCheckOutUser.getCounty());
        billingAddressComp.selectState(defaultCheckOutUser.getState());
        billingAddressComp.inputCity(defaultCheckOutUser.getCity());
        billingAddressComp.inputAddress1(defaultCheckOutUser.getAdd1());
        billingAddressComp.inputZipcode(defaultCheckOutUser.getZipcode());
        billingAddressComp.inputPhoneNumber(defaultCheckOutUser.getPhoneNumber());
        billingAddressComp.clickContinueBtn();

    }

    public double extractAdditionalPrice(String optionStr) {
        // Medium  [+15.00]
        double price = 0;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(optionStr);
        if (matcher.find()) {
            price = Double.parseDouble(matcher.group(1).replaceAll("[+-]", ""));
        }
        return price;
    }


}