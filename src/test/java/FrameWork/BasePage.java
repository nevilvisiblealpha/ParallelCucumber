package FrameWork;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Month;
import java.util.List;

public class BasePage {



    protected WebDriver driver;
    private WebDriverWait wait;
    final int DefaultTimeOut = 15;

    public BasePage(WebDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver, DefaultTimeOut);
    }



    public BasePage ClickOn(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        return this;
    }

    public BasePage KeyBoardEnter(By Locator)
    {
        wait.until(ExpectedConditions.elementToBeClickable(Locator)).sendKeys(Keys.ENTER);
        return this;
    }

    public BasePage waitUntilDisappear(By Locator,int timeout)
    {

        WebDriverWait wait = new WebDriverWait(driver,timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(Locator));

        return this;
    }



    public BasePage Enter(By locator,String inputText) {
      WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
      element.clear();
      element.sendKeys(inputText);
        return this;
    }

    public boolean isDisplayed(By Locator,int timeOutinSec)
    {  WebDriverWait wait = new WebDriverWait(driver,timeOutinSec);
    try {
     return   wait.until(ExpectedConditions.visibilityOfElementLocated(Locator)).isDisplayed();
    }
    catch (Exception e)
    {
        return false;
    }

    }



    public BasePage SelectFromDropDown(By Locator, String ValueTobeSelect)
    {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(Locator)));
      List<WebElement> Options = select.getOptions();
      for(WebElement option:Options)
        {
            if(option.getText().equalsIgnoreCase(ValueTobeSelect))
            {
                wait.until(ExpectedConditions.elementToBeClickable(option)).click();
            }
        }

        return this;
    }


   public BasePage SelectFromAutoSuggest(By Locator,By AutosuggestTextLocator ,By AutosuggestArea,String valueToBeSelect) {

        ClickOn(Locator);
        Enter(AutosuggestTextLocator,valueToBeSelect);
        ClickOn(AutosuggestArea);



        return this;

    }

    public List<WebElement> getWebElements(By Locator)
    {

        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Locator));
    }

   public String getAttributeValue(By Locator,String attribute)
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(Locator)).getAttribute(attribute);
    }

    public Boolean isAttributeValuePresent(By Locator, String Attribute, String value)
    {
        return wait.until(ExpectedConditions.attributeContains(Locator,Attribute,value));
    }

    public Boolean waitUntilTextValuePresent(By Locator, String Text,int timeout)

    {
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        return wait.until(ExpectedConditions.textToBe(Locator,Text));
    }



    public String getText(By Locator)
    {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(Locator)).getText();
    }


}
