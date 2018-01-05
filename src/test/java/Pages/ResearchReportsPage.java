package Pages;

import FrameWork.BasePage;
import FrameWork.SharedDriver;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResearchReportsPage extends BasePage {


    public WebDriver driver;
    public ResearchReportsPage(SharedDriver sharedDriver) {
        super( sharedDriver.getDriver());
        this.driver = sharedDriver.getDriver();
    }


    By commentsList = By.xpath("//div[@class='row do-not-follow-scroll']//div/p");
    By shareButton = By.xpath("//a[contains(@id,'share-research-report')]");
    By shareOption(String Option) { return  By.xpath("//ul[@class='dropdown-menu']/li/a[text()='"+Option+"']");};
    By saveButton = By.xpath("//ul[contains(@id,'save_research_dropdown_')]/ancestor::span[@class='dropdown']");
    By saveOption(String Option){return By.xpath("//ul[contains(@id,'save_research_dropdown_')]/li/a[text()='"+Option+"']");}
    By MessageLeftTop = By.xpath("//div[@class='noty_body']");




    @Then("^I should see same comment shows up when opening research report$")
    public void i_should_see_same_comment_shows_up_when_opening_research_report_by_clicking_on_the_title() throws Throwable {

      boolean  commentFound= false;
        List<WebElement> comments=  getWebElements(commentsList);
               for(WebElement comment: comments)
               {

                  if( comment.getText().equalsIgnoreCase(DashboardPage.commentValue))
                  {
                      commentFound= true;
                      break;
                  }

               }

        Assert.assertTrue(commentFound);

    }
    @When("^I share report with company$")
    public void i_share_report_with_company() throws Throwable {
        ClickOn(shareButton);
        ClickOn(shareOption("Share with company"));
    }


    @When("^I save the report in personal brief case$")
    public void i_save_the_report_in_personal_brief_case() throws Throwable {
        ClickOn(saveButton);
        ClickOn(saveOption("Save to personal briefcase"));
        waitUntilDisappear(MessageLeftTop,20);
    }

    @When("^I save the report to company RMS$")
    public void i_save_the_report_to_company_RMS() throws Throwable {
        ClickOn(saveButton);
        ClickOn(saveOption("Save to company RMS"));
        waitUntilDisappear(MessageLeftTop,20);
    }




}
