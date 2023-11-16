package api_learning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import support.ui.SelectEx;

public class DropdownInteraction {
    private static final String TARGET_URL = "https://the-internet.herokuapp.com/dropdown";
    private static final By DROPDOWN_SEL = By.id("dropdown");

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get(TARGET_URL);
        try {
            // Locate Dropdown and select the dropdown
            WebElement dropdownEle = driver.findElement(DROPDOWN_SEL);
            Select select = new Select(dropdownEle);
            SelectEx selectEx = new SelectEx(dropdownEle);
            // by visible text
            selectEx.selectOption1();
            Thread.sleep(1500);
            //  select.selectByVisibleText("Option 1");
            // by value
            select.selectByValue("2");
            Thread.sleep(1500);
            // by index
            select.selectByIndex(1);
            Thread.sleep(1500);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

