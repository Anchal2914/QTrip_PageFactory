package qtriptest.tests;

import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_03 extends DP{

    static RemoteWebDriver driver;
    static ExtentReports report; 
    static ExtentTest test;

    @BeforeTest(alwaysRun=true)
    public static void createDriver() throws MalformedURLException {
        driver = DriverSingleton.getDriverInstance();
    }

    public static String capture(WebDriver driver) throws IOException{
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File(System.getProperty("user.dir") + "/QTripImages/"+ System.currentTimeMillis() + ".png");
        String flpath = Dest.getAbsolutePath();
        FileUtils.copyFile(scrFile, Dest);
        return flpath;
    }

    @Test(description = "Verify that adventure booking and cancellation works fine", priority=3, groups = "Booking and Cancellation Flow", enabled=true, dataProvider = "data-provider")
    public static void TestCase03(String email, String password, String cityName, String adventureName, String guestName, String date, String guestCount) throws InterruptedException, IOException{ 
        report = ReportSingleton.getReport();
        test = ReportSingleton.getTestInstance();

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
        try{
            Assert.assertTrue(history.verifyCancleReservation());
            test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+ "Adventure booking and cancellation functionality Passed");
        } catch (AssertionError e) {
            test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "Adventure booking and cancellation functionality Failed, reason: " +e.getMessage());
        }
        ReportSingleton.endTest();
    }
}
