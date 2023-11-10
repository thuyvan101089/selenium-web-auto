package api_learning;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LauchBrowser {
    public static void main(String[] args) {
        /*
        * 1. Check the current browser version
        * 2. Find the compatible browser driver from the central and then download
        * 3. Use it as browser driver
        * */
        WebDriver driver = new ChromeDriver();
        // Open the web page
        driver.get("https://www.notion.so/");
        // Close the window
        driver.close();
        //Quit the session
        driver.quit();


    }
}
