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

public class LoginPage {

    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login";

    @FindBy(id ="floatingInput")
    WebElement email_txt_box;
    @FindBy(id ="floatingPassword")
    WebElement password_txt_box;
    @FindBy(xpath ="//button[contains(text(),'Login to QTrip')]")
    WebElement login_button;
    @FindBy(xpath ="//div[contains(text(),'Logout')]")
    WebElement logout_button;
    @FindBy(xpath ="//a[contains(text(),'Login Here')]")
    WebElement loginButton;

    public LoginPage(RemoteWebDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    }

    public void navigateToLoginPage(){
        SeleniumWrapper.navigate(driver, url);
    }

    public Boolean performLogin(String emailAddress, String password) throws InterruptedException{
        
        //System.out.println("logging in with email: " + emailAddress + "pwd: " + password);
        //email_txt_box.sendKeys(emailAddress);
        try {
            SeleniumWrapper.sendKeys(SeleniumWrapper.findElementWithRetry(driver, email_txt_box, 3), emailAddress);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //password_txt_box.sendKeys(password);
        try {
            SeleniumWrapper.sendKeys(SeleniumWrapper.findElementWithRetry(driver, password_txt_box, 3), password);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //login_button.click();
        SeleniumWrapper.click(login_button, driver);
        Thread.sleep(5000);
        FluentWait<RemoteWebDriver> wait = new FluentWait<>(driver)
                .withTimeout((Duration.ofSeconds(30)))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Logout')]")));
        return false;
    }

    public Boolean verifyUserLoggedIn(){
        try{
            return logout_button.getText().equals("Logout");
        } catch(Exception e) {
        return false;
        }
    }
    
    public Boolean performLogout() throws InterruptedException{
    
        //logout_button.click();
        SeleniumWrapper.click(logout_button, driver);
        Thread.sleep(3000);
        FluentWait<RemoteWebDriver> wait = new FluentWait<>(driver)
                .withTimeout((Duration.ofSeconds(30)))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Login Here')]")));
        return false;
    }

    public Boolean verifyUserLoggedOut(){
        try{
            return loginButton.getText().equals("Login Here");
        } catch(Exception e) {
        return false;
        }
    }

}
