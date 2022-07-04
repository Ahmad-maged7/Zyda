import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;

public class TestBase {
    protected WebDriver driver;

    @BeforeClass
    protected void goToBasicCheckout() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://automationpractice.com/index.php");

    }

    @AfterMethod
    protected void ScreenShotForFailure(ITestResult result) throws IOException, InterruptedException {
        String screenShotName = this.getClass().getSimpleName();
        if (result.getStatus() == ITestResult.FAILURE) {
            Thread.sleep(500);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.body.style.zoom = '0.5'");
            Thread.sleep(5000);
            TakesScreenshot scrShot = (TakesScreenshot) driver;
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File(System.getProperty("user.dir") + "/screenShots/" + "Failed " + screenShotName + ".png");
            FileUtils.copyFile(SrcFile, DestFile);
            System.out.println("Failed!,,,, Taking ScreenShoot");
        }
    }

    @AfterClass
    protected void closeBrowser() {
        driver.quit();
    }
}
