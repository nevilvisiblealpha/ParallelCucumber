package Pages;

import FrameWork.SharedDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    public WebDriver driver;
    public LoginPage(SharedDriver sharedDriver) {
        this.driver = sharedDriver.getDriver();
      //  PageFactory.initElements(driver, this);
    }


   public void  load(){

        driver.get("http://www.mailinator.com");
    }

    public void  aeLoad(){

        driver.get("https://braai-dev.alpha-exchange.com/users/sign_in");
    }

    public void loginWithUserNameAndPassword(String username,String Password)
    {
        driver.findElement(By.id("user_email")).sendKeys(username);
        driver.findElement(By.id("user_password")).sendKeys(Password);
        driver.findElement(By.xpath("//input[@type='submit']")).submit();
    }


   public boolean verifyUserNameIsDisplayed(String name) {
        boolean isDisplayed = driver.findElement(By.xpath("//a[@class='profile dropdown-toggle']//span[@class='visible-lg-inline'][text()='"+name+"']")).isDisplayed();
        Assert.assertTrue(isDisplayed);
        return isDisplayed;
    }

    public void EnterEmailAddress(String value){

       driver.findElement(By.id("inboxfield")).sendKeys(value);
       driver.findElement(By.xpath("//button[@class='btn btn-dark']")).click();
    }

    public void isErrorMessageDisplayed(String ErrorMessage)
    {
        String ErrorMessageFromPage =   driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissable']")).getText();
        Assert.assertTrue(ErrorMessageFromPage.contains(ErrorMessage));
    }

}
