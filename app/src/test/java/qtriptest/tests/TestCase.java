package qtriptest.tests;


import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestCase {

static RemoteWebDriver driver;

	// Method to help us log our Unit Tests
	public static void logStatus(String type, String message, String status) {
		System.out.println(String.format("%s |  %s  |  %s | %s",
				String.valueOf(java.time.LocalDateTime.now()), type, message, status));
	}

	// Iinitialize webdriver for our Unit Tests
	@BeforeClass(alwaysRun = true, enabled = false)
	public static void createDriver() throws MalformedURLException {
		logStatus("driver", "Initializing driver", "Started");
		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(BrowserType.CHROME);
		driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
		logStatus("driver", "Initializing driver", "Success");
	}


	@Test(description = "Verify functionality of - navigate to home page", enabled = false)
	public static void testHome_navigateToHomePage() {
		//Assertion assertion = new Assertion();
		logStatus("Page test", "navigation to home page", "started");
		try {
			HomePage register = new HomePage(driver);
			register.navigateToHomePage();
			logStatus("Page test", "navigation to home page", "success");
		} catch (Exception e) {
			logStatus("Page test", "navigation to home page", "failed");
			e.printStackTrace();
		}
	}

    @Test(description = "Verify functionality of - navigate to register page", enabled = false)
	public static void testHome_navigateToRegisterPage() {
		logStatus("Page test", "navigation to register page", "started");
		try {
			RegisterPage register = new RegisterPage(driver);
			register.navigateToRegisterPage();
			logStatus("Page test", "navigation to register page", "success");
		} catch (Exception e) {
			logStatus("Page test", "navigation to register page", "failed");
			e.printStackTrace();
		}
	}


	@Test(description = "Verify functionality of - register user", enabled = false)
	public static void testRegister_registerUser() {
		logStatus("Page test", "register user", "started");
		try {
			RegisterPage register = new RegisterPage(driver);
			register.navigateToRegisterPage();
			Boolean status = register.registerUser("testcheck@123", "abc@123", true);
			if(!status){
				throw new Exception("Not Registerted"); 
			}
			logStatus("Page test", "register user", "success");
		} catch (Exception e) {
			logStatus("Page test", "register user", "failed");
			e.printStackTrace();
		}
	}

	@Test(description = "Verify functionality of - navigate to login page", enabled = false)
	public static void testLogin_navigateToLoginPage() {
		//Assertion assertion = new Assertion();
		logStatus("Page test", "navigation to login page", "started");
		try {
			LoginPage login = new LoginPage(driver);
			login.navigateToLoginPage();
			logStatus("Page test", "navigation to login page", "success");
		} catch (Exception e) {
			logStatus("Page test", "navigation to login page", "failed");
			e.printStackTrace();
		}
	}

	@Test(description = "Verify functionality of - perform log in", enabled = false)
	public static void testLogin_performLogin() {
		try {
			logStatus("Page test", "perform log in", "started");
			RegisterPage register = new RegisterPage(driver);
			register.navigateToRegisterPage();
			register.registerUser("testcheck@123", "abc@123", true);
			String username = register.lastGeneratedUsername;
			Thread.sleep(3000);
			LoginPage login = new LoginPage(driver);
			login.navigateToLoginPage();
			Boolean status = login.performLogin(username, "abc@123");
			if(status){
				logStatus("Page test", "perform log in", "success");
			} 
		} catch (Exception e) {
			logStatus("Page test", "perform log in", "failed");
			e.printStackTrace();
		}
	}

	@Test(description = "Verify functionality of - verify user logged in", enabled = false)
	public static void testLogin_verifyUserLoggedIn() {
		try {
			logStatus("Page test", "verify user logged in", "started");
			RegisterPage register = new RegisterPage(driver);
			register.navigateToRegisterPage();
			register.registerUser("testcheck@123", "abc@123", true);
			String username = register.lastGeneratedUsername;
			Thread.sleep(3000);
			LoginPage login = new LoginPage(driver);
			login.navigateToLoginPage();
			login.performLogin(username, "abc@123");
			Boolean status = login.verifyUserLoggedIn();
			if(!status){
				throw new Exception("Not Logged in"); 
			}
			logStatus("Page test", "verify user logged in", "success");
		} catch (Exception e) {
			logStatus("Page test", "verify user logged in", "failed");
			e.printStackTrace();
		}
	}

    @Test(description = "Verify functionality of - perform log out", enabled = false)
	public static void testLogin_performLogout() {
		try {
			logStatus("Page test", "perform log out", "started");
			RegisterPage register = new RegisterPage(driver);
			register.navigateToRegisterPage();
			register.registerUser("testcheck@123", "abc@123", true);
			String username = register.lastGeneratedUsername;
			Thread.sleep(3000);
			LoginPage login = new LoginPage(driver);
			login.navigateToLoginPage();
			login.performLogin(username, "abc@123");
            login.verifyUserLoggedIn();
            Boolean status = login.performLogout();
			if(status){
				logStatus("Page test", "perform log out", "success"); 
			}
		} catch (Exception e) {
			logStatus("Page test", "perform log out", "failed");
			e.printStackTrace();
		}
	}

	@Test(description = "Verify functionality of - verify user logged out", enabled = false)
	public static void testLogin_verifyUserLoggedOut() {
		try {
			logStatus("Page test", "verify user logged out", "started");
			RegisterPage register = new RegisterPage(driver);
			register.navigateToRegisterPage();
			register.registerUser("testcheck@123", "abc@123", true);
			String username = register.lastGeneratedUsername;
			Thread.sleep(3000);
			LoginPage login = new LoginPage(driver);
			login.navigateToLoginPage();
			login.performLogin(username, "abc@123");
            login.verifyUserLoggedIn();
            login.performLogout();
			Boolean status = login.verifyUserLoggedOut();
			if(status){
				logStatus("Page test", "verify user logged out", "success");
			}
		} catch (Exception e) {
			logStatus("Page test", "verify user logged out", "failed");
			e.printStackTrace();
		}
	}

	// Quit webdriver after Unit Tests
	@AfterClass(enabled = true)
	public static void quitDriver() throws MalformedURLException {
		driver.close();
		driver.quit();
		logStatus("driver", "Quitting driver", "Success");
	}

}

