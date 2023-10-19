import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestEbayMultiPricesSearch2 {
    WebDriver driver;
    String myWebBrowserDriver = "webdriver.chrome.driver";
    String myDriverPath = "/Users/dushanshandoc/Downloads/chromedriver-mac-x64/chromedriver";

    String url = "http://www.ebay.com/";
    int sleepTime = 5000;
    String msg = "not found the price meets my expectation ";

    String item1 = "JBL Speakers";
    String price1a = "$65.45";
    String price1b = "$119.99";
    String price1c = "159.99";

    String item2 = "cell phones";
    String price2a = "$325.90";
    String price2b = "$244.00";
    String price2c = "$69.78";

    String item3 = "womens shoes";
    String price3a = "$71.97";
    String price3b = "$29.99";
    String price3c = "$89.96";

    @BeforeTest
    void setup() {
        System.setProperty(myWebBrowserDriver, myDriverPath);
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterTest
    void tearDown() {
        driver.quit();
    }

    @Test(priority = 3, enabled = true)
    void searchJBLSpeakersComparePrices() throws InterruptedException {
        searchItem(item1, price1a, price1b, price1c);
    }

    @Test(priority = 1, enabled = true)
    void searchWomensShowsComparePrices() throws InterruptedException {
        searchItem(item3, price3a, price3b, price3c);
    }

    @Test(priority = 2, enabled = false)
    void searchCellPhoneComparePrices() throws InterruptedException {
        searchItem(item2, price2a, price2b, price2c);
    }

    void searchItem(String item, String priceA, String priceB, String priceC) throws InterruptedException {
        driver.findElement(By.id("gh-ac")).clear();
        driver.findElement(By.id("gh-ac")).sendKeys(item);
        driver.findElement(By.id("gh-btn")).click();
        Thread.sleep(sleepTime);
        boolean b = driver.getPageSource().contains(priceA) ||
                driver.getPageSource().contains(priceB) ||
                driver.getPageSource().contains(priceC);
        Assert.assertTrue(b, msg + priceA + " or " + priceB + " or " + priceC);
    }
}
