import org.testng.Assert;
import org.testng.annotations.*;

public class annotationsTest {
    @BeforeClass
    public static void Beginning(){
        System.out.println("running @BeforeClass: Starting");
    }

    @BeforeTest
    public void OpenBrowser(){
        System.out.println("running @BeforeTest: Open browser");
    }

    @AfterClass
    public static void Ending(){
        System.out.println("running @AfterClass: Ending");
    }

    @AfterTest
    public void CloseBrowser(){
        System.out.println("running @AfterTest: Close browser");
    }

    @Test
    public void SendYahooEmailTest(){
        System.out.println("running @Test: Testing Sending Yahoo Email");
        int SendingOutYahooEmail = 10;
        int ReceivingYahooEmail = 11;
        try{
            Assert.assertEquals(SendingOutYahooEmail, ReceivingYahooEmail);
        }catch(Throwable t){
            System.out.println("Error encountered");
        }
        System.out.println("Sending Yahoo Email != Receiving Yahoo Email");
    }

    @Test
    public void RegisterAccountTest(){
        System.out.println("running @Test: Testing Register Account");
    }

    @Ignore
    @Test
    public void OnlineShoppingTest(){
        System.out.println("running @Test: Testing Online Shopping");
    }

}
