package qtriptest;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSingleton {

    private static RemoteWebDriver driver=null;
   
    private DriverSingleton()  {
	}

    public static RemoteWebDriver getDriverInstance() throws MalformedURLException {
        DesiredCapabilities capabilities = null;
        if(driver==null){
           WebDriverManager.chromedriver().setup();
           ChromeOptions options = new ChromeOptions();
           options.addArguments("--test-type");
           capabilities = DesiredCapabilities.chrome();
           options.setCapability(ChromeOptions.CAPABILITY, options);

           driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
           driver.manage().window().maximize();
           return driver;
        }
        return driver;
    }

    public static void closeDriverInstance(){
        driver.quit();
    }

}
