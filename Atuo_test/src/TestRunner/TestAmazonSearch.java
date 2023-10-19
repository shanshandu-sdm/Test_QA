package TestRunner;/*
 * Name: shanshan du
 * ID: 19850
 * Class: CS522 * Assignment: 9 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

import java.io.File;


import java.time.Duration;

public class TestAmazonSearch {
    // add global definition here (10%)
    WebDriver driver;
    String myWebBrowserDriver = "webdriver.chrome.driver";
    String myWebBrowserDriverPath = "/Users/dushanshandoc/Downloads/chromedriver-mac-x64/chromedriver";
    String urlEbay = "http://www.ebay.com/";
    String urlAmazon = "http://www.amazon.com/";

    // Launch browser and go to amazon
    void launchBrowserGoToAmazon() throws InterruptedException {
        System.setProperty(myWebBrowserDriver, myWebBrowserDriverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(urlAmazon);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    // Maximize window size
    void maximizeWindowSize() {
        driver.manage().window().maximize();
    }

    // Input a product item and click Search
    void searchProduct() {

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("MacBook Pro");

        driver.findElement(By.id("nav-search-submit-button")).click();
    }

    // Click the link Best Sellers
    void openLinkBestSellers() throws InterruptedException {

        driver.findElement(By.linkText("Best Sellers")).click();

        Thread.sleep(2000);
    }

    // Navigate to ebay.com then go back
    void navigateToEbayGoBack() throws InterruptedException {
        driver.navigate().to(urlEbay);
        Thread.sleep(2000);
        driver.navigate().back();
        Thread.sleep(2000);
    }

    // Select a search category
    void selectCategory() throws InterruptedException {
        driver.findElement(By.id("searchDropdownBox")).click();
        // select value electronics from dropdown
        driver.findElement(By.cssSelector("option[value='searchalias=electronics']")).click();
        Thread.sleep(2000);
    }

    // Close browser and exit
    void closeExit() {

        driver.close();

        driver.quit();
    }


    void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        //Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        File DestFile = new File(fileWithPath);
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
    }


    public static void main(String[] args) throws InterruptedException {
        TestAmazonSearch obj = new TestAmazonSearch();

        obj.launchBrowserGoToAmazon();
        obj.maximizeWindowSize();
        obj.selectCategory();
        obj.searchProduct();
        obj.openLinkBestSellers();
        obj.navigateToEbayGoBack();
        obj.closeExit();

    }

}