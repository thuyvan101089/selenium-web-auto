package test_flows.global;

import models.components.global.footer.*;
import models.pages.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import models.components.global.header.CategoryItemComponent;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FooterTestFlow {
    private final WebDriver driver;

    public FooterTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyFooterComponent() {
        Assert.fail("Check case Failed with report");
        BasePage basePage = new BasePage(this.driver);
        InformationColumnComponent informationColumnComp = basePage.footerComp().informationColumnComp();
        CustomerServiceColumnComponent customerServiceColumnComp = basePage.footerComp().customerServiceColumnComp();
        MyAccountColumnComponent myAccountColumnComp = basePage.footerComp().myAccountColumnComp();
        FollowUsColumnComponent followUsColumnComp = basePage.footerComp().followUsColumnComp();

        verifyInformationColumn(informationColumnComp);
        verifyCustomerServiceColumn(customerServiceColumnComp);
        verifyMyAccountColumn(myAccountColumnComp);
        verifyFollowUsColumn(followUsColumnComp);

    }

    public void verifyProductCatFooterComponent() {
        // Randomly pick up MainItem from TopMenuComponent
        BasePage basePage = new BasePage(driver);
        List<CategoryItemComponent> categoryItemComponents = basePage.categoryItemComponents();
        Assert.assertFalse(categoryItemComponents.isEmpty(), "[ERROR] There is no category item on top menu ");
        int randomCategoryComponentIndex = new SecureRandom().nextInt(categoryItemComponents.size());
        CategoryItemComponent randomCategoryComponent = categoryItemComponents.get(randomCategoryComponentIndex);
        String randomCatHef = randomCategoryComponent.catItemLink().getAttribute("href");

        // Get Sublist => click on random sublist or main item (if has no sublist)
        List<WebElement> subListItems = randomCategoryComponent.subListItems();
        if (subListItems.isEmpty()) {
            randomCategoryComponent.catItemLink().click();
        } else {
            int randomSubListIndex = new SecureRandom().nextInt(subListItems.size());
            WebElement randomSubListItem = subListItems.get(randomSubListIndex);
            randomCatHef = randomSubListItem.getAttribute("href");
            randomSubListItem.click();
        }
        // Make sure we are on the right page | Wait until navigation is done
        try {
            WebDriverWait wait = randomCategoryComponent.componentWait();
            wait.until(ExpectedConditions.urlContains(randomCatHef));
        } catch (TimeoutException ignored) {
            Assert.fail("[ERROR]: The target page is not matched");
        }
        // Call common verify method
        verifyFooterComponent();

    }

    private void verifyInformationColumn(FooterColumnComponent informationColumnComp) {
        List<String> expectedLinkTexts = Arrays.asList("Sitemap", "Shipping & Returns", "Privacy Notice", "Conditions of Use", "About us", "Contact us");
        List<String> expectedHrefs = Arrays.asList("/sitemap", "/shipping-returns", "/privacy-policy", "/conditions-of-use", "/about-us", "/contactus");
        testFooterColumn(informationColumnComp, expectedLinkTexts, expectedHrefs, "https://demowebshop.tricentis.com");

    }

    private void verifyCustomerServiceColumn(FooterColumnComponent customerServiceColumnComp) {
        List<String> expectedLinkTexts = Arrays.asList("Search", "News", "Blog", "Recently viewed products", "Compare products list", "New products");
        List<String> expectedHrefs = Arrays.asList("/search", "/news", "/blog", "/recentlyviewedproducts", "/compareproducts", "/newproducts");
        testFooterColumn(customerServiceColumnComp, expectedLinkTexts, expectedHrefs, "https://demowebshop.tricentis.com");
    }

    private void verifyMyAccountColumn(FooterColumnComponent myAccountColumnComp) {
        List<String> expectedLinkTexts = Arrays.asList("My account", "Orders", "Addresses", "Shopping cart", "Wishlist");
        List<String> expectedHrefs = Arrays.asList("/customer/info", "/customer/orders", "/customer/addresses", "/cart", "/wishlist");
        testFooterColumn(myAccountColumnComp, expectedLinkTexts, expectedHrefs, "https://demowebshop.tricentis.com");
    }

    private void verifyFollowUsColumn(FooterColumnComponent followUsColumnComp) {
        List<String> expectedLinkTexts = Arrays.asList("Facebook", "Twitter", "RSS", "YouTube", "Google+");
        List<String> expectedHrefs = Arrays.asList("http://www.facebook.com/nopCommerce", "https://twitter.com/nopCommerce", "https://demowebshop.tricentis.com/news/rss/1", "http://www.youtube.com/user/nopCommerce", "https://plus.google.com/+nopcommerce");
        testFooterColumn(followUsColumnComp, expectedLinkTexts, expectedHrefs, "");
    }

    private void testFooterColumn(FooterColumnComponent footerColumnComponent, List<String> expectedLinkTexts, List<String> expectedHrefs, String link) {
        List<String> actualLinkTexts = new ArrayList<>();
        List<String> actualHrefs = new ArrayList<>();
        expectedHrefs.replaceAll(originalHef -> link + originalHef);

        footerColumnComponent.linkEles().forEach(columnItem -> {
            actualLinkTexts.add(columnItem.getText());
            actualHrefs.add(columnItem.getAttribute("href"));
        });
        if (actualLinkTexts.isEmpty() || actualHrefs.isEmpty()) {
            Assert.fail("There is no column texts or hyperlink");
        }
        Assert.assertEquals(actualLinkTexts, expectedLinkTexts, "[ERROR]: Footer column link texts are different from expected link texts");
        Assert.assertEquals(actualHrefs, expectedHrefs, "[ERROR]: Footer column hyperlink are different from expected hyperlink");

    }
}
