import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestEbayMultiPricesSearch1 {
    WebDriver driver;

    String myWebBrowserDriver = "webdriver.chrome.driver";
    String myDriverPath = "/Users/dushanshandoc/Downloads/chromedriver_mac64/chromedriver";
    String url = "http://www.ebay.com/";
    int sleepTime = 5000;
    String msg = "not found the price meets my expectation ";

    String item1 = "JBL Speakers";
    String price1 = "$65.45";

    String item2 = "cell phones";
    String price2a = "$325.90";
    String price2b = "$244.00";
    String price2c = "$69.78";

    String item3 = "womens shoes";
    String price3a = "$71.97";
    String price3b = "$29.99";
    String price3c = "$89.96";

    @BeforeTest
    void setup(){
        System.setProperty(myWebBrowserDriver, myDriverPath);
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterTest
    void tearDown(){
        driver.quit();
    }

    @Test(priority = 3, enabled = true)
    void searchItem1() throws InterruptedException {
        driver.findElement(By.id("gh-ac")).clear();
        driver.findElement(By.id("gh-ac")).sendKeys(item1);
        driver.findElement(By.id("gh-btn")).click();
        Thread.sleep(sleepTime);
        boolean b = driver.getPageSource().contains(price1);
        Assert.assertTrue(b, msg + price1);
    }

    @Test(priority = 1, enabled = true)
    void searchItem3() throws InterruptedException {
        driver.findElement(By.id("gh-ac")).clear();
        driver.findElement(By.id("gh-ac")).sendKeys(item3);
        driver.findElement(By.id("gh-btn")).click();
        Thread.sleep(sleepTime);
        boolean b = driver.getPageSource().contains(price3a) ||
                driver.getPageSource().contains(price3b) ||
                driver.getPageSource().contains(price3c);
        Assert.assertTrue(b, msg + price3a + " or " + price3b + " or " + price3c);
    }

    @Test(priority = 2, enabled = false)
    void searchItem2() throws InterruptedException {
        driver.findElement(By.id("gh-ac")).clear();
        driver.findElement(By.id("gh-ac")).sendKeys(item2);
        driver.findElement(By.id("gh-btn")).click();
        Thread.sleep(sleepTime);
        boolean b = driver.getPageSource().contains(price2a) ||
                driver.getPageSource().contains(price2b) ||
                driver.getPageSource().contains(price2c);
        Assert.assertTrue(b, msg + price2a + " or " + price2b + " or " + price2c);
    }

}
