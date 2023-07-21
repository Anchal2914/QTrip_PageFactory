package qtriptest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumWrapper {

    
    
    public static boolean click(WebElement elementToClcik, WebDriver driver ){
        if(elementToClcik.isDisplayed()){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", elementToClcik);
            elementToClcik.click();
            return true;
        }
        return false;
    }

    public static boolean sendKeys(WebElement inputBox, String keysToSend ){
        inputBox.clear();
        inputBox.sendKeys(keysToSend);
        return false; 
    }

    public static boolean navigate(WebDriver driver, String url ){
        if(!driver.getCurrentUrl().equals(url)){
            driver.get(url);
        }
        return false;
    }

    public static WebElement findElementWithRetry(WebDriver driver, WebElement element, int retryCount ) throws Exception{
        By by =null;
        // try {
        //     WebDriverWait wait = new WebDriverWait(driver, 30);
        //     element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        // } catch (Exception e) {
        
        //     for(int i=0; i<retryCount; i++){
        //         element = driver.findElement(by);
        //         if(element.isDisplayed())
        //         return element;
        //     }
        // }
        for(int i = 1; i <= retryCount; i++) {
            try { 
                 element = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(by));
                if (element.isDisplayed()) {
                    return element;
                }
            }
            catch(Exception e) {
                // ignore
            }
        }
        return element;
    }
}
