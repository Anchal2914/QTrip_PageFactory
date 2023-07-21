
package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class HistoryPage {

    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/index.html";

    @FindBy(xpath ="//tbody[@id='reservation-table']/tr/th")
    WebElement transactionId;
    @FindBy(xpath ="//tbody[@id='reservation-table']/tr/th")
    WebElement removeTranID;
    @FindBy(className ="cancel-button")
    WebElement cancleReservationButton;
    @FindBy(id ="no-reservation-banner")
    WebElement noReservationField;
    @FindBy(xpath ="//tbody[@id='reservation-table']/tr")
    WebElement allReservations;
    
    
    
    public HistoryPage(RemoteWebDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    }

    public void navigateToRerservationPage(){
        SeleniumWrapper.navigate(driver, url);
    }

    public String storeTransactionId(){
        try{
            return transactionId.getText();
        } catch(Exception e) {
        return null;
        }
    }

    public Boolean cancleReservation() throws InterruptedException{
    
        Thread.sleep(3000);
        //cancleReservationButton.click();
        SeleniumWrapper.click(cancleReservationButton, driver);
        FluentWait<RemoteWebDriver> wait = new FluentWait<>(driver)
                .withTimeout((Duration.ofSeconds(30)))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("no-reservation-banner")));
        driver.navigate().refresh();
        return false;
    }

    public Boolean verifyAllReservation(){
        try{
            Thread.sleep(3000);
            noReservationField.isDisplayed();
            noReservationField.getSize();
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public Boolean verifyCancleReservation() {
        try{
            Thread.sleep(3000);
            noReservationField.isDisplayed();
            return true;
        } catch(Exception e) {
            return false;
        }
    }

}