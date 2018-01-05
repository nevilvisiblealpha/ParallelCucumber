package Pages;

import FrameWork.BasePage;
import FrameWork.SharedDriver;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Header extends BasePage {

    public WebDriver driver;
    public Header(SharedDriver sharedDriver) {
        super( sharedDriver.getDriver());
        this.driver = sharedDriver.getDriver();
        // PageFactory.initElements(driver, this);
    }


    By UserProfileDropdown = By.xpath("//a[@class='profile dropdown-toggle']");
    By Tab(String Value) {return By.xpath("//ul[@class='nav navbar-nav menu' or @class='nav navbar-nav col-md-6 menu']/li/a[text()='"+Value+"']");}

    By UserProfileDropdownOtion(String option) {
        return By.xpath("//ul[@class='dropdown-menu']/li/a[text()='"+option+"']");
    }

    By PageHeader(String HeaderName){
        return By.xpath("//a[@class='navbar-brand'][text()='"+HeaderName+"']");
    }


    @When("^I navigate to the \"([^\"]*)\" of User Profile Dropdown$")
    public void i_navigate_to_the_of_User_Profile_Dropdown(String Option) throws Throwable {
        ClickOn(UserProfileDropdown);
        ClickOn(UserProfileDropdownOtion(Option));
    }

    @Then("^I should direct to the \"([^\"]*)\" page$")
    public void i_should_direct_to_the_page(String pageName) throws Throwable {
        Assert.assertTrue(isDisplayed(PageHeader(pageName),10));
    }

    @Then("^I should direct to the \"([^\"]*)\" tab$")
    public void i_should_direct_to_the_tab(String tabName) throws Throwable {
        Assert.assertTrue(isAttributeValuePresent( Tab(tabName),"class","active"));
    }

}
