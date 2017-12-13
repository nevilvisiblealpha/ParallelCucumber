package Pages;

import FrameWork.SharedDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPage2 {
    public WebDriver driver;
    public LoginPage2(SharedDriver sharedDriver) {
        this.driver = sharedDriver.getDriver();
       // PageFactory.initElements(driver, this);
    }


   public void  load(){

        driver.get("http://www.mailinator.com");
    }


    public void EnterEmailAddress(String value){

       driver.findElement(By.id("inboxfield")).sendKeys(value);
       driver.findElement(By.xpath("//button[@class='btn btn-dark']")).click();
    }

}
