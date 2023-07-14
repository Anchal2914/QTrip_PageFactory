package qtriptest;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSingleton {

    private static DriverSingleton getInstance=null;
    private static RemoteWebDriver driver;
   
    private DriverSingleton()  {
        final DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(BrowserType.CHROME);
            try {
                driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
                driver.manage().window().maximize();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
	}
   
    public static DriverSingleton getInstance() throws MalformedURLException {
        if(getInstance==null){
            getInstance = new DriverSingleton();
        }
        return getInstance;
    }

    public RemoteWebDriver getDriver()
    {
    	return driver;
    }
}
