package test;

import driver.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BaseTest {
    protected static WebDriver driver;

    @BeforeTest
    protected void initBrowserSession() {
        driver = DriverFactory.getDriver();
    }

    @AfterTest(alwaysRun = true)
    protected void closeBrowserSession() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterMethod
    public void captureScreenShot(ITestResult result) {
        boolean isTestFailed = result.getStatus() == ITestResult.FAILURE;
        if (isTestFailed) {
            attachScreenShotToReport(result);
        }

    }

    private void attachScreenShotToReport(ITestResult result) {
        // 1. Get method name
        String methodName = result.getName();
        // 2. Get taken Time
        Calendar calendar = new GregorianCalendar();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH) + 1;
        int d = calendar.get(Calendar.DATE);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int s = calendar.get(Calendar.SECOND);
        String fileName = methodName + "-" + y + "-" + m + "-" + d + "-" + h + "-" + min + "-" + s + ".png";


        // 3. Take ScreenShot
        File screenShotBase64Data = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String fileLocation = System.getProperty("user.dir") + "/screenshots/" + fileName;
            FileUtils.copyFile(screenShotBase64Data, new File(fileLocation));
            Path content = Paths.get(fileLocation);
            try (InputStream inputStream = Files.newInputStream(content)) {
                Allure.addAttachment(methodName, inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        // 4. Save
        // 5. Attach to the report
    }
}
