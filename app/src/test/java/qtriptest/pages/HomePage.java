package qtriptest.pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app";

    
    @FindBy(xpath ="//a[contains(text(),'Register')]")
    WebElement registerLink;
    @FindBy(id ="autocomplete")
    WebElement searchCity;
    @FindBy(xpath ="//h5[text()='No City found']")
    WebElement noResults;
    @FindBy(id ="results")
    WebElement cityResults;

    public HomePage(RemoteWebDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    }

    public void navigateToHomePage() throws InterruptedException{
        if(!driver.getCurrentUrl().equals(this.url)){
            driver.get(this.url);
        }
    }

    // public void clickOnRegisterLink() throws InterruptedException{
    //     try {
    //         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    //         registerLink.click();
    //         Thread.sleep(2000);
    //     } catch (Exception e){
    //         System.out.println("Exception while clciking on Register Link: " + e.getMessage());
    //     }
    // }

    public Boolean searchForCity(String city){
        try {
            Thread.sleep(3000);
            searchCity.clear();
            searchCity.sendKeys(city);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='results']/a")),
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='No City found']"))));
            Thread.sleep(3000);
            return true;
            } catch (Exception e) {
                System.out.println("Error while searching for a product: " + e.getMessage());
            return false;
        }
    }

    public Boolean isNoResultFound() {
        Boolean status = false;
        try {
            status = noResults.isDisplayed();
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    public Boolean getSearchResult() {
        Boolean status = false;
        try {
            status = cityResults.isDisplayed();
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    public Boolean clickOnCity() {
        try {
            cityResults.click();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.urlContains("https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/"));
                    return true;
        } catch (Exception e) {
            System.out.println("Exception while clciking on city found: " + e.getMessage());
            return false;
        }
    }

}
   