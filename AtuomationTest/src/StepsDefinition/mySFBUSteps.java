package StepsDefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
//import org.testng.Assert;
import org.junit.Assert;
import java.time.Duration;
import java.util.List;

public class mySFBUSteps {

    WebDriver driver;
    Actions actions;
    String driverPath = "/Users/dushanshandoc/Downloads/chromedriver";
    static String sfbuUrl = "https://sfbu.edu";
    static String linkCreateAccount = "Create Account";
    static String applicantType = "new";
    static String myFirstName = "Charles";
    static String myLastName = "Sfbu";
    static String myGender = "Male";
    static String myEmail = "myEmail@student.sfbu.edu";
    static String my1stPassword = "1234";
    static String my2ndPassword = "5678";
    static String mySurveyType = "NPU student/alumni";
    static String mySurveyTypeDetails = "My Friend";

        static String passwordTooShortMsg = "'Password' must be at least 8 characters long.";
//    static String passwordTooShortMsg = "I love SFBU.";
    static String passwordConfirmMsg = "The password and confirmation password do not match.";
    static String otherMsg = null;

    @Given("Open the Chrome and launch the SFBU application")
    public void open_the_Chrome_and_launch_the_SFBU_application() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        actions = new Actions(driver);
        driver.get(sfbuUrl);
        driver.manage().window().maximize();
    }

    @When("Open Application Portal")
    public void open_Application_Portal() throws InterruptedException {
        // tab bar click Apply Today
        driver.findElement(By.cssSelector("#block-sfbu-mainnavigationright > ul > li:nth-child(1) > a")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Given("Open SFBU Application Portal")
    public void open_sfbu_application_portal() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        actions = new Actions(driver);
        driver.get(sfbuUrl);
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("#block-sfbu-mainnavigationright > ul > li:nth-child(1) > a")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Then("Logout")
    public void logout(){
        driver.close();
        driver.quit();
    }

    @And("Create Account")
    public void create_Account() throws InterruptedException {
        driver.findElement(By.linkText(linkCreateAccount)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @When("Select New Applicant")
    public void select_New_Applicant() {
        // Write code here that turns the phrase above into concrete actions
        List<WebElement> appType = driver.findElements(By.name("userType"));
        int size = appType.size();
        for (int i = 0; i < size; i++) {
            String getValue = appType.get(i).getAttribute("value");
            if (getValue.equalsIgnoreCase(applicantType)) {
                appType.get(i).click();
                break;
            }
        }
    }

    @When("Enter Names")
    public void enter_Names() {
        // Enter Given Name/First Name
        for (int i = 0; i < 3; i++) {
            actions.sendKeys(Keys.TAB).build().perform();
        }
        actions.sendKeys(myFirstName);
        actions.build().perform();

        // Enter Surname/Primary Name
        driver.findElement(By.id("LastName")).sendKeys(myLastName);
    }

    @When("Select Gender")
    public void select_Gender() throws InterruptedException {
        new Select(driver.findElement(By.name("Gender"))).selectByVisibleText(myGender);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @When("Enter Email")
    public void enter_Email() {
        driver.findElement(By.id("Email")).sendKeys(myEmail);
    }

    @When("Enter Passwords")
    public void enter_Passwords() {
        // Enter an invalid (not qualified) password
        driver.findElement(By.id("Password")).sendKeys(my1stPassword);
        // Enter Confirm password
        driver.findElement(By.id("ConfirmPassword")).sendKeys(my2ndPassword);
    }

    @When("Select Survey")
    public void select_Survey() throws InterruptedException {
        driver.findElement(By.id("survey_0_3")).click();
        driver.findElement(By.id("userInput_survey_0_3")).sendKeys(mySurveyTypeDetails);
//        switch (type) {
//            case "Internet search":
//                driver.findElement(By.id("survey_0_0")).click();
//                driver.findElement(By.id("userInput_survey_0_0")).sendKeys(mySurveyTypeDetails);
//                break;
//            case "NPU student/alumni":
//                driver.findElement(By.id("survey_0_3")).click();
//                driver.findElement(By.id("userInput_survey_0_3")).sendKeys(mySurveyTypeDetails);
//                break;
//            case "Community event":
//                driver.findElement(By.id("survey_0_5")).click();
//                driver.findElement(By.id("userInput_survey_0_5")).sendKeys(mySurveyTypeDetails);
//                break;
//            default:
//                driver.findElement(By.id("survey_0_8")).click();
//                driver.findElement(By.id("userInput_survey_0_8")).sendKeys(mySurveyTypeDetails);
//        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Then("Error Messages are popup")
    public Boolean error_Messages_are_popup() {
        // Write code here that turns the phrase above into concrete actions
        boolean b = driver.getPageSource().contains(passwordTooShortMsg);
        try {
            Assert.assertTrue(b);
            System.out.println("Found expected message: " + passwordTooShortMsg + " ----- Test Passed!");
        } catch (Throwable e){
            System.out.println("Not Found expected message: " + passwordTooShortMsg + " ----- Test Failed");
        }
        return b;
    }

    @Then("Close and exit")
    public void close_and_exit() {
        driver.quit();
    }

    @When("Enter the Username as {string} and Password {string}")
    public void enterTheUsernameAsAndPassword(String username, String password) throws InterruptedException {
        driver.findElement(By.name("UserName")).sendKeys(username);
        driver.findElement(By.name("Password")).sendKeys(password);
        System.out.println("Username is: " + username);
        System.out.println("Password is: " + password);
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Then("Login should be failed {string}")
    public void login_should_be_failed(String message) {
        boolean b = driver.getPageSource().contains(message);
        try {
            Assert.assertTrue(b);
            System.out.println("Found expected message: " + message + " ----- Test Passed!");
        } catch (Throwable e){
            System.out.println("Not Found expected message: " + message + " ----- Test Failed");
        }
    }

    @When("User logs in using Username as {string} and Password {string}")
    public void user_logs_in_using_Username_as_and_Password(String myUsername, String myPassword) {
        driver.findElement(By.name("UserName")).sendKeys(myUsername);
        driver.findElement(By.name("Password")).sendKeys(myPassword);
        System.out.println("Username is: " + myUsername);
        System.out.println("Password is: " + myPassword);
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Then("Return Login failed message {string}")
    public void return_Login_failed_message(String myMessage) {
        boolean b = driver.getPageSource().contains(myMessage);
        try {
            Assert.assertTrue(b);
            System.out.println("Found expected message: " + myMessage + " ----- Test Passed!");
        } catch (Throwable e){
            System.out.println("Not Found expected message: " + myMessage + " ----- Test Failed");
        }
    }

}