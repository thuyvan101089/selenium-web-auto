package test_flows.computer;

import models.components.cart.CartItemRowComponent;
import models.components.cart.TotalComponent;
import models.components.checkout.*;
import models.components.order.ComputerEssentialComponent;
import models.pages.CheckOutOptionPage;
import models.pages.CheckOutPage;
import models.pages.ComputerItemDetailPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test_data.CreditCardType;
import test_data.DataObjectBuilder;
import test_data.PaymentMethod;
import test_data.computer.ComputerData;
import test_data.user.UserDataObject;

import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {

    private Class<T> computerEssentialCompClass;
    private WebDriver driver;
    private ComputerData computerData;
    private double itemTotalPrice;
    private int quantity;
    private UserDataObject defaultCheckOutUser;
    private PaymentMethod paymentMethod;
    private CreditCardType creditCardType;

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

    public void agreeTermsAndCheckout() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        TotalComponent totalComponent = shoppingCartPage.totalComponent();
        totalComponent.agreeTermService();
        totalComponent.clickOnCheckoutButton();
        // Exception case/. Normally, do not use one flow method for more than one page
        new CheckOutOptionPage(driver).clickOnCheckOutAsGuest();

    }

    public void inputBillingAddress() {
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

    public void inputShippingAddress() {
        new CheckOutPage(driver).shippingAddressComp().clickOnContinueBtn();
    }

    public void selectShippingMethod() {
        List<String> shippingMethods = Arrays.asList("Ground", "Next Day Air", "2nd Day Air");
        int randomShoppingMethodIndex = new SecureRandom().nextInt(shippingMethods.size());
        String randomShoppingMethod = shippingMethods.get(randomShoppingMethodIndex);
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        ShippingMethodComponent shippingMethodComp = checkOutPage.shippingMethodComp();
        shippingMethodComp.selectShippingMethod(randomShoppingMethod);
        shippingMethodComp.clickOnContinueBtn();
    }

    public void selectPaymentMethod() {
        this.paymentMethod = PaymentMethod.COD;
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        PaymentMethodComponent paymentMethodComp = checkOutPage.paymentMethodComp();
        paymentMethodComp.selectCODMethod();
    }

    public void selectPaymentMethod(PaymentMethod paymentMethod) {
        Assert.assertNotNull(paymentMethod, "[ERROR]: Payment method cannot be NULL");
        this.paymentMethod = paymentMethod;
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        PaymentMethodComponent paymentMethodComp = checkOutPage.paymentMethodComp();
        switch (paymentMethod) {
            case CHECK_MONEY_ORDER:
                paymentMethodComp.selectMoneyOrderMethod();
                break;
            case CREDIT_CARD:
                paymentMethodComp.selectCreditCardMethod();
                break;
            case PURCHASE_ORDER:
                paymentMethodComp.selectPurchaseOrderMethod();
            default:
                paymentMethodComp.selectCODMethod();

        }
        paymentMethodComp.clickOnContinueBtn();

    }

    public void inputPaymentInfo(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        PaymentInformationComponent paymentInformationComp = checkOutPage.paymentInfomationComp();
        if (this.paymentMethod.equals(PaymentMethod.PURCHASE_ORDER)) {
            paymentInformationComp.inputPurchaseOderNumber("123456");
        } else if (this.paymentMethod.equals(PaymentMethod.CREDIT_CARD)) {
            paymentInformationComp.selectCardType(creditCardType);
            String cardHolderFirstName = this.defaultCheckOutUser.getFirstName();
            String cardHolderLastName = this.defaultCheckOutUser.getLastName();
            paymentInformationComp.inputCardHolderName(cardHolderFirstName + " " + cardHolderLastName);
            String cardNumber = creditCardType.equals(CreditCardType.VISA) ? "4012888888881881" : "6011000990139424";
            paymentInformationComp.inputCardNumber(cardNumber);
            Calendar calendar = new GregorianCalendar();
            int expiredMonthNum = calendar.get(Calendar.MONTH) + 1;
            String expiredMonthString = expiredMonthNum < 10 ? "0" + expiredMonthNum : String.valueOf(expiredMonthNum);
            paymentInformationComp.inputExpiredMonth(expiredMonthString);
            paymentInformationComp.inputExpiredYear(String.valueOf(calendar.get(Calendar.YEAR) + 1));
            paymentInformationComp.inputCardCode("123");
            paymentInformationComp.clickOnContinueBtn();
        } else if (this.paymentMethod.equals(PaymentMethod.COD)) {
            // Verify text = You will pay by COD
        } else {
            //Verify Text = Mail Personal or Business Check, Cashier's Check or money order to:
        }

    }

    public void confirmOrder() {
        new CheckOutPage(driver).confirmOrderComp().clickOnConfirmBtn();
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