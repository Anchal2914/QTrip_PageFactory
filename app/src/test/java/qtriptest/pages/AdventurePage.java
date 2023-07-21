package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdventurePage {

    RemoteWebDriver driver;
    //String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/?city=new-york";

    @FindBy(id ="duration-select")
    WebElement hoursDropdown;
    @FindBy(xpath ="//select[@id='duration-select']/following-sibling::div")
    WebElement clearHours;
    @FindBy(id ="category-select")
    WebElement categoryDropdown;
    @FindBy(xpath ="//select[@id='category-select']/following-sibling::div")
    WebElement clearDuration;
    @FindAll({
        @FindBy(xpath ="//*[@id='data']/div"),
        @FindBy(xpath ="//*[@id='data']")
     })
    List<WebElement> adventureContents;
    @FindBy(id ="search-adventures")
    WebElement searchAdventure;
    @FindBy(xpath ="//div[@id='data']/div[1]/a")
    WebElement selectAdventure;
    
    
    public AdventurePage(RemoteWebDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    }

    // public void navigateToAdventurePage() throws InterruptedException{
    //     if(!driver.getCurrentUrl().equals(this.url)){
    //         driver.get(this.url);
    //     }
    // }

    public Boolean verifyExistenceofHoursDropdown() {
        Boolean status = false;
        try {
            status = hoursDropdown.isDisplayed();
            return status;
        } catch (Exception e) {
            return status;
        }
    }
    
    public Boolean selectHoursFilter(String hours) {
        try {
            Select hoursDuration = new Select(hoursDropdown);
            hoursDuration.selectByVisibleText(hours);
            Thread.sleep(3000);
            return false;
        } catch (Exception e) {
            System.out.println("Exception Occurred while selecting the hoursDuration: " + e.getMessage());
            return false;
        }

    }

    public Boolean clearHoursFilter() {
        try {
            //clearHours.click();
            SeleniumWrapper.click(clearHours, driver);
            Thread.sleep(3000);
            return false;
        } catch (Exception e) {
            System.out.println("Exception Occurred while clearing the hoursDuration filter: " + e.getMessage());
            return false;
        }
    }

    public Boolean verifyExistenceofCategoryDropdown() {
        Boolean status = false;
        try {
            status = categoryDropdown.isDisplayed();
            return status;
        } catch (Exception e) {
            return status;
        }
    }
    
    public Boolean selectCategogyFilter(String category) {
        try {
            Select categoryDuration = new Select(categoryDropdown);
            categoryDuration.selectByVisibleText(category);
            Thread.sleep(3000);
            return false;
        } catch (Exception e) {
            System.out.println("Exception Occurred while selecting the category: " + e.getMessage());
            return false;
        }

    }

    public Boolean clearCategoryFilter() {
        try {
            //clearDuration.click();
            SeleniumWrapper.click(clearDuration, driver);
            Thread.sleep(3000);
            return false;
        } catch (Exception e) {
            System.out.println("Exception Occurred while clearing the category filter: " + e.getMessage());
            return false;
        }
    }

    public Boolean verifyAdventureContents(String resultList) {
        try {
            Integer actualResult = (adventureContents.size())-1;
            String result = actualResult.toString();
            if(result.equals(resultList)){
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Exception while verifying adventure contents: " + e.getMessage());
            return true;
        }
    }

    public Boolean searchAdventure(String adventureName) {
        try {
            Thread.sleep(3000);
            //searchAdventure.sendKeys(adventureName);
            SeleniumWrapper.sendKeys(SeleniumWrapper.findElementWithRetry(driver, searchAdventure, 3), adventureName);
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println("Exception Occurred while searching the adventure: " + e.getMessage());
        }
        return false;
    }

    public Boolean selectAdventure() {
        try {
            //selectAdventure.click();
            SeleniumWrapper.click(selectAdventure, driver);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.urlContains("https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/detail"));
        } catch (Exception e) {
            System.out.println("Exception Occurred while selecting the adventure: " + e.getMessage());
        }
        return false;
    }

}