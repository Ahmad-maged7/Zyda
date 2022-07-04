import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class AddItemToCardThenCheckout extends TestBase
{
    HomePage homeObject;

    @Test
    public void addItemsToCart() throws InterruptedException {
        homeObject = new HomePage(driver);
        Assert.assertTrue(homeObject.logo.isDisplayed());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)",homeObject.homePageTabs);
        Thread.sleep(1000);
        homeObject.addFirstItemToCart();
        homeObject.addSecondItemToCart();
        homeObject.goToCheckout();
    }
}
