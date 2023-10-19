import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
public class TestSelenium {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/dushanshandoc/Downloads/chromedriver_mac64/chromedriver");
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        driver.get("http://www.google.com");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.findElement(By.name("q")).sendKeys("SFBU");
        actions.sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(2000);
        driver.navigate().to("https://sfbu.edu");
        Thread.sleep(2000);
        String expectedTitle = "SFBU | San Francisco Bay University | SFBU";
        String actualTitle = driver.getTitle();
        System.out.println("Current open web page title is " + actualTitle);
        if (actualTitle.contentEquals(expectedTitle)) {
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }
        driver.close();
        driver.quit();
    }
}
