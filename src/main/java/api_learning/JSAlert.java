package api_learning;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JSAlert {
    private static final String TARGET_URL = "https://the-internet.herokuapp.com/javascript_alerts";
    private static final By JS_ALERT_SEL = By.cssSelector("[onclick = 'jsAlert()']");
    private static final By JS_ALERT_CONFIRM_SEL = By.cssSelector("[onclick = 'jsConfirm()']");
    private static final By JS_ALERT_PROMPT_SEL = By.cssSelector("[onclick = 'jsPrompt()");
    private static final By RESULT_SEL = By.cssSelector("#result");



    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get(TARGET_URL);
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            WebElement resultTextEle = driver.findElement(RESULT_SEL);
            //JS alert interaction
            handleJSAlert(driver,JS_ALERT_SEL,true);
            System.out.println(resultTextEle.getText());
            //JS confirm alert interaction
            handleJSAlert(driver,JS_ALERT_CONFIRM_SEL,false);
            System.out.println(resultTextEle.getText());
            //JS Prompt Interaction
            handleJSAlert(driver,JS_ALERT_PROMPT_SEL,true,"Hello!!");
            System.out.println(resultTextEle.getText());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private static void handleJSAlert (WebDriver driver, By triggerSelector, boolean isAccepting, String ...messages){
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.findElement(triggerSelector).click();
        Alert JSAlert = webDriverWait.until(ExpectedConditions.alertIsPresent());
        if(messages.length>0){
            JSAlert.sendKeys(messages[0]);
            makeJSAlertDecision(JSAlert,isAccepting);
        }else {
            makeJSAlertDecision(JSAlert,isAccepting);
        }

    }

    private static void makeJSAlertDecision(Alert jsAlert, boolean isAccepting) {
        if(isAccepting){
            jsAlert.accept();
        }else {
            jsAlert.dismiss();
        }
    }
}

