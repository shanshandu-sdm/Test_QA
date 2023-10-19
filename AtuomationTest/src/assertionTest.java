import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class assertionTest {

    WebDriver driver;

    @BeforeClass
    void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","/Users/dushanshandoc/Downloads/chromedriver-mac-x64/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://www.ebay.com/");
        driver.manage().window().maximize();
        Thread.sleep(3000);
    }

    @Test(priority = 1, enabled = true)
    void logoTest(){
        WebElement logo = driver.findElement(By.id("gh-logo"));
        Assert.assertTrue(logo.isDisplayed(), "eBay logo should display in the web page.");
    }

    @Test (priority = 2, enabled = true)
    void homepageTitleTest(){
        String title = driver.getTitle();
//        Assert.assertEquals(title, "abc", "web page title doesn't match.");
        Assert.assertEquals(title, "Electronics, Cars, Fashion, Collectibles & More | eBay", "web page title doesn't match.");
    }

    @Test (priority = 3, enabled = true)
    void checkLowestPrice() throws InterruptedException {
        driver.findElement(By.id("gh-ac")).sendKeys("JBL Speakers");
        driver.findElement(By.id("gh-btn")).click();
        Thread.sleep(2000);

        boolean b = driver.getPageSource().contains("$139.99");
        Assert.assertTrue(b, "not found the price meets my expectation.");
    }

    @Test (priority = 4, enabled = true)
    void comparePrices() throws InterruptedException {
        driver.findElement(By.id("gh-ac")).clear();
        driver.findElement(By.id("gh-ac")).sendKeys("Wommen Shoes");
        driver.findElement(By.id("gh-btn")).click();
        Thread.sleep(2000);

        String expectedPrice = "49.99";
        String actualPrice = "59.99";
        Assert.assertEquals(expectedPrice, actualPrice, "Expected price is " + expectedPrice + " Actual Price is " + actualPrice);
    }
    @AfterClass
    void tearDown(){
        driver.quit();
    }

}
