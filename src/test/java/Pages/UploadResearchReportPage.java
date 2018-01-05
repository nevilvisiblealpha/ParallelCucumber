package Pages;

import FrameWork.BasePage;
import FrameWork.SharedDriver;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UploadResearchReportPage extends BasePage {

    public WebDriver driver;
    public UploadResearchReportPage(SharedDriver sharedDriver) {
        super( sharedDriver.getDriver());
        this.driver = sharedDriver.getDriver();
    }

    By research_report_title = By.id("research_report_title");


    @Then("^I should see uploaded research form$")
    public void i_should_see_upload_research_form() throws Throwable {
       ClickOn(research_report_title);
    }



}
