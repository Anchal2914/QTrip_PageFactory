package qtriptest.pages;

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
        if(!driver.getCurrentUrl().equals(this.url)){
            driver.get(this.url);
        }
    }

    public Boolean registerUser(String emailAddress, String password, boolean generateRandomUsername) throws InterruptedException{
        
        String test_data_email = emailAddress;
        
        if (generateRandomUsername == true)
        test_data_email  = UUID.randomUUID().toString()+emailAddress;

        String test_data_password = password;

        //System.out.println("Registering with emailAddress: " + test_data_email + test_data_password);
        email_text_box.sendKeys(test_data_email);
        password_text_box.sendKeys(test_data_password);
        confirm_password_text_box.sendKeys(test_data_password);
        register_button.click();
        this.lastGeneratedUsername = test_data_email;
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/pages/login"));
        return this.driver.getCurrentUrl().endsWith("/login");
    }
}
