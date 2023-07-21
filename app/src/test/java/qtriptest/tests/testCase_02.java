package qtriptest.tests;

import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
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

public class testCase_02 extends DP{

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

    @Test(description = "Verify that Search and filters work fine", priority=2, groups = "Search and Filter flow", enabled=true, dataProvider = "data-provider")
    public static void TestCase02(String cityName, String categoryFilter, String timeFilter, String filteredResult, String unfilteredResult) throws InterruptedException, IOException{
        report = ReportSingleton.getReport();
        test = ReportSingleton.getTestInstance();
        HomePage home = new HomePage(driver);
        home.navigateToHomePage(); 
        home.searchForCity(cityName);
        Assert.assertTrue(home.isNoResultFound()||home.getSearchResult());
        home.clickOnCity();
        AdventurePage adventure = new AdventurePage(driver);
        Assert.assertTrue(adventure.verifyExistenceofCategoryDropdown());
        adventure.clearCategoryFilter();
        adventure.clearHoursFilter();
        adventure.selectCategogyFilter(categoryFilter);
        adventure.selectHoursFilter(timeFilter);
        adventure.verifyAdventureContents(filteredResult);
        adventure.clearHoursFilter();
        adventure.clearCategoryFilter();
        var status = adventure.verifyAdventureContents(unfilteredResult);
        try{
            Assert.assertTrue(status);
            test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+ "Search and filters functionality Passed");
        } catch (AssertionError e) {
            test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "Search and filters functionality Failed, reason: " +e.getMessage());
        }
        ReportSingleton.endTest();
    }
}
