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

public class testCase_04 extends DP{

    static RemoteWebDriver driver;

    @BeforeTest(alwaysRun=true)
    public static void createDriver() throws MalformedURLException {
        DriverSingleton dsObj = DriverSingleton.getInstance();
        driver = dsObj.getDriver();
    }

    @Test(description = "Verify that Booking history can be viewed", priority=4, groups = "Reliability Flow", enabled=true, dataProvider = "data-provider")
    public static void TestCase04(String email, String password, String dataset1, String dataset2, String dataset3) throws InterruptedException{ 
        HomePage home = new HomePage(driver);
        home.navigateToHomePage(); 
        RegisterPage register = new RegisterPage(driver);
        register.navigateToRegisterPage(); 
        register.registerUser(email, password, true);
        String uniqueUsername = register.lastGeneratedUsername;
        LoginPage login = new LoginPage(driver);
        login.navigateToLoginPage();
        login.performLogin(uniqueUsername, password);
       // dataset1 = dataset1.split(";")[0];
        home.searchForCity(dataset1.split(";")[0]);
        Assert.assertTrue(home.isNoResultFound()||home.getSearchResult());
        home.clickOnCity();
        AdventurePage adventure = new AdventurePage(driver);
        adventure.searchAdventure(dataset1.split(";")[1]);
        adventure.selectAdventure();
        AdventureDetailsPage adventureDetail = new AdventureDetailsPage(driver);
        adventureDetail.bookAdventure(dataset1.split(";")[2], dataset1.split(";")[3], dataset1.split(";")[4]);
        Assert.assertTrue(adventureDetail.verifyReservation());
        adventureDetail.clickOnHome(); 
        home.searchForCity(dataset2.split(";")[0]);
        Assert.assertTrue(home.isNoResultFound()||home.getSearchResult());
        home.clickOnCity();
        adventure.searchAdventure(dataset2.split(";")[1]);
        adventure.selectAdventure();
        adventureDetail.bookAdventure(dataset2.split(";")[2], dataset2.split(";")[3], dataset2.split(";")[4]);
        Assert.assertTrue(adventureDetail.verifyReservation());
        adventureDetail.clickOnHome(); 
        home.searchForCity(dataset3.split(";")[0]);
        Assert.assertTrue(home.isNoResultFound()||home.getSearchResult());
        home.clickOnCity();
        adventure.searchAdventure(dataset3.split(";")[1]);
        adventure.selectAdventure();
        adventureDetail.bookAdventure(dataset3.split(";")[2], dataset3.split(";")[3], dataset3.split(";")[4]);
        Assert.assertTrue(adventureDetail.verifyReservation());
        adventureDetail.clickOnReservation();
        HistoryPage history = new HistoryPage(driver);
        Assert.assertTrue(history.verifyAllReservation());
    }
}
