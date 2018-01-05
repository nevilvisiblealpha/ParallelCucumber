package Pages;

import FrameWork.BasePage;
import FrameWork.SharedDriver;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForwardCalenderPage extends BasePage {
    public WebDriver driver;
    public ForwardCalenderPage(SharedDriver sharedDriver) {
        super( sharedDriver.getDriver());
        this.driver = sharedDriver.getDriver();
    }

    By  EventTitleText =By.id("event_title");





    @Then("^I should see create event form$")
    public void i_should_see_create_event_form() throws Throwable {

        isDisplayed(EventTitleText,10);

    }

}
