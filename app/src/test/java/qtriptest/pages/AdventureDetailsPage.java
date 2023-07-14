package qtriptest.pages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.JavascriptExecutor;

public class AdventureDetailsPage {

    RemoteWebDriver driver;
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @FindBy(xpath  ="//*[@id='myForm']")
    WebElement form;
    @FindBy(xpath  ="//form[@id='myForm']/input[1]")
    WebElement nameTextBox;
    @FindBy(xpath ="//form[@id='myForm']/input[2]")
    WebElement dateTextBox;
    @FindBy(xpath ="//form[@id='myForm']/div[1]/div[2]/input")
    WebElement personTextBox;
    @FindBy(xpath ="//form[@id='myForm']/button")
    WebElement reserveButton;
    @FindBy(id ="reserved-banner")
    WebElement successMassage;
    @FindBy(xpath ="//a[contains(text(),'Reservations')]")
    WebElement reservationLink;
    @FindBy(xpath ="//a[contains(text(),'Home')]")
    WebElement homeLink;
    
    
    public AdventureDetailsPage(RemoteWebDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    }

    public Boolean bookAdventure(String name, String date, String persons) throws InterruptedException{
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(3000);
        nameTextBox.sendKeys(name);
        dateTextBox.sendKeys(date);
        personTextBox.clear();
        personTextBox.sendKeys(persons);
        reserveButton.click();
        Thread.sleep(5000);
        FluentWait<RemoteWebDriver> wait = new FluentWait<>(driver)
                .withTimeout((Duration.ofSeconds(30)))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reserved-banner")));
        return false;
    }

    public Boolean verifyReservation() {
            try{
                Thread.sleep(3000);
                successMassage.isDisplayed();
                Thread.sleep(3000);
                return true;
            } catch(Exception e) {
                return false;
            }
    }

    public void clickOnReservation() throws InterruptedException{
        try {
            Thread.sleep(3000);
            reservationLink.click();
        } catch (Exception e){
            System.out.println("Exception while clciking on reservation link: " + e.getMessage());
        }
    }

    public void clickOnHome() throws InterruptedException{
        try {
            Thread.sleep(3000);
            homeLink.click();
        } catch (Exception e){
            System.out.println("Exception while clciking on reservation link: " + e.getMessage());
        }
    }
}