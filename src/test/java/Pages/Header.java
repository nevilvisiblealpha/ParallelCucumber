package Pages;

import FrameWork.BasePage;
import FrameWork.SharedDriver;
import cucumber.api.java.en.When;
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

    By UserProfileDropdownOtion(String option) {
        return By.xpath("//ul[@class='dropdown-menu']/li/a[text()='"+option+"']");
    }


    @When("^I navigate to the \"([^\"]*)\" of User Profile Dropdown$")
    public void i_navigate_to_the_of_User_Profile_Dropdown(String Option) throws Throwable {
        ClickOn(UserProfileDropdown);
        ClickOn(UserProfileDropdownOtion(Option));
    }


}
