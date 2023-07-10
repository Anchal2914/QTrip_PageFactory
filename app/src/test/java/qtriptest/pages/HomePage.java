package qtriptest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {

    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app";

    @FindBy(className ="navbar-toggler")
    WebElement menuBar;
    @FindBy(xpath ="//a[contains(text(),'Register')]")
    WebElement registerLink;

    public HomePage(RemoteWebDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    }

    public void navigateToHomePage() throws InterruptedException{
        if(!driver.getCurrentUrl().equals(this.url)){
            driver.get(this.url);
        }
        menuBar.click();
        Thread.sleep(5000);
        registerLink.click();
        Thread.sleep(3000);
    }
}