package qtriptest.tests;

import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_03 extends DP{

    static RemoteWebDriver driver;

    @BeforeTest(alwaysRun=true)
    public static void createDriver() throws MalformedURLException {
        DriverSingleton dsObj = DriverSingleton.getInstance();
        driver = dsObj.getDriver();
    }

    @Test(description = "Verify that adventure booking and cancellation works fine", priority=3, groups = "Booking and Cancellation Flow", enabled=true, dataProvider = "data-provider")
    public static void TestCase03(String email, String password, String cityName, String adventureName, String guestName, String date, String guestCount) throws InterruptedException{ 
        HomePage home = new HomePage(driver);
        home.navigateToHomePage(); 
        RegisterPage register = new RegisterPage(driver);
        register.navigateToRegisterPage(); 
        register.registerUser(email, password, true);
        String uniqueUsername = register.lastGeneratedUsername;
        LoginPage login = new LoginPage(driver);
        login.navigateToLoginPage();
        login.performLogin(uniqueUsername, password);
        home.searchForCity(cityName);
        Assert.assertTrue(home.isNoResultFound()||home.getSearchResult());
        home.clickOnCity();
        AdventurePage adventure = new AdventurePage(driver);
        adventure.searchAdventure(adventureName);
        adventure.selectAdventure();
        AdventureDetailsPage adventureDetail = new AdventureDetailsPage(driver);
        adventureDetail.bookAdventure(guestName, date, guestCount);
        Assert.assertTrue(adventureDetail.verifyReservation());
        adventureDetail.clickOnReservation();
        HistoryPage history = new HistoryPage(driver);
        history.storeTransactionId();
        history.cancleReservation();
        Assert.assertTrue(history.verifyCancleReservation());
    }
}
