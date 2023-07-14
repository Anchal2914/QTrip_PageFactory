package qtriptest.tests;

import qtriptest.DriverSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.net.MalformedURLException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_02 extends DP{

    static RemoteWebDriver driver;

    @BeforeTest(alwaysRun=true)
    public static void createDriver() throws MalformedURLException {
        DriverSingleton dsObj = DriverSingleton.getInstance();
        driver = dsObj.getDriver();
    }

    @Test(description = "Verify that Search and filters work fine", priority=2, groups = "Search and Filter flow", enabled=true, dataProvider = "data-provider")
    public static void TestCase02(String cityName, String categoryFilter, String timeFilter, String filteredResult, String unfilteredResult) throws InterruptedException{
        HomePage home = new HomePage(driver);
        home.navigateToHomePage(); 
        home.searchForCity(cityName);
        Assert.assertTrue(home.isNoResultFound()||home.getSearchResult());
        home.clickOnCity();
        AdventurePage adventure = new AdventurePage(driver);
        Assert.assertTrue(adventure.verifyExistenceofCategoryDropdown());
        adventure.selectCategogyFilter(categoryFilter);
        Assert.assertTrue(adventure.verifyExistenceofHoursDropdown());
        adventure.selectHoursFilter(timeFilter);
        adventure.verifyAdventureContents(filteredResult);
        adventure.clearHoursFilter();
        adventure.clearCategoryFilter();
        adventure.verifyAdventureContents(unfilteredResult);
    }
}
