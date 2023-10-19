import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class priorityWebpagesTest {
    WebDriver driver;
    String Url_1 = "http://cnn.com"; // 3rd open web page, enabled
    String Url_2 = "http://yahoo.com"; // 2nd open web page, not enabled
    String Url_3 = "http://msn.com"; // 1st open page, enabled

    @Test(priority = 3, enabled = true)
    public void openCNN() throws InterruptedException {
        driver.get(Url_1);
        driver.manage().window().maximize();
        Thread.sleep(2000);
        System.out.println("opening cnn.");
    }

    @Test(priority = 1, enabled = false)
    public void openYahoo() throws InterruptedException {
        driver.get(Url_2);
        driver.manage().window().maximize();
        Thread.sleep(2000);
        System.out.println("opening yahoo.");
    }

    @Test(priority = 0, enabled = true)
    public void openMSN() throws InterruptedException {
        driver.get(Url_3);
        driver.manage().window().maximize();
        Thread.sleep(2000);
        System.out.println("opening msn.");
    }

    @BeforeTest
    public void initiate() {
        System.setProperty("webdriver.chrome.driver","C:\\Drivers\\Selenium\\chrome\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterTest
    public void Teardown() {
//        driver.close();
        driver.quit();
    }

}
