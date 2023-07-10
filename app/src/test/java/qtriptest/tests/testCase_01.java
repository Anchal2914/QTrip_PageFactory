package qtriptest.tests;

import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class testCase_01 extends DP{
    
    static RemoteWebDriver driver;

    public static void logStatus(String type, String message, String status) {
        System.out.println(String.format("%s |  %s  |  %s | %s",
                String.valueOf(java.time.LocalDateTime.now()), type, message, status));
    }

    // Iinitialize webdriver for our Unit Tests
    @BeforeSuite(enabled = true, alwaysRun = true)
    public static void createDriver() throws MalformedURLException {
        logStatus("driver", "Initializing driver", "Started");
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
        logStatus("driver", "Initializing driver", "Success");
    }
    
    @Test(description = "Verify user registration - login - logout", enabled=true, dataProvider = "data-provider")
    public static void TestCase01(String emailAddress, String password) throws InterruptedException{
        HomePage home = new HomePage(driver);
        home.navigateToHomePage(); 
        Thread.sleep(5000);
        RegisterPage register = new RegisterPage(driver);
        register.navigateToRegisterPage(); 
        Assert.assertTrue(register.registerUser(emailAddress, password, true));
        String uniqueUsername = register.lastGeneratedUsername;
        Thread.sleep(3000);
        LoginPage login = new LoginPage(driver);
        login.navigateToLoginPage();
        login.performLogin(uniqueUsername, password);
        Assert.assertTrue(login.verifyUserLoggedIn());
        login.performLogout();
        Assert.assertTrue(login.verifyUserLoggedOut());    
    }
}
