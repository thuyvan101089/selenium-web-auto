package api_learning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.NoSuchElementException;

public class MouseHoverAndNarrowDownSearchingScope {
    private static final String TARGET_URL = "https://the-internet.herokuapp.com/hovers";
    private static final By FIGURE_SEL = By.cssSelector(".figure");
    private static final By PROFILENAME_SEL = By.cssSelector(".figcaption h5");
    private static final By PROFILELINK_SEL = By.cssSelector(".figcaption a");

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get(TARGET_URL);
        try {
            /*
             * Search global => means from html tag
             * driver.findElement(s)
             * Narrow down searching scope with parent scope is now the element
             * Web Element.findElement(locator)
             *
             * */
            // target parent elements
            List<WebElement> figureEles = driver.findElements(FIGURE_SEL);
            if (figureEles.isEmpty()) {
                throw new NoSuchElementException("No figure on the page");
            }
            Actions actions = new Actions(driver);
            // Find the child element
            for (WebElement figureEle : figureEles) {
                WebElement profileNameEle = figureEle.findElement(PROFILENAME_SEL);
                WebElement profileLinkEle = figureEle.findElement(PROFILELINK_SEL);
                boolean isProfileNameDisplayed = profileNameEle.isDisplayed();
                boolean isProfileLinkDisplayed = profileLinkEle.isDisplayed();
                System.out.println("Before | Is Profile Name Displayed: " + isProfileNameDisplayed);
                System.out.println("Before | Is Profile Link Displayed: " + isProfileLinkDisplayed);
                // Mouse Hover
                actions.moveToElement(figureEle).perform();
                isProfileNameDisplayed = profileNameEle.isDisplayed();
                isProfileLinkDisplayed = profileLinkEle.isDisplayed();
                System.out.println(profileNameEle.getText());
                System.out.println(profileLinkEle.getAttribute("href"));
                System.out.println("After | Is Profile Name Displayed: " + isProfileNameDisplayed);
                System.out.println("After | Is Profile Link Displayed: " + isProfileLinkDisplayed);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }


}
