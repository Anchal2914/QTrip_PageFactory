package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.UUID;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/register";
    public String lastGeneratedUsername = "";

    @FindBy(id ="floatingInput")
    WebElement email_text_box;
    //SeleniumWrapper.findElementWithRetry(driver, email_text_box,3);
    @FindBy(name ="password")
    WebElement password_text_box;
    @FindBy(name ="confirmpassword")
    WebElement confirm_password_text_box;
    @FindBy(xpath ="//button[contains(text(),'Register Now')]")
    WebElement register_button;

    public RegisterPage(RemoteWebDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    }

    public void navigateToRegisterPage(){
        SeleniumWrapper.navigate(driver, url);
    }

    public Boolean registerUser(String emailAddress, String password, boolean generateRandomUsername) throws InterruptedException{
        
        String test_data_email = emailAddress;
        
        if (generateRandomUsername == true)
        test_data_email  = UUID.randomUUID().toString()+emailAddress;

        String test_data_password = password;

        //System.out.println("Registering with emailAddress: " + test_data_email + test_data_password);
        //email_text_box.sendKeys(test_data_email);
        try {
            SeleniumWrapper.sendKeys(SeleniumWrapper.findElementWithRetry(driver, email_text_box, 3), test_data_email);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //password_text_box.sendKeys(test_data_password);
        try {
            SeleniumWrapper.sendKeys(SeleniumWrapper.findElementWithRetry(driver, password_text_box, 3), test_data_password);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //confirm_password_text_box.sendKeys(test_data_password);
        try {
            SeleniumWrapper.sendKeys(SeleniumWrapper.findElementWithRetry(driver, confirm_password_text_box, 3), test_data_password);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //register_button.click();
        SeleniumWrapper.click(register_button, driver);
        this.lastGeneratedUsername = test_data_email;
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/pages/login"));
        return this.driver.getCurrentUrl().endsWith("/login");
    }
}
