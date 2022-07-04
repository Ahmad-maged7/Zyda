package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class HomePage extends PageBase {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, 10);

    @FindBy(id = "header_logo")
    public WebElement logo;
    @FindBy(id = "home-page-tabs")
    public WebElement homePageTabs;
    @FindBy(id = "homefeatured")
    WebElement popularList;
    WebElement firstItem = popularList.findElements(By.tagName("li")).get(0);
    String firstItemName = firstItem.getText();
    @FindBy(id = "layer_cart")
    WebElement addConfirmationPopup;
    WebElement secondItem = popularList.findElements(By.tagName("li")).get(1);
    String secondItemName = secondItem.getText();


    public void addFirstItemToCart() throws InterruptedException {

        System.out.println(firstItemName);
        Actions actions = new Actions(driver);
        actions.moveToElement(firstItem).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(firstItem.findElement(By.linkText("Add to cart"))));
        firstItem.findElement(By.linkText("Add to cart")).click();
        wait.until(ExpectedConditions.visibilityOf(addConfirmationPopup));
        System.out.println("Added item: "+addConfirmationPopup.findElement(By.id("layer_cart_product_title")).getText());
        Assert.assertTrue(firstItemName.contains(addConfirmationPopup.findElement(By.id("layer_cart_product_title")).getText()));
        System.out.println("Selected item is added successfully");
        addConfirmationPopup.findElement(By.xpath("//header/div[3]/div[1]/div[1]/div[4]/div[1]/div[1]/span[1]")).click();
    }

    public void addSecondItemToCart() throws InterruptedException {

        System.out.println(secondItemName);
        Actions actions = new Actions(driver);
        actions.moveToElement(secondItem).build().perform();
        Thread.sleep(500);//for some reason, static sleep is needed here
        wait.until(ExpectedConditions.elementToBeClickable(secondItem.findElement(By.linkText("Add to cart"))));
        secondItem.findElement(By.linkText("Add to cart")).click();
        wait.until(ExpectedConditions.visibilityOf(addConfirmationPopup));
        System.out.println("Added item: "+addConfirmationPopup.findElement(By.id("layer_cart_product_title")).getText());
        Assert.assertTrue(secondItemName.contains(addConfirmationPopup.findElement(By.id("layer_cart_product_title")).getText()));
        System.out.println("Selected item is added successfully");

    }

    public void goToCheckout()
    {
        addConfirmationPopup.findElement(By.linkText("Proceed to checkout")).click();
        Assert.assertTrue(driver.getCurrentUrl().equals("http://automationpractice.com/index.php?controller=order"));
    }

}
