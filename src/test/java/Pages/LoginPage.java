package Pages;

import FrameWork.SharedDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public WebDriver driver;
    public LoginPage(SharedDriver sharedDriver) {
        this.driver = sharedDriver.getDriver();
       PageFactory.initElements(driver, this);
    }
    @FindBy(how= How.ID, using="user_email") WebElement EmailTextbox;
    @FindBy(how=How.ID, using="user_password") WebElement PassWordTexBox;
    @FindBy(how=How.XPATH, using="//input[@type='submit']") WebElement SubmitButton;
    @FindBy(how=How.XPATH,using="//a[@class='profile dropdown-toggle']//span[@class='visible-lg-inline'][text()='?']")WebElement LoggedInName;


   /* public void  load(){
        String URL = System.getProperty("URL");
        if(URL.equalsIgnoreCase("null"))
        {
            driver.get("http://www.mailinator.com");
        }else{
            driver.get(URL);

        }
    }*/

    public void  aeLoad(){
        String URL = System.getProperty("URL");
        String build_display_name = System.getProperty("BUILD_DISPLAY_NAME");

        System.out.println("*********************************************"+URL+"***********************************");
        System.out.println("*********************************************"+build_display_name+"***********************************");
        if(URL!=null)
        {
            driver.get(URL);

        }else{
            driver.get("https://braai-dev.alpha-exchange.com/users/sign_in");
        }
    }

    public void loginWithUserNameAndPassword(String username,String Password)
    {
        EmailTextbox.sendKeys(username);
        PassWordTexBox.sendKeys(Password);
        SubmitButton.click();
       // driver.findElement(By.id("user_password")).sendKeys(Password);
      //  driver.findElement(By.xpath("//input[@type='submit']")).submit();
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
